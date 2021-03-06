package com.alwin.asap;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ide.impl.ProjectUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class OpenAndroidSystemAppProject extends AnAction {
    public String mUsersLastProjPath;
    public String mUsersLasTSrcPath;
    private String mDefaltSrcPath;
    private String mDefaltProjPath;
    private final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("android_sys_app_plugin", NotificationDisplayType.BALLOON, true);

    public Notification notify(Project project, String content) {
        final Notification notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.INFORMATION);
        notification.notify(project);
        return notification;
    }

    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
    }

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        com.alwin.asap.SettingState mySettingState = com.alwin.asap.SettingState.getInstance();
        mDefaltSrcPath = mySettingState.sorucePath;
        mDefaltProjPath= mySettingState.projectPath;
        if (mDefaltSrcPath.isEmpty()){
            mDefaltSrcPath = actionEvent.getProject().getBasePath();
        }
        if (mDefaltProjPath.isEmpty()){
            mDefaltProjPath = actionEvent.getProject().getBasePath();
        }
        com.alwin.asap.NewProjectDialogBox dlg = new com.alwin.asap.NewProjectDialogBox(mDefaltSrcPath, mDefaltProjPath);
        dlg.show();
        if (dlg.isOK()){
            // Edit input file path
            String outputPath = dlg.getOutputPath().replaceAll("\\\\", "/");
            String appSourcePath = dlg.getAppSourcePath().replaceAll("\\\\", "/");
            // Create output directory if not exist
            File outputDir = new File(outputPath);
            if (! outputDir.exists()){
                outputDir.mkdirs();
            }
            com.alwin.asap.SettingState settings = com.alwin.asap.SettingState.getInstance();
            mDefaltSrcPath = appSourcePath;
            mDefaltProjPath = outputPath;
            settings.sorucePath = mDefaltSrcPath;
            settings.projectPath = mDefaltProjPath;
            String buildGradleTemplate = settings.buildGradleTemplate;
            if (buildGradleTemplate.isEmpty()){
                buildGradleTemplate = getBuildGradleTemplate();
                settings.buildGradleTemplate = buildGradleTemplate;
            }
            com.alwin.asap.GradleFileGenerator gradleFileGenerator = new com.alwin.asap.GradleFileGenerator(mDefaltSrcPath, mDefaltProjPath, buildGradleTemplate);
            gradleFileGenerator.createBuildGradleFile();
            // Open the built project
            try{
                Project openedProject = ProjectUtil.openOrImport(outputDir.getPath(), actionEvent.getProject(), false);
            } catch (Exception excp) {
                notify(actionEvent.getProject(), "Fail to open project" + excp.getMessage());
            }
        }
    }

    public static InputStream openResourceAsStream(Class clazz, String fileName) {
        InputStream input = clazz.getResourceAsStream("/" + fileName);
        return input;
    }

    public static InputStreamReader openResourceAsStreamReader(Class clazz, String fileName) {
        return new InputStreamReader(
                openResourceAsStream(clazz, fileName), Charset.forName("UTF-8"));
    }

    public String getBuildGradleTemplate() {
        // Looks for build.gradle in resource
        String line = null;
        InputStreamReader buildFileInputStringReader = openResourceAsStreamReader(getClass(), "build.gradle");
        BufferedReader buildFileBufferReader = new BufferedReader(buildFileInputStringReader);
        StringBuilder outputStringBuilder = new StringBuilder().append(System.lineSeparator());
        try{
            while ((line = buildFileBufferReader.readLine()) != null) {
                outputStringBuilder.append(line + System.lineSeparator());
            }
            buildFileInputStringReader.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
        return outputStringBuilder.toString();
    }
}