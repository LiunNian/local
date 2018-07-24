# cpccacloud

#### 项目介绍

该项目是中国文化馆协会系统

#### 软件架构
|-eureka—注册中心

|	       |-eurekaserver

|

|-zuul—分发

|           |-zuulserver

|

|-feign—负载均衡

|            |-feignserver

|

|-server—服务层

|		|-paper-common 公共包	

|              |-paperserver     论文系统

|	        |-  paper-train    培训报名(现只有预报名)    依赖于公共包


#### 安装教程

1. 安装 maven
2. mvn package

#### 使用说明

1. 数据库文档与需求文档在doc
