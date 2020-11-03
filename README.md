
Jeewx-Boot 免费微信管家平台
==========

当前最新版本： 1.3（发布日期：20200916）

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/zhangdaiscott/jeewx-boot/blob/master/LICENSE)
[![](https://img.shields.io/badge/Author-JEECG团队-orange.svg)](http://www.jeewx.com)
[![](https://img.shields.io/badge/version-1.3-brightgreen.svg)](https://gitee.com/jeecg/jeewx-boot)



项目介绍
-----------------------------------
JeewxBoot是一款免费的JAVA微信管家平台，支持微信公众号、小程序、微信第三方平台、抽奖活动等。JeewxBoot已经实现了系统权限管理、公众号管理、抽奖活动等基础功能，便于二次开发，可以快速搭建微信应用！

技术架构：SpringBoot2.1.3 + Mybatis + Velocity；
采用插件开发机制，实现一个抽奖活动一个独立插件（对JAVA来讲就是一个JAR包），可以很方便的做插拔，提供丰富的活动插件下载。。






技术文档
-----------------------------------

* 入门必读：http://doc.jeewx.com/1414959
* QQ交流群 : 97460170
* 技术官网 ：[www.jeewx.com](http://www.jeewx.com)
* 演示地址 ：[http://demo.jeewx.com](http://demo.jeewx.com) 【测试账号： jeewx/123456】
* 视频教程 ：[JeewxBoot入门视频教程](https://www.bilibili.com/video/av62847704)
* 常见问题：[入门常见问题汇总](http://bbs.jeecg.com/forum.php?mod=viewthread&tid=8185&extra=page%3D1)


项目说明
-----------------------------------

#### 基础平台项目

| 项目名   |      中文名      |  备注 |
|----------|:-------------:|------:|
| jeewx-boot-start |  启动项目 |  |
| jeewx-boot-base-system |  系统用户管理模块 |  |
| jeewx-boot-module-weixin | 微信公众号管理 |     |	 
| jeewx-boot-module-api | 共通业务API接口 |     |	
| huodong/jeewx-boot-module-goldenegg |    砸金蛋活动   |    |
| huodong/jeewx-boot-module-cms |   小程序官网   |  [集成文档](http://doc.jeewx.com/1767423)  |


#### 插件项目说明

* 应用商店(免费插件)： [http://cloud.jeecg.com](http://cloud.jeecg.com/?categoryId=1291328642663645186)


| 项目名   |      中文名      |  备注 
|----------|:-------------:|------:|
| jeewx-boot-module-commonluckymoney |    圣诞拆红包抽奖   |       
| jeewx-boot-module-scratchcards |    刮刮乐活动   |    
| jeewx-module-divination |    摇签祈福活动   |    
| P3-Biz-shaketicket |    摇一摇活动   |    
| jeewx-boot-module-luckyroulette |    新版大转盘活动   | 



#### 小程序源码

* 小程序官网 ：https://gitee.com/jeecg/jeewx-app-cms


	  
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




系统效果
----



##### 系统截图
![](https://oscimg.oschina.net/oscnet/up-cfcc44a9ad6cc52a5e4dd2a19d1cd775d55.png)
![](https://oscimg.oschina.net/oscnet/up-697c944f14c0d16a9bce405e1369ab27088.png)


![](https://oscimg.oschina.net/oscnet/up-e77abee0fcbc6b1216e987b9721f7c497e8.png)
![](https://oscimg.oschina.net/oscnet/up-83fcf83848071fa7499bdb8792358aec355.png)
![](https://oscimg.oschina.net/oscnet/up-77d779e14210120766c256b5c7af768ec8a.png)

![](https://images.gitee.com/uploads/images/2019/0715/140426_f26f4ebf_57093.jpeg)
![](https://oscimg.oschina.net/oscnet/up-26a8ad222460e46515e572e9f73134df8b1.png)
![](https://oscimg.oschina.net/oscnet/up-b13041a3f8ef35e5cc5d528a1f2dfe1a5bd.png)
![](https://oscimg.oschina.net/oscnet/up-ec65fa68786246deda14a2020fc81d54e5d.png)




##### 体验二维码
![github](https://static.oschina.net/uploads/img/201907/13100959_naiO.jpg "jeewx521")
