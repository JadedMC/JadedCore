JadedCore
===========

Server essentials for the JadedMC Minecraft server.

What's Inside
------
- Rank Management
- Staff Tools
- Database Management
- Utility commands
- JadedUtils library

Requirements
------
- Paper API
- Java 17
- Minecraft 1.20.1

Maven
------
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupID>com.github.JadedMC</groupID>
    <artifactId>JadedCore</artifactID>
    <version>master-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

Gradle
------
```kotlin
allprojects { 
    repositories { 
        maven { url 'https://jitpack.io' }
    }
}
```

```kotlin
dependencies {
    implementation 'com.github.JadedMC:JadedCore:master-SNAPSHOT'
}
```