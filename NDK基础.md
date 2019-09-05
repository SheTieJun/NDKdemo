## NDK开发基础

[TOC]

#### 一、什么NDK,什么是JNI

JNI 全程`Java Native Interface` 本地开发接口

它是一种协议，一种用来沟通`Java`和外部的本地代码`（C/C++）`,使用这个协议我们就能随意的让`Java`代码调用`C/C++`

NDK (Nativie Development Kit) 本地开发的“工具包”



#### 二、CPU架构

- arm架构：主要是用于移动手持、嵌入式设备。
- X86架构：主要是台式机，笔记本。比如Intel和AMD的CPU
- MIPS架构：主要是用于网关、猫、机顶盒设备



#### 三、操作说明

##### 一.基础说明

- `-d`: 表示生产一个目录

- `../`:表示在当前目录的上一层目录

- `javah`:通过该命令生成Java类对应的头文件

  ```
  javah -d ../jni com.xxx.xxx.xxx.JNIUtils
  ```

- 头文件：C/C++里面中的用来保存程序说明，表示有什么方法

- `Android.mk`:Android的makefile文件

  ```
  LOCAL_PATH := $(call my-dir)
  include $(CLEAR_VARS)
  LOCAL_MODULE := native-lib
  LOCAL_SRC_FILES := jnitest.c
  include $(BUILD_SHARED_LIBRARY)
  APP_ABI:=all
  ```

- LOCAL_PATH:=$(call my-dir)

- 宏函数`my-dir`：由编译系统提供，返回当前Android.mk文件的目录路径

- 宏函数`CLEAR_VARS`:由编译系统提供，清除除LOCAL_PATH以外的LOCAL_XXX变量

- LOCAL_MODULE ：编译目标对象，名称必须是唯一，而且不包含任何空格，最后将生成`native-lib.so`

- LOCAL_SRC_FILES: 必须包含将要编译打包进模块的C/C++源码文件，只需要列出直接传递给编译器的源码文件。系统会自定帮你找出依赖文件

- 指定编译出的库类型

  - 1.BUILD_SHARED_LIBRARY：动态库；
  - 2.BUILD_STATIC_LIBRARY：静态库;
  - 3.BUILD_EXECUTEABLE指：可执行文件

- APP_ABI有四种类型（默认armeabi），armeabi、armeabi-v7a、x86、mips，设置时以空格隔开，all表示所有。

```
先.h文件和c++文件，接下来还需要在这个jni目录下增加两个文件，Android.mk和Application.mk。 
```

```
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := JNI_STATIC_ANDROID_TEST
LOCAL_SRC_FILES := jnistaticutils.cpp
include $(BUILD_SHARED_LIBRARY)
//配置多个module
include $(CLEAR_VARS)
LOCAL_MODULE := JNI_DYNAMIC_ANDROID_TEST
LOCAL_SRC_FILES := jnidynamicutils.cpp
include $(BUILD_SHARED_LIBRARY)

```

```
Application.mk中设置的APP_MODULES := JNI1，
要生成JNI2的so文件的时候会报错，除非写成APP_MODULES := JNI1 JNI2，这里我们直接省略默认使用Android.mk中的）。
```

##### 帮助操作

Settings–>Tools–>ExternalTools

```
javah -jni命令，是根据java文件生成.h头文件的，会自动根据java文件中的类名（包含包名）与方法名生成对应的C/C++里面的方法名。下面是参数配置及其含义： 

1. Program: $JDKPath$\bin\javah.exe 这里配置的是JDK目录下的javah.exe的路径。 
2. Parametes: -classpath . -jni -d $ModuleFileDir$/src/main/jni $FileClass$ 这里$FileClass$指的是要执行操作的类名（即我们操作的文件），$ModuleFileDir$/src/main/jni表示生成的文件保存在这个module目录的src/main/jni目录下。 
3. Working: $ModuleFileDir$\src\main\java module目录下的src\main\java目录（不是很理解）。 
   使用方式：选中java文件—>右键—>External Tools—>javah-jni，将生成jni文件夹以及文件夹下的 包名.类名的.h头文件 （名字过长，我们可以自己重命名）。
 
```

```

ndk -build命令，是根据C/C++文件生成so文件的。下面是参数配置及其含义： 
1. Program: F:\apk\sdk\ndk-bundle\ndk-build.cmd 这里配置的是ndk下的ndk-build.cmd的路径（根据实际情况填写）。 
2. Working: $ModuleFileDir$\src\main\ 
   使用方式：选中C/C++文件—>右键—>ExternalTools—>ndk-build，将在main文件夹下生成libs文件夹以及多个so文件，我们可以移动至jniLibs目录下去。

```

