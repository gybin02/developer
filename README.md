# Developer
开发者功能，
- 所有页面简单初始化后，都可以调用开发者页面功能；
- 同时每个页面，也可以配置自己的测试功能；
- 集成了二维码扫描；
- 应用清除缓存； 退出应用；
### Relation ;
-  Rocket http://git.meiyou.im/Android/rocket
-  QrCode  http://git.meiyou.im/Android/QrcodeMain
- 开发者页面 http://git.meiyou.im/Android/Android/wikis/%E5%BC%80%E5%8F%91%E8%80%85%E9%A1%B5%E9%9D%A2
### build 

```groovy
//编译
compile 'com.meiyou.framework:developer:1.0.0-SNAPSHOT'
// 经期 只在测试，预发阶段引入；
//compile 'com.meiyou.framework:developer-noop:1.0.0-SNAPSHOT'
```

### 使用
参考 Rocket使用