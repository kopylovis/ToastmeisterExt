# Toastmeister Extension
[![](https://jitpack.io/v/kopylovis/toastmeister.svg)](https://jitpack.io/#kopylovis/toastmeister)

## Installation:
### Step 1:
Android Gradle plugin (AGP) lower than 7.0 - add it in your root build.gradle at the end of repositories:
```
allprojects {
   repositories {
     ...
     maven { url 'https://jitpack.io' }
    }
}
```
Android Gradle plugin (AGP) 7.0 and higher add it in your root gradle.settings at the end of repositories:
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```
### Step 2:
```
dependencies {
    implementation 'com.github.kopylovis:toastmeister:1.0'
}
```
