
Jeewx-Boot 免费微信管家平台
==========

当前最新版本： 1.1.0（发布日期：20191205）

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/zhangdaiscott/jeewx-boot/blob/master/LICENSE)
[![](https://img.shields.io/badge/Author-JEECG团队-orange.svg)](http://www.jeewx.com)
[![](https://img.shields.io/badge/version-1.1-brightgreen.svg)](https://gitee.com/jeecg/jeewx-boot)



项目介绍
-----------------------------------
JeewxBoot是一款基于SpringBoot的开源微信管家系统，采用SpringBoot2.1.3 + Mybatis + Velocity 框架技术。支持微信公众号、微信小程序、微信第三方平台（扫描授权公众号）、抽奖活动等。JeewxBoot实现了系统权限管理、公众号管理、抽奖活动等基础功能，便于二次开发，可以快速搭建微信应用！
Jeewx-Boot采用插件开发机制，实现一个抽奖活动项目是一个独立的插件（对JAVA来讲就是一个JAR包），可以很方便的做插拔，最终打造像Discuz一样的插件生态圈。。


	  
功能清单
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
│  └─微信第三方平台
│  └─支持扫描授权公众号
│  ├─素材管理
│  │  ├─文本素材
│  │  ├─图文素材
│  │  ├─超强图文编辑器
│  │  ├─图文预览功能
│  ├─用户管理
│  │  ├─粉丝管理
│  │  ├─粉丝标签管理
│  │  ├─图文编辑器
│  │  ├─接受消息管理
│  │  ├─粉丝消息回复
│  │  ├─图文预览功能
│  ├─高级功能
│  │  ├─微信群发功能
│  │  ├─群发审核功能
│  │  ├─二维码管理
├─微信抽奖活动
│  ├─砸金蛋
│  ├─小程序官网（CMS模块）
│  ├─摇一摇（尚未开源）
│  ├─微信砍价（尚未开源）
│  ├─更多商业活动
├─高级功能（尚未开源）
│  ├─小程序商城
│  ├─竞选投票
│  ├─分销商城
│  ├─团购商城
│  ├─红包活动
│  ├─更多商业功能
│  ├─。。。
```




技术文档
-----------------------------------

* 入门必读：http://doc.jeewx.com/1414959
* QQ交流群 : 97460170
* 技术官网 ：[www.jeewx.com](http://www.jeewx.com)
* 演示地址 ：[http://demo.jeewx.com](http://demo.jeewx.com) 【测试账号： jeewx/123456】
* 视频教程 ：[JeewxBoot入门视频教程](https://www.bilibili.com/video/av62847704)
* 常见问题贴：[开发日常问题汇总](http://bbs.jeecg.com/forum.php?mod=viewthread&tid=8185&extra=page%3D1)


项目说明
-----------------------------------

| 项目名   |      中文名      |  备注 |
|----------|:-------------:|------:|
| jeewx-boot-start |  启动项目 |  |
| jeewx-boot-base-system |  系统用户管理模块 |  |
| jeewx-boot-module-weixin | 微信公众号管理 |     |	 
| jeewx-boot-module-api | 共通业务API接口 |     |	
| huodong/jeewx-boot-module-goldenegg |    砸金蛋活动   |    |
| huodong/jeewx-boot-module-cms |   小程序官网   |  [集成文档](http://doc.jeewx.com/1767423)  |


小程序端源码
-----------------------------------

* 小程序官网 ：https://gitee.com/jeecg/jeewx-app-cms
* 小程序商城(暂未开放) ：https://gitee.com/jeecg/weixin-app-shop




系统效果
----

##### 体验二维码
![github](https://static.oschina.net/uploads/img/201907/13101120_zUgL.jpg "jeewx521")
![github](https://static.oschina.net/uploads/img/201907/13100959_naiO.jpg "jeewx521")

##### PC端
![输入图片说明](https://static.oschina.net/uploads/img/201907/15141922_GIP8.png "在这里输入图片标题")
![github](https://static.oschina.net/uploads/img/201808/11172049_s7hH.png "jeecg")
![github](https://static.oschina.net/uploads/img/201808/11153109_73Aj.png "jeecg")
![输入图片说明](https://static.oschina.net/uploads/img/201807/26192231_JVRQ.png "在这里输入图片标题")


##### 手机端
![github](https://static.oschina.net/uploads/img/201808/11195358_bi9e.png "jeecg")




