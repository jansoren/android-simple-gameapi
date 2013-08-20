# Android Simple Game API

This distribution contains:
## android-simple-gameapi
   - for getting you startet developing android games.
   - use maven to build, or download the jar file listed above.
   - use gradle for setting up project in Android Studio
   
   **Features**
     - Screenmanagement
     - Bitmaphandling (Sprite, buttons and text)
     - Soundmanagement
	 - Physics (Box2d for 2D rigid body simulation)
	 - Bitmapcaching
 
## android-simple-gameapi-example
   - simple examples of the usage of android-simple-gameapi

## Games using the API on Google Play:
   - Reversi Paper Blobs Free 	https://play.google.com/store/apps/details?id=com.machinegames.othello
   - Reversi Paper Blobs 		https://play.google.com/store/apps/details?id=com.machinegames.othello.paid
   - Raindrop 					https://play.google.com/store/apps/details?id=com.arconegames.raindrop
   
# How to get started using example

## Get the code
1. Create folder `c:\git` and enter it
1. Pull the code from github `git clone https://github.com/jansoren/android-simple-gameapi.git`
2. Enter folder `c:\git\android-simple-gameapi`
3. Select the master branch `git checkout master` 

## Setup using Android Studio
1. Download Android Studio http://developer.android.com/sdk/installing/studio.html
2. Import as Gradle project
3. Done :)

## Setup using Eclipse
1. Enter folder `c:\git\android-simple-gameapi\android-simple-gameapi`
2. Use maven to build code `mvn clean install`
3. Prepare for eclipse `mvn eclipse:eclipse`
4. In eclipse, import using `Git -> Projects from Git`
5. Select your `c:\git\android-simple-gameapi` folder -> Next
6. Select `Import existing projects` -> Next
7. The `android-simple-gameapi` - project should be selected -> Finish

## Setup example using Eclipse
1. Download Gradle `http://www.gradle.org/get-started` and last Android SDK version `http://developer.android.com/sdk/index.html`
2. Set environment variable `GRADLE_HOME=C:\...\gradle-1.6` and `ANDROID_HOME=C:\...\android-sdk`
3. Set/dobbelcheck path variable `PATH=...;%GRADLE_HOME%\bin;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools`
4. Enter folder `c:\git\android-simple-gameapi\android-simple-gameapi-example`
5. Build project `gradle build`
6. // TODO: waiting for a way to build eclipse project files using gradle

# Alternative method for implementing project 
1. Import using `Git -> Projects from Git`
2. Select your `c:\git\android-simple-gameapi` folder -> Next
3. Select `Use the New Project wizard` -> Finish
4. Select `Android Project from Existing Code` -> Next
5. Set Root Directory to the android example code `c:\git\android-simple-gameapi\android-simple-gameapi-example` -> Finish
6. Enter the projects build path in the `Projects`-tab and add `android-simple-gameapi`

You are now ready for developing android games!

# Donate
Please appreciate and recognize with donating or staring this project :)

[![Flattr Button](http://api.flattr.com/button/button-static-50x60.png "Flattr This!")](http://flattr.com/thing/1833285/jansorenandroid-simple-gameapi-on-GitHub "Arcone")
