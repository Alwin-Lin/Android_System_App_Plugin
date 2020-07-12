# Android_System_App_Plugin
## Intorduction
- The plugin generates build.gradle after user inputs their project source and where they want the gradle to be, then opens up the generated gradle file as a project.
## Problems
- For each new Android project you need to create and edit build.gradle 
## Install
1. Download from release or clone and build the project.
   * The built can be found under \android_sys_app_plugin\build\libs
2. Open Intellij/Android Studio > Help > Find action > "Install plugin from disk"
3. Chose the plugin jar file
## Using plugin
- The plugin can be found next to Help button
- Source dir is where your AndroidManifest.xml and source code is located
- Project dir is where you wish the project to be created, if the folder you enter does not exsit, it will create one.
- If you wish to edit build.gradle template, go to Settings in Intellij/Android Studio > Tools
