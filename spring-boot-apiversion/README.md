# 基于spring-boot项目的版本管理


### 需求

大家在做后台开发给客户端端定义接口过程中我相信应该都有遇到接口版本的问题，在业务初期的时候可能需要快速上线定义了一套接口假设为V1，可是随着业务的发展迭代可能版本也会对应的发展到V2
甚至V3,V4等，那我们一般有如下几种方式：

- 请求头中带上对应接口版本信息
- 请求路径中带上版本信息如：v1/users/ID
- 接口参数中带上版本信息如：url?v=1.2

这边我们主要介绍第二种使用方式

### 基本原理

具体代码这边就不作详细介绍，原理大概是首先定义一个自定义注解，然后通过自定义类继承springmvc中的RequestMappingHandlerMapping替换掉请求中的版本相关信息

### 演示

项目开始定义了两个接口分别是：
- http://localhost:8081/v1/hello?msg=test
- http://localhost:8081/v1/other?msg=other

随着项目迭代hello接口需要做一些调整，但是other接口业务却没有变化，那这个时候客户端又不想维护两套版本，这个时候我们访问：
- http://localhost:8081/v2/hello?msg=test
- http://localhost:8081/v2/other?msg=other

运行之后可以发现v2/hello正确的映射到了v2版本，v2/other没有对应的版本会自动向下兼容使用到v1/other

具体代码可以[点击查看](https://github.com/zhiliao666/spring-boot-demo/blob/master/spring-boot-apiversion/src/main/java/com/zhiliao/api/ControllerTest.java)

