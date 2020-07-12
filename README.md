# Android_System_App_Plugin
## Intorduction
This plugin helps to create Android Studio project for Android system apps to leverage the IDE and tools millions developers use.
## Problems
Developing Android system apps can be painful because there are no specific IDE optimised for it.
## Install to Android Studio
1. Build this plugin or download a [release](https://github.com/Alwin-Lin/Android_System_App_Plugin/releases/tag/1.0) .
2. Open Android Studio > Help > Find action > "Install plugin from disk"
3. Chose the plugin jar.
## How to use it
- Use the plugin menu item AndroidSysApp to Open a project in concern.
  - Source dir is where the source code of the project, it should include AndroidManifest.xml.
  - Project dir is where the project will be created 
    - This can be sepreated from your source code to avoid complicating the git repo.
    - If the folder you enter does not exsit, it will create one.
  - Press OK to create and open the project.

![](https://user-images.githubusercontent.com/22556115/87254708-4ea77a00-c439-11ea-9a52-22d972f971f2.png)

- If you wish to edit build.gradle template, go to Settings in Android Studio > Tools > ASAP: Android system app plugin 

![](https://user-images.githubusercontent.com/22556115/87254709-4f401080-c439-11ea-82a3-e7034527596e.png)
## Build
You can build or change the plugin by Android Studio. 
- If you want the plugin to lauch with Android studio instead of Intellij, edit alternativeIdePath in [build.gradle](https://github.com/Alwin-Lin/Android_System_App_Plugin/blob/master/build.gradle#L32) to your IDE path
- The plugin jar is under \android_sys_app_plugin\build\libs
