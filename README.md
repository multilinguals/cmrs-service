# cmrs-service

#### 介绍
公司点餐系统服务端项目。

#### 软件架构
以领域驱动（DDD）和CQRS作为架构指导思想。采用[Axon Framework](https://github.com/AxonFramework/AxonFramework)作为基础框架，与Spring Boot整合。
数据库选择MongoDB。

#### 安装教程

1. JDK >= 1.8，< 1.9
2. Maven 3.0+
3. MongoDB 4.0+

#### 代码结构说明
```
org.multilinguals.enterprise.cmrs
├── command  //命令层：接受外部命令，对聚合的变化进行处理
|   ├── aggregate //聚合：对应领域驱动设计的聚合
|   ├── handler //处理器：当一个业务指令需要横跨多个聚合时，需要在这里进行操作
|   └── saga //事件事务
├── constant //常量
├── dto //接口层返回结果用到的数据结果
├── infrastructure //基础层：进行通用的，对业务透明的配置
|   ├── config //对Spring和Axon框架的基础配置
|   ├── data //自定义的数据结构
|   ├── dto //DTO相关的抽象设计
|   ├── exception //自定义异常
|   ├── persistence //持久层的抽象设计
|   └── security //安全相关的通用处理
├── interfaces
|   ├── perprocess //当应用启动时，需要进行的预处理工作
|   ├── command //接受命令的接口
|   └── query //查询接口
├── query //查询层：响应命令层的事件，聚合视图，支持客户端视图的查看
└──server //服务启动层
```