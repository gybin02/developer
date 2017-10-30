# Developer

### 背景
  开发完一个功能，想点击一个额外按钮直接测试怎么办？ 页面里写死一个测试按钮？还是代码里修改一个现有方法来测试？ 都不是好的方法，现在可以用developer了。
  通过简单的独立配置文件编写测试功能，初始化下，既可以在当前页面集成一个 测试悬浮页面，方便随时测试；

### 功能
快速功能测试工具，for 开发者 和 QA。
- 所有页面简单初始化后，都可以调用开发者页面功能；
- 同时每个页面，也可以配置自己的测试功能；
- 集成了二维码扫描；
- 应用清除缓存；
- 卸载应用；
- 使用帮助

### Relation ;
-  [Rocket] http://git.meiyou.im/Android/rocket
-  [QrCode] (http://git.meiyou.im/Android/QrcodeMain)
- [开发者页面](http://git.meiyou.im/Android/Android/wikis/%E5%BC%80%E5%8F%91%E8%80%85%E9%A1%B5%E9%9D%A2)

![jietu2](images/QQ20170920-1.png)
<img src="http://git.meiyou.im/Android/developer/raw/master/images/device1.png" width="340" height="541">
### build 

```groovy
//编译。 from 经期614 已加入
compile 'com.meiyou.framework:developer:1.0.0-SNAPSHOT'
// 经期 只在测试，预发阶段引入；因为包含二维码扫描，影响包大小，正式包去掉这功能. 正式版本引用空包可以不影响包的大小
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

### AppRocketConfig  参考 Rocket使用
```java
public class DeveloperConfig extends RocketConfig {
    
    @Button("点击发起APM接口和Batch接口")
    public void doBatch() {
        //轮询接口，为了方便测试，自己点击发起
        ApmSyncManager.getInstance().doSync(context);
        GaSyncManager.getInstance().doSync(context);
    }

    @EditText("输入Uri，如：meiyou:///news/comment/open")
    public void doUri(String uri) {
        MeetyouDilutions.create().formatProtocolService(uri);
    }

    /**
     * 临时方法测试
     */
    @Button("临时方法测试")
    public void temp() {
//        RuntimeUtil.uninstall(this);
//        RefreshAuthController.getInstance().handle(context, true);

    }

    public void testLogin() {
        LoginConfig config = new LoginConfig();
        config.bEnterMain = false;
        LoginActivity.enterActivity(context, config, null);
    }

    @Title("QaTest 功能(Charles查看埋点数据)")
    @CheckBox
    public void openQaTest(boolean enable) {
        ToastUtils.showToast(context, "Qa Test 功能启用状态：" + enable);

        QaTestConfig config = new QaTestConfig();
        config.enable = enable;
        QaTestController.getInstance().setConfig(config);

        SharedPreferencesUtil.saveBoolean(context, DeveloperController.key_qatest, enable);
    }

    @Button("清除联合登录数据")
    public void clearUnionLogin() {
        if (AppTraveler.getInstance().clearAccount()) {
            ToastUtils.showToast(context, "清除联合账号数据成功");
        } else {
            ToastUtils.showToast(context, "清除联合账号数据失败");
        }
    }
```
#### 美柚已经接入情况：
http://git.meiyou.im/Android/Android/wikis/%E5%BC%80%E5%8F%91%E8%80%85%E9%A1%B5%E9%9D%A2

#### change log
#### 1.0.5-SNAPSHOT
1. remove zxing包
2. 排序功能优化，
3. 新增 安装Charles证书
4. 新增 自动设置WiFi代理，支持安卓5.0以下所有版本。
#### 1.0.4-SNAPSHOT
1. 新增 Order功能，支持排序显示

##### COMPONENT_VERSION=1.0.0-SNAPSHOT
- @Checkbox 新增 isTemp功能； 
- Develop UI 修改



