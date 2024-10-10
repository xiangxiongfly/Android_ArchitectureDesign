[TOC]

# Android 架构设计

## 官网文档

https://developer.android.com/jetpack/guide?hl=zh-cn#common-principles

## MVC 架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/89a7d1a688fd40fca75122484e58fce8.png)

MVC 是 Android 默认的设计，主要将代码分为三个部分：
| 角色 | 说明 |
| -------------------- | ------------------------------------------------------------ |
| Model（模型） | 负责请求数据和处理数据，将处理后的数据发送给 View 和 Controller。 |
| View（视图） | 负责界面展示，对应XML布局和View。视图不处理任何业务逻辑，只负责显示数据并接收用户的输入。 |
| Controller（控制器） |
负责业务逻辑处理和UI相互，对应Activity和Fragment。控制器是模型和视图之间的协调者。它处理用户在视图上的交互（例如点击按钮），并更新模型以反映这些交互。然后，它更新视图以反映模型的新状态。
|

**代码结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/aff68e401d194e73ae64fc81908ce8bd.png)

## MVP 架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/9e2e25ab6a474aafb1a14ae3177ba078.png)

| 角色          | 说明                                           |
| ------------- | ---------------------------------------------- |
| Model（模型） | 负责请求数据和处理数据，不包含任何UI信息。     |
| View（视图）  | 负责显示数据和UI交互，通常是Activity和Fragment |
| Presenter     | Presenter是Model和View之间的桥梁，负责业务逻辑 |

**代码结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/43981a24bd9d42bbabce1c75dcaa8208.png)

### MVP+RxJava+Retrofit

**代码结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/b8436d1572d84ce28beaf3410ebc1109.png)

## MVVM 架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210518115543344.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE0ODc2MTMz,size_16,color_FFFFFF,t_70)

| 角色                  | 说明                                                         |
| --------------------- | ------------------------------------------------------------ |
| Model（模型）         | 负责获取数据和处理数据                                       |
| View（视图）          | 负责界面展示和用户交互，即Activity/Fragment                  |
| ViewModel（视图模型） | ViewModel是Model和View之间的桥梁，负责业务逻辑处理、数据驱动，通常由ViewModel+LiveData。从Model获取数据更新View，处理View的交互，ViewModel不会引用View |

**代码结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/f80736896dc24456accb31fdf5212408.png)

### Jetpack MVVM

**Jetpack MVVM**是 MVVM 模式在 Android 开发中的一个具体实现，是 Android 官方提供并推荐的 MVVM实现方式。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2021032723012254.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE0ODc2MTMz,size_16,color_FFFFFF,t_70)

**代码结构：**

![在这里插入图片描述](https://i-blog.csdnimg.cn/direct/aed836b93ce349d2a3fc20ff7d533a51.png)

### MVVM+coroutines+retrofit+flow

**代码结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/2b5b5ac487334c6885546ceeabf70f38.png)

### MVVM+coroutines+retrofit+flow+hilt

**代码结构：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/direct/d8d4daa69c2d4f29b91b7811b494a0ef.png)

