Jeewx-Boot  免费微信管家平台
==========

当前最新版本： 1.0.3（发布日期：20190801）

项目介绍
-----------------------------------
Jeewx-Boot是一款开源免费的微信管家平台。支持微信公众号、微信第三方平台、小程序官网、小程序商城、微信抽奖活动等。Jeewx-Boot实现了微信公众号管理、小程序CMS、微信抽奖活动等基础功能，便于二次开发，可以快速搭建微信应用！Jeewx-Boot独创插件开发机制，实现了每一抽奖活动（砸金蛋、刮刮乐、砍价等）、官网、商城都是一个独立的插件，对JAVA来讲就是一个JAR包，可以很方便的做插拔，最终打造像Discuz、微擎一样的插件生态圈。。

### Jeewx-Boot诞生的目的
-   Jeewx开源至今已经6年时间，积累无数粉丝，但老版技术陈旧，随着功能增多项目也变的臃肿。之所以打造一款全新的产品Jeewx-Boot，最终目的是打造一个全新的微信开发生态圈。Jeewx-Boot独创插件开发机制，实现了每一抽奖活动（砸金蛋、刮刮乐、砍价等）、官网、商城、小程序都是一个独立的插件，对JAVA来讲就是一个JAR包，可以很方便的做插拔，打造像discuzz、微擎一样的插件生态机制；
 -  插件机制的好处在于，你可以灵活的选择你需要的插件，组装成你要的系统，就像搭建积木一样；当然如果你不需要那个功能，可以很轻松的拔掉；
 -  Jeewx-Boot采用最新主流技术SpinrgBoot2.x + Mybatis + Velocity，是大家熟悉的技术，学习成本低， 新的平台实现了插件开发机制，同时也支持更高的并发、更高的大数据能力；
 

技术文档
-----------------------------------

* QQ交流群 : 97460170
* 反馈问题 ：[请发issue](https://github.com/zhangdaiscott/jeewx-boot/issues/new)
* 开发文档：http://jeewx-boot.mydoc.io
* 技术论坛 ：[www.jeecg.org](http://www.jeecg.org)
* 小程序文档： http://shop.jeewx.com/#/doc/rumen


基础项目说明
-----------------------------------

| 项目名   |      中文名      |  备注 |
|----------|:-------------:|------:|
| jeewx-boot-base-system |  系统用户管理 | 含项目启动类 |
| jeewx-boot-module-cms |    CMS管理后台   |    |
| jeewx-boot-module-weixin | 微信公众号管理 |     |	 
| jeewx-boot-module-api | 共通业务API接口 |     |	
| [jeewx-app-cms](https://github.com/zhangdaiscott/jeewx-app-cms) | 小程序官网源码 |    采用wepy语言 | 


独立插件项目（插件项目在目录huodong下）
-----------------------------------

| 项目名   |      中文名      |  备注 |
|----------|:-------------:|------:|
| jeewx-boot-module-goldenegg | 砸金蛋活动 |  独立数据库脚步   |	



	  
系统模块
-----------------------------------

```
├─系统管理
│  ├─用户管理
│  ├─角色管理
│  ├─菜单管理
│  └─首页设置
│  └─项目管理（插件）
├─公众号运营
│  ├─基础配置
│  │  ├─公众号管理
│  │  ├─关注欢迎语
│  │  ├─未识别回复语
│  │  ├─关键字设置
│  │  ├─自定义菜单
│  │  ├─菜单支持小程序链接
│  │  ├─Oauth2.0链接机制
│  ├─素材管理
│  │  ├─文本素材
│  │  ├─图文素材
│  │  ├─超强图文编辑器
│  │  ├─图文预览功能
│  ├─用户管理
│  │  ├─粉丝管理
│  │  ├─粉丝标签管理
│  │  ├─超强图文编辑器
│  │  ├─接受消息管理
│  │  ├─粉丝消息回复
├─小程序官网
│  ├─站点管理
│  ├─广告管理
│  ├─栏目管理
│  ├─文章管理
│  ├─后台管理代码
│  ├─小程序前端代码
├─微信抽奖活动
│  ├─砸金蛋
│  ├─摇一摇
│  ├─微信砍价
├─高级功能（尚未开源）
│  ├─小程序商城
│  ├─微信投票
│  ├─分销商城
│  ├─。。。
└─其他插件
   └─更多功能陆续开源。。
```






	  	  
	  
技术架构
-----------------------------------

- 1.采用SpringBoot2.1.3 + Mybatis + Velocity 框架技术
- 2.启动项目，执行下面启动类
```
  jeewx-boot-base-system/src/main/java/com/jeecg/JeewxBootApplication.java
  登录账号密码：   admin\123456
```
- 3.页面采用模板语言Velocity
- 4.插件式开发，每个模块独立打成jar
- 5.数据库、redis配置文件
```
  jeewx-boot-base-system/src/main/resources/application.yml
```
- 6.微信域名配置
```
  jeewx-boot-base-system/src/main/resources/jeewx.properties
```
- 7.maven依赖下载失败，请配置镜像

找到 maven老家 conf/settings.xml
在<mirrors>标签内增加下面方式的阿里云maven镜像（删除自己的镜像配置）， 最终结果见下面：
```
<mirrors>
       <mirror>
            <id>nexus-aliyun</id>
            <mirrorOf>*,!jeecg,!jeecg-snapshots</mirrorOf>
            <name>Nexus aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
        </mirror> 
 </mirrors>
```



系统效果
----

##### PC端
![输入图片说明](https://static.oschina.net/uploads/img/201907/15141922_GIP8.png "在这里输入图片标题")
![github](https://static.oschina.net/uploads/img/201808/13105211_AVY4.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11172049_s7hH.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11153109_73Aj.png "jeecg")
![输入图片说明](https://static.oschina.net/uploads/img/201907/15144608_1zcZ.png "在这里输入图片标题")


##### 小程序端
![输入图片说明](https://static.oschina.net/uploads/img/201907/15143215_Km1G.jpg "在这里输入图片标题")


##### 手机端

![github](https://static.oschina.net/uploads/img/201808/13105211_lMFh.jpg "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11195358_bi9e.png "jeecg")




体验二维码
-----------------------------------
![github](https://static.oschina.net/uploads/img/201907/13101120_zUgL.jpg "jeewx521")
![github](https://static.oschina.net/uploads/img/201907/13100959_naiO.jpg "jeewx521")

