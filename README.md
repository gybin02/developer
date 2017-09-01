# Developer
开发者功能，
- 所有页面简单初始化后，都可以调用开发者页面功能；
- 同时每个页面，也可以配置自己的测试功能；
- 集成了二维码扫描；
- 应用清除缓存；
- 卸载应用；
- 使用帮助

### Relation ;
-  Rocket http://git.meiyou.im/Android/rocket
-  QrCode  http://git.meiyou.im/Android/QrcodeMain
- 开发者页面 http://git.meiyou.im/Android/Android/wikis/%E5%BC%80%E5%8F%91%E8%80%85%E9%A1%B5%E9%9D%A2

![截图](/images/device1.png)
### build 

```groovy
//编译
compile 'com.meiyou.framework:developer:1.0.0-SNAPSHOT'
// 经期 只在测试，预发阶段引入；
//compile 'com.meiyou.framework:developer-noop:1.0.0-SNAPSHOT'
```

### 使用

#### Activity 配置，启动悬浮窗 

你可以初始化在 具体的 activity里面 或者在BaseActivity,这样每个页面都可以用；

```java
@Override protected void onCreate(Bundle savedInstanceState) {
    ...

    Rocket.init(this)
      .inject(AppRocketConfig.class);  //required
}
```

AppRocketConfig  参考 Rocket使用
