# Isme Java Serve

![Static Badge](https://img.shields.io/badge/Version-0.0.1--SNAPSHOT-blue)
![Static Badge](https://img.shields.io/badge/Spring_Boot-3.1.6-blue)
![Static Badge](https://img.shields.io/badge/Jdk-21-blue)

## 简介

本项目是 [Vue Naive Admin 2.0](https://github.com/zclzone/vue-naive-admin) 的一个轻量级的Java后端服务。最小启动要求不需要第三方中间件，兼容

H2、Mysql两种数据库，方便开发调试。基于SpringBoot、MybatisPlus、SaToken、MapStruct等实现。

基于[zclzone/isme-nest-serve](https://github.com/zclzone/isme-nest-serve)提供的接口，目前已完成99%的接口，存在不兼容等问题麻烦通过issue反馈

## 开发

### 环境要求

- JDK 21 推荐：[Liberica JDK](https://bell-sw.com/pages/downloads/#jdk-21-lts)

### 代码风格

本项目在编译强制校验代码风格，checkstyle采用google代码规范，在idea开发时推荐安装[checkstyle-idea](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea)
插件

### 项目结构

```
.
├── java
     └── cn.dhbin.isme
         ├── common 通用类包
             ├── auth   鉴权相关
             ├── exception  异常处理
             ├── mapstruct  mapstruct配置
             ├── mybatis    mybatis配置
             ├── preview    预览功能实现
             ├── request    http请求相关
             └── response   http响应相关
         └── pms    系统权限模块
             ├── controller
             ├── convert    mapstrcut定义
             ├── domain     bean定义
                 ├── dto        http响应bean
                 ├── entity     数据库映射bean
                 └── request    http请求bean
             ├── mapper     mybatis mapper
             ├── service    Service层
                 └── impl
             └── util       工具类
└── resources
    ├── db
    └── mapper
        └── pms

```

### 启动

最小配置默认采用h2数据库内存模式，直接运行`AdminServeApplication`即可

- h2数据库修改成文件模式
    - `src/main/resources/application-h2.yml`修改`spring.datasource.url`为
      `jdbc:h2:file:/~/.isme/db;MODE=MySQL;DATABASE_TO_LOWER=TRUE`，启动将会在用户文件夹`.isme`生成数据库文件

- 使用mysql数据库，切换`spring.profiles.active`为`mysql`，数据库初始化文件[init.sql](https://github.com/zclzone/isme-nest-serve/blob/main/init.sql)

- 新建模块
    - 在`cn.dhbin.isme`包下创建你需要的模块开发

- Jwt鉴权
    - Jwt鉴权是通过SaToken提供的能力实现的，默认是无状态模式，需要采用其它模式参考SaToken的官方文档

## LICENSE

```
MIT License

Copyright (c) 2023 dhb(xx158@qq.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```