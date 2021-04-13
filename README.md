## HBAPlugin简介
一个针对于Android App和Library项目构建的Gradle公共插件，目的是
- 避免多个Project或者多个Module 的Gradle构建脚本（build.gradle）中存在重复的构建模块内容
- 通过HBAPlugin拓展参数（Extension）对公共参数进行修改，例如android versionCode versionName
- 添加项目公共构建逻辑的Task，便于项目的构建维护管理

## HBAPlugin使用说明
#### 1. 在project的build.gradle文件中添加
```groovy
buildscript {
  repositories {
         maven { url "http://maven.championai.com/repository/maven-android-public/"}
  }
  dependencies {
         classpath "com.hisoybean.android.plugin:HBAPlugin:1.0.1"
  }
}
```
#### 2. 在app或者library module中build.gradle文件中添加
```groovy
plugins {
    id 'com.hisoybean.android.plugin.HBAPlugin'
}

//目前可用参数,后续会根据需要修改添加
HBAConfig {
    versionCode 1 //设置android versionCode
    versionName "1.0.0-SNAPSHOT" //设置android versionName
    syncWithCleanGradleCache true // 修改gradle缓存时间为0秒，使SNAPSHOT变化模块立即生效
    mavenPublish true //是否需要mavenPublish插件，用于需要上传maven的模块
}
```
##### 在拥有同样构建逻辑内容的情况下，对比对应module build.gradle内容 
未使用HBAPlugin：
```groovy
plugins {
    id 'com.android.library'
    id 'kotlin-android'
}

android {
    compileSdkVersion 29

    defaultConfig {
        minSdkVersion 19
        targetSdkVersion 29
        versionCode 10
        versionName "3.0.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles "consumer-rules.pro"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
}
```

使用HBAPlugin：
```groovy
plugins {
    id 'com.android.library'
    id 'com.hisoybean.android.plugin.HBAPlugin'
}

HBAConfig {
    versionCode 10
    versionName "3.0.0"
    syncWithCleanGradleCache true
    mavenPublish true
}

android {
    ... //这里也可以自行添加修改对应参数
}

dependencies {
    implementation "com.google.code.gson:gson:2.8.6"
}
```

## 目前HBAPlugin内容
- 添加公共插件，例如kotlin
- 添加Android公共基础配置，例如compileSdkVersion，minSdkVersion，targetSdkVersion等
- 添加公共依赖，例如kotlin-stdlib，androidx-core-ktx,androidx-appcompat等
#### 待更新内容：
- signingConfig签名配置
- buildType配置
- 多渠道版本配置
- 构建公共逻辑Task
- library module maven自动发布逻辑






