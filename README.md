# ViewTager
一个带分类 `tag` 的 `ViewPager`，因此命名为 `ViewTager`。

在目前流行的 APP 中应用很多，比如

|网易新闻|触宝输入法|
|---|---|
|![Alt text](https://github.com/auv1107/ViewTager/raw/master/readme/1638961672.jpg)|![Alt text](https://github.com/auv1107/ViewTager/raw/master/readme/370590687.jpg)|



## 1. 结构

![Alt text](https://github.com/auv1107/ViewTager/raw/master/readme/803353270.jpg)


## 2. 用法

### 2.1 引用

```
# 项目 build.gradle

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}


# 模块 build.gradle

dependencies {
    compile 'com.github.auv1107:ViewTager:a73de7facf'
}

```

### 2.2 添加布局
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" tools:context="com.sctdroid.app.sample.MainActivity">

    <com.sctdroid.app.viewtager.ViewTager
        android:id="@+id/view_tager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</FrameLayout>
```

### 2.3 初始化数据
主要使用两个方法添加适配器

```
ViewTager viewTager = (ViewTager) findViewById(R.id.view_tager);
viewTager.setRadioGroupAdapter(new RadioAdapter(){
	...
});
mViewTager.setViewPagerAdapter(new PagerAdapter() {
	...
});
```

`RadioAdapter` 对应 分类列表数据
`PagerAdapter` 对应 单页 ViewPager 数据

**重点**： `RadioAdapter` 和 `PagerAdapter` 数量要一致。

## 3. 效果
![Alt text](https://github.com/auv1107/ViewTager/raw/master/readme/clip.gif)

## 4. 其它方法说明
|方法|说明|
|---|---|
|setViewPagerHeightDelegate(ViewHeightDelegate): void| 若 `ViewTager` 在 `ScrollView` 中, 可能需要自己设定`ViewPager` 的高度|
|setViewPagerOnPageChangeListener: void| 用户OnPageChange 事件|
