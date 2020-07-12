# Android_System_App_Plugin
## Intorduction
The plugin generates build.gradle after user inputs their project source and where they want the gradle to be, then opens up the generated gradle file as a project.
## Problems
For each new Android project you need to create and edit build.gradle
## Install
1. Download from release or clone and build the project.
   * The built can be found under \android_sys_app_plugin\build\libs
   * Why build it yourself? 
      * In build.gradle there is alternativeIdePath, it lets you choose which IDE to lauch it from
2. Open Intellij/Android Studio > Help > Find action > "Install plugin from disk"
3. Chose the plugin jar file
## Using plugin
- The plugin can be found next to Help button
  - Source dir is where your AndroidManifest.xml and source code is located
  - Project dir is where you wish the project to be created, if the folder you enter does not exsit, it will create one.

![](https://user-images.githubusercontent.com/22556115/87254708-4ea77a00-c439-11ea-9a52-22d972f971f2.png)

- If you wish to edit build.gradle template, go to Settings in Intellij/Android Studio > Tools

![](https://user-images.githubusercontent.com/22556115/87254709-4f401080-c439-11ea-82a3-e7034527596e.png)
