package com.heyteago.amap.geolocation;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashSet;
import java.util.Set;

public class RNAMapGeolocationModule extends ReactContextBaseJavaModule implements AMapLocationListener {

    private final ReactApplicationContext reactContext;
    private AMapLocationClient mAMapLocationClient;
    private DeviceEventManagerModule.RCTDeviceEventEmitter mRCTDeviceEventEmitter;
    private AMapLocationClientOption mOption = new AMapLocationClientOption();
    private Set<Promise> currentLocationPromiseSet = new HashSet<>();

    private String lastKey = "";
    private AMapLocation currentAMapLocation;

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
        if (aMapLocation != null) {
            currentAMapLocation = aMapLocation;
            getDeviceEventEmitter().emit("AMap_onLocationChanged", location2WritableMap(aMapLocation));
            for (Promise promise : currentLocationPromiseSet) {
                promise.resolve(location2WritableMap(currentAMapLocation));
            }
            currentLocationPromiseSet.clear();
        }
    }

    @ReactMethod
    public void init(String key, Promise promise) {
        if (!lastKey.equals(key) && mAMapLocationClient != null) {
            mAMapLocationClient.onDestroy();
        }
        if (mAMapLocationClient != null) {
            promise.resolve(false);
            return;
        }
        this.lastKey = key;
        AMapLocationClient.setApiKey(key);
        mAMapLocationClient = new AMapLocationClient(reactContext);
        mAMapLocationClient.setLocationListener(this);
        promise.resolve(true);
    }

    @ReactMethod
    public void setAccuracy(int accuracy) {

    }

    @ReactMethod
    public void getDeviceId(Promise promise) {
        if (mAMapLocationClient == null) {
            promise.reject("-1", "尚未调用init()进行初始化");
            return;
        }
        promise.resolve(AMapLocationClient.getDeviceId(reactContext));
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

    // 此方法不属于高德地图API
    @ReactMethod
    public void getCurrentLocation(Promise promise) {
        currentLocationPromiseSet.add(promise);
    }

    @ReactMethod
    public void getLastKnownLocation(Promise promise) {
        if (mAMapLocationClient == null) {
            promise.reject("-1", "尚未调用init()进行初始化");
            return;
        }
        AMapLocation aMapLocation = mAMapLocationClient.getLastKnownLocation();
        promise.resolve(location2WritableMap(aMapLocation));
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

    private WritableMap location2WritableMap(AMapLocation aMapLocation) {
        WritableMap map = Arguments.createMap();
        if (aMapLocation == null) {
            return map;
        }
        map.putInt("errorCode", aMapLocation.getErrorCode());
        map.putString("errorInfo", aMapLocation.getErrorInfo());
        if (aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
            map.putDouble("accuracy", aMapLocation.getAccuracy());
            map.putString("address", aMapLocation.getAddress());
            map.putString("adCode", aMapLocation.getAdCode());
            map.putString("aoiName", aMapLocation.getAoiName());
            map.putDouble("altitude", aMapLocation.getAltitude());
            map.putDouble("bearing", aMapLocation.getBearing());
            map.putString("buildingId", aMapLocation.getBuildingId());
            map.putString("city", aMapLocation.getCity());
            map.putString("cityCode", aMapLocation.getCityCode());
            map.putInt("conScenario", aMapLocation.getConScenario());
            map.putString("coordType", aMapLocation.getCoordType());
            map.putString("country", aMapLocation.getCountry());
            map.putString("description", aMapLocation.getDescription());
            map.putString("district", aMapLocation.getDistrict());
            map.putString("floor", aMapLocation.getFloor());
            map.putInt("gpsAccuracyStatus", aMapLocation.getGpsAccuracyStatus());
            map.putDouble("latitude", aMapLocation.getLatitude());
            map.putString("locationDetail", aMapLocation.getLocationDetail());

            WritableMap qualityReportMap = Arguments.createMap();
            AMapLocationQualityReport aMapLocationQualityReport = aMapLocation.getLocationQualityReport();
            String adviseMessage = aMapLocationQualityReport.getAdviseMessage();
            if (adviseMessage != null) {
                adviseMessage = adviseMessage.replaceAll("[\\t\\n\\r/]", "");
            }
            qualityReportMap.putString("adviseMessage", adviseMessage);
            qualityReportMap.putInt("gpsSatellites", aMapLocationQualityReport.getGPSSatellites());
            qualityReportMap.putInt("gpsStatus", aMapLocationQualityReport.getGPSStatus());
            qualityReportMap.putDouble("netUseTime", aMapLocationQualityReport.getNetUseTime());
            qualityReportMap.putString("networkType", aMapLocationQualityReport.getNetworkType());
            qualityReportMap.putBoolean("isInstalledHighDangerMockApp", aMapLocationQualityReport.isInstalledHighDangerMockApp());
            qualityReportMap.putBoolean("isWifiAble", aMapLocationQualityReport.isWifiAble());
            map.putMap("locationQualityReport", qualityReportMap);

            map.putInt("locationType", aMapLocation.getLocationType());
            map.putDouble("longitude", aMapLocation.getLongitude());
            map.putString("poiName", aMapLocation.getPoiName());
            map.putString("provider", aMapLocation.getProvider());
            map.putString("province", aMapLocation.getProvince());
            map.putInt("satellites", aMapLocation.getSatellites());
            map.putDouble("speed", aMapLocation.getSpeed());
            map.putString("street", aMapLocation.getStreet());
            map.putString("streetNum", aMapLocation.getStreetNum());
            map.putInt("trustedLevel", aMapLocation.getTrustedLevel());
            map.putDouble("time", aMapLocation.getTime());
        }
        return map;
    }

}