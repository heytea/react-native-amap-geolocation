
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

    private String lastKey = "";

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
        if(aMapLocation != null) {
            getDeviceEventEmitter().emit("AMapGeolocation", mGson.toJson(aMapLocation));
        }
    }

    @ReactMethod
    public void init(String key, Promise promise) {
        if (!lastKey.equals(key) && mAMapLocationClient != null) {
            mAMapLocationClient.onDestroy();
        }
        this.lastKey = key;
        AMapLocationClient.setApiKey(key);
        mAMapLocationClient = new AMapLocationClient(reactContext);
        mAMapLocationClient.setLocationListener(this);
        promise.resolve(true);
    }

    @ReactMethod
    public void getDeviceId(Promise promise) {
        if (mAMapLocationClient == null) {
            promise.reject("-1", "尚未调用init()进行初始化");
            return;
        }
        promise.resolve(mAMapLocationClient.getDeviceId(reactContext));
    }

    @ReactMethod
    public void startLocation() {
        if (mAMapLocationClient == null) {
            return;
        }
        mAMapLocationClient.startLocation();
    }

    @ReactMethod
    public void stopLocation() {
        if (mAMapLocationClient == null) {
            return;
        }
        mAMapLocationClient.stopLocation();
    }

    @ReactMethod
    public void isStarted(Promise promise) {
        if (mAMapLocationClient == null) {
            promise.reject("-1", "尚未调用init()进行初始化");
            return;
        }
        promise.resolve(mAMapLocationClient.isStarted());
    }

    @ReactMethod
    public void getLastKnownLocation(Promise promise) {
        if (mAMapLocationClient == null) {
            promise.reject("-1", "尚未调用init()进行初始化");
            return;
        }
        promise.resolve(mGson.toJson(mAMapLocationClient.getLastKnownLocation()));
    }

    @ReactMethod
    public void setOnceLocation(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setOnceLocation(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setWifiScan(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setWifiScan(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setInterval(int interval) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setInterval(interval);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setSensorEnable(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setSensorEnable(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setOpenAlwaysScanWifi(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        AMapLocationClientOption.setOpenAlwaysScanWifi(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setNeedAddress(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setNeedAddress(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setOnceLocationLatest(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setOnceLocationLatest(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setMockEnable(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setMockEnable(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setLocationCacheEnable(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setLocationCacheEnable(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setGpsFirst(boolean value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setGpsFirst(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setHttpTimeout(int value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setHttpTimeOut(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setGpsFirstTimeout(int value) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setGpsFirstTimeout(value);
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setLocationMode(String mode) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.valueOf(mode));
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setLocationPurpose(String purpose) {
        if (mAMapLocationClient == null) {
            return;
        }
        mOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.valueOf(purpose));
        mAMapLocationClient.setLocationOption(mOption);
    }

    @ReactMethod
    public void setGeoLanguage(String language) {
        if (mAMapLocationClient == null) {
            return;
        }
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