# ModularPlugin

本插件目前主要用于二方库或三方库的源码依赖及调试。
能够在只进行较少配置的情况下实现源码替换maven依赖，从而进行源码调试。

## 插件引入方式

插件引入方式可参考ModularPluginTester的配置，下面会做一个简单的介绍，源码来自ModularPluginTester，

第一步，在根工程的settings.gradle.kts中应用"mp-settings"插件，代码如下

```kotlin
//...

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath("com.ywq.mp:ModularPlugin:1.0-SNAPSHOT")
    }
}

apply(plugin = "mp-settings")

//...
```

第二步，在根工程的build.gradle.kts中应用"mp-project"插件，代码如下

```kotlin
//...

apply(plugin = "mp-project")

//...
```

通过以上配置，我们就完成了ModularPlugin插件的引入，接下来我们介绍如何配置源码依赖

## 源码依赖配置方式

配置方式也可参考ModularPluginTester中的，下面我们对配置参数做一个简单的介绍

第一步，在根工程创建一个名为"modular.yml"的配置文件

第二步，在"modular.yml"中填写源码依赖配置，代码如下

```yaml

# global switch, whether to turn on debug
debug: true
# modules to debug
moduleList:
  # switch, whether to turn on debug
  - debug: false
    # match artifact in maven
    name: rxjava
    # match group in maven
    group: io.reactivex.rxjava3
    # match version in maven, If set, it will only work for the specified version.
    version:
    # the path of the source code. Attention, you must download(clone) the source code into the path by yourself.
    path: ../../rxjava


```

上面的配置代表我们将用路径为"../../rxjava"处的源码替换rxjava的maven依赖，从而进行源码调试