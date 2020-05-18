
package com.heyteago.amap.geolocation;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;

public class RNAMapGeolocationModule extends ReactContextBaseJavaModule implements AMapLocationListener {

    private final ReactApplicationContext reactContext;
    private AMapLocationClient mAMapLocationClient;
    private DeviceEventManagerModule.RCTDeviceEventEmitter mRCTDeviceEventEmitter;
    private AMapLocationClientOption mOption = new AMapLocationClientOption();
    private Gson mGson = new Gson();

    public RNAMapGeolocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNAMapGeolocation";
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @ReactMethod
    public void init(String key, Promise promise) {
        if (mAMapLocationClient != null) {
            mAMapLocationClient.onDestroy();
        }
        AMapLocationClient.setApiKey(key);
        mAMapLocationClient = new AMapLocationClient(reactContext);
        mAMapLocationClient.setLocationListener(this);
        promise.resolve(true);
    }

    @ReactMethod
    public void start() {
        if (mAMapLocationClient == null) {
            return;
        }
        mAMapLocationClient.startLocation();
    }

    @ReactMethod
    public void stop() {
        if (mAMapLocationClient == null) {
            return;
        }
        mAMapLocationClient.stopLocation();
    }

    @ReactMethod
    public void isStarted(Promise promise) {
        promise.resolve(mAMapLocationClient.isStarted());
    }

    @ReactMethod
    public void getLastKnownLocation(Promise promise) {
        promise.resolve(mGson.toJson(mAMapLocationClient.getLastKnownLocation()));
    }

    @ReactMethod
    public void setOnceLocation(boolean value) {
        mOption.setOnceLocation(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setWifiScan(boolean value) {
        mOption.setWifiScan(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setInterval(int interval) {
        mOption.setInterval(interval);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setSensorEnable(boolean value) {
        mOption.setSensorEnable(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setOpenAlwaysScanWifi(boolean value) {
        AMapLocationClientOption.setOpenAlwaysScanWifi(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setNeedAddress(boolean value) {
        mOption.setNeedAddress(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setOnceLocationLatest(boolean value) {
        mOption.setOnceLocationLatest(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setMockEnable(boolean value) {
        mOption.setMockEnable(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setLocationCacheEnable(boolean value) {
        mOption.setLocationCacheEnable(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setGpsFirst(boolean value) {
        mOption.setGpsFirst(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setHttpTimeout(int value) {
        mOption.setHttpTimeOut(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setGpsFirstTimeout(int value) {
        mOption.setGpsFirstTimeout(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setLocationMode(String mode) {
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.valueOf(mode));
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setLocationPurpose(String purpose) {
        mOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.valueOf(purpose));
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setGeoLanguage(String language) {
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.valueOf(language));
        mAMapLocationClient.setLocationOption(mOption);
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getDeviceEventEmitter() {
        if (mRCTDeviceEventEmitter == null) {
            mRCTDeviceEventEmitter = reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        return mRCTDeviceEventEmitter;
    }

}