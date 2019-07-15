Jeewx-Boot  免费微信管家平台
==========
Jeewx-Boot，是一款开源免费的微信开发平台，是jeewx的新一代产品。功能涵盖：微信公众号管理、微信活动、小程序官网。技术架构采用SpinrgBoot2.x+Mybatis+Velicity，插件式开发模块化、支持更高的并发大数据；

当前最新版本： 1.0（发布日期：20190716）



技术文档
-----------------------------------

* QQ交流群 : 97460170
* 在线文档：http://jeewx-boot.mydoc.io
* 小程序文档： http://shop.jeewx.com/#/doc/rumen
* 技术论坛 ：[www.jeecg.org](http://www.jeecg.org)
	

项目说明
-----------------------------------

| 项目名   |      中文名      |  备注 |
|----------|:-------------:|------:|
| jeewx-boot-base-system |  系统用户管理 | 含项目启动类 |
| jeewx-boot-module-cms |    CMS管理后台   |    |
| jeewx-boot-module-weixin | 微信公众号管理 |     |	 
| [jeewx-app-cms](https://github.com/zhangdaiscott/jeewx-app-cms) | 小程序官网源码 |    采用wepy语言 | 

	  
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
├─微信抽奖活动（即将开源）
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




系统效果
----

##### PC端
![输入图片说明](https://static.oschina.net/uploads/img/201907/15141922_GIP8.png "在这里输入图片标题")
![github](https://static.oschina.net/uploads/img/201808/13105211_AVY4.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11172049_s7hH.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11153109_73Aj.png "jeecg")
![输入图片说明](https://static.oschina.net/uploads/img/201907/15144608_1zcZ.png "在这里输入图片标题")

##### 手机端

![github](https://static.oschina.net/uploads/img/201808/13105211_lMFh.jpg "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11195358_bi9e.png "jeecg")

##### 小程序端
![输入图片说明](https://static.oschina.net/uploads/img/201907/15143215_Km1G.jpg "在这里输入图片标题")


体验二维码
-----------------------------------
![github](https://static.oschina.net/uploads/img/201907/13101120_zUgL.jpg "jeewx521")
![github](https://static.oschina.net/uploads/img/201907/13100959_naiO.jpg "jeewx521")

