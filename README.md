
# @heytea/native-amap-geolocation

react native 高德地图定位模块。

## Install

`$ npm install @heytea/react-native-amap-geolocation --save`


#### Android

在Manifest.xml文件中添加

```xml
<application>
    <meta-data
        android:name="com.amap.api.v2.apikey"
        android:value="高德地图App key" />

    <service android:name="com.amap.api.location.APSService"></service>
</application>
```

声明以下权限：

```xml
<!--用于进行网络定位-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
<!--用于访问GPS定位-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
<!--用于获取运营商信息，用于支持提供运营商信息相关的接口-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
<!--用于访问wifi网络信息，wifi信息会用于进行网络定位-->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<!--用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
<!--用于访问网络，网络定位需要上网-->
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<!--用于读取手机当前的状态-->
<uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
<!--用于申请调用A-GPS模块-->
<uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"></uses-permission>
```

## Use

#### 1. 初始化

```typescript
import geoLocation from '@heytea/react-native-amap-geolocation'

geoLocation.init(aMapKey)
```

#### 2. API

```typescript
// 获取当前位置（省电，会自动启动定位，获取到位置后，自动关闭定位）
const location = await geoLocation.getCurrentLocation()

// 获取最后已知位置
const location = await geoLocation.getLastKnownLocation()

// 获取deviceId
const id = await geoLocation.getDeviceId()

// 启动定位
geoLocation.startLocation()

// 关闭定位
geoLocation.stopLocation()

// 定位是否启动中
const doing = await geoLocation.isStarted()

// 设置项
setWifiScan(boolean)
setInterval(number)
setSensorEnable(boolean)
setOpenAlwaysScanWifi(boolean)
setNeedAddress(boolean)
setOnceLocationLatest(boolean)
setMockEnable(boolean)
setLocationCacheEnable(boolean)
setGpsFirst(boolean)
setHttpTimeout(number)
setGpsFirstTimeout(number)
setGeoLanguage(string)

```

  
