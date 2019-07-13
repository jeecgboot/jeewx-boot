Jeewx-Boot  免费微信平台
==========
Jeewx-Boot，是一款开源免费的微信开发平台，是jeewx的新一代产品。功能涵盖：微信公众号管理、微信活动、小程序官网。技术架构采用SpinrgBoot2.x+Mybatis+Velicity，插件式开发模块化、支持更高的并发大数据；

当前最新版本： 1.0（发布日期：20190715）



	  
	  
平台功能
-----------------------------------

【微信公众号】
*   1、微信公众号管理
*   2、微信自定义菜单
*   3、关注欢迎语
*   4、未识别回复语
*   5、关键字管理
*   6、菜单支持小程序链接
*   7、文本素材管理
*   8、图文素材管理
*   9、强大图文编辑器
*   10、粉丝管理
*   11、同步粉丝功能
*   12、粉丝打标签功能
*   14、接受微信消息
*   14、回复粉丝消息
*   15、Oauth2.0链接

【系统管理】
*   1、系统用户管理
*   2、系统角色管理
*   3、系统菜单管理
*   4、项目管理（活动插件）
	
【微信活动】
*   1、九宫格
*   2、摇一摇
*   3、微信砍价


技术文档
-----------------------------------

* 在线文档：http://jeewx-boot.mydoc.io
* 小程序开发文档： http://shop.jeewx.com/#/doc/rumen
* 技术论坛 ：[www.jeecg.org](http://www.jeecg.org)
* 技术QQ群 : 97460170





项目说明
-----------------------------------

- 1.系统用户管理   	 jeewx-boot-base-system（含项目启动类）
- 2.CMS模块	         jeewx-boot-module-cms
- 3.微信管家模块     jeewx-boot-module-weixin
	  
	  
	  
体验二维码
-----------------------------------
![github](https://static.oschina.net/uploads/img/201907/13101120_zUgL.jpg "jeewx521")
![github](https://static.oschina.net/uploads/img/201907/13100959_naiO.jpg "jeewx521")

	  	  
	  
技术架构
-----------------------------------

- 1.采用SpringBoot2.1.3 + Mybatis + Velocity 框架技术
- 2.启动项目，执行下面启动类
	  jeewx-boot-base-system/src/main/java/com/jeecg/JeewxBootApplication.java
- 3.页面采用模板语言Velocity
- 4.插件式开发，每个模块独立打成jar
- 5.数据库、redis配置文件
	  jeewx-boot-base-system/src/main/resources/application.yml
- 6.微信域名配置
	  jeewx-boot-base-system/src/main/resources/jeewx.properties
	 




系统效果
----

##### 后台
![github](https://static.oschina.net/uploads/img/201808/13105211_M0FW.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/13105211_AVY4.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11172049_s7hH.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11153109_73Aj.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11221430_KZ1b.png "jeecg")

##### 活动
![github](http://www.jeecg.org/data/attachment/forum/201601/25/180710anjfgtn677nojgg0.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/13105211_lMFh.jpg "jeecg")
![github](http://www.jeecg.org/data/attachment/forum/201601/25/180500iwpg1agqm778wggp.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11195358_bi9e.png "jeecg")

##### 小程序