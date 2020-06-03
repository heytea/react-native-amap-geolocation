
#import "RNReactNativeHeyteaGeolocation.h"

@implementation RNReactNativeHeyteaGeolocation {
      AMapLocationManager *_manager;
       CLLocationManager *_clManager;
      BOOL  _isStarted;
}

RCT_EXPORT_MODULE(RNAMapGeolocation)

- (dispatch_queue_t)methodQueue {
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}


- (instancetype)init {
    
    if (self = [super init]) {
        _manager = [AMapLocationManager new];
        [_manager setDesiredAccuracy:kCLLocationAccuracyHundredMeters];
        _clManager = [CLLocationManager new];
        _isStarted = NO;
    }
    return self;
}

//初始化
RCT_EXPORT_METHOD(init:(NSString *)key) {
    [AMapServices sharedServices].apiKey = key;
}

//是否已经开始定位了
RCT_EXPORT_METHOD(isStarted:(RCTResponseSenderBlock)callback) {
  callback(@[@(_isStarted)]);
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setOnceLocation:(BOOL)value) {

}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setWifiScan:(BOOL)value) {
  
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setInterval:(NSInteger)value) {
  
}


//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setSensorEnable:(BOOL)value) {
      
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setOpenAlwaysScanWifi:(BOOL)value) {
      
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setNeedAddress:(BOOL)value) {
      
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setOnceLocationLatest:(BOOL)value) {
      
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setMockEnable:(BOOL)value) {
      
}


//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setLocationCacheEnable:(BOOL)value) {
      
}


//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setGpsFirst:(BOOL)value) {
      
}

//
RCT_EXPORT_METHOD(setHttpTimeout:(NSInteger)value) {
  [_manager setLocationTimeout:value];
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setGpsFirstTimeout:(NSInteger)value) {

}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setLocationMode:(NSString *)mode) {
  
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setLocationPurpose:(NSString *)purpose) {
  
}

//安卓那边有这个设置 ios没找到
RCT_EXPORT_METHOD(setGeoLanguage:(NSString *)language) {
  
}


//设置定位精度
RCT_EXPORT_METHOD(setAccuracy:(NSInteger)accuracy) {
  
  switch (accuracy) {
           case 0:
                 [_manager setDesiredAccuracy:kCLLocationAccuracyBestForNavigation];
               break;
           case 1:
               [_manager setDesiredAccuracy:kCLLocationAccuracyBest];
               break;
           case 2:
                 [_manager setDesiredAccuracy:kCLLocationAccuracyNearestTenMeters];
               break;
           case 3:
                [_manager setDesiredAccuracy:kCLLocationAccuracyHundredMeters];
               break;
           case 4:
                [_manager setDesiredAccuracy:kCLLocationAccuracyKilometer];
               break;
           case 5:
               [_manager setDesiredAccuracy:kCLLocationAccuracyThreeKilometers];
               break;
           default:
               break;
       }
}





//获取当前定位
RCT_EXPORT_METHOD(getCurrentLocation:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
      
    
  CLAuthorizationStatus status =    [CLLocationManager authorizationStatus];
  [_clManager requestWhenInUseAuthorization];
  if (status == kCLAuthorizationStatusAuthorizedAlways || status ==  kCLAuthorizationStatusAuthorizedWhenInUse || status ==  kCLAuthorizationStatusNotDetermined) {
   [_manager requestLocationWithReGeocode:NO completionBlock:^(CLLocation *location, AMapLocationReGeocode *regeocode, NSError *error) {
          if (error) {
              reject([NSString stringWithFormat:@"%ld",(long)error.code],error.localizedDescription,error);
          }else {
              id json = [self json:location reGeocode:regeocode];
              [NSUserDefaults.standardUserDefaults setObject:json forKey:RNReactNativeHeyteaGeolocation.storeKey];
              resolve(json);
          }
      }];
      
  } else {
      reject(@"-10086",@"location unauthorized",nil);
  }
  
}

//获取最近的一次定位信息
RCT_EXPORT_METHOD(getLastKnownLocation:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    id json = [NSUserDefaults.standardUserDefaults objectForKey:RNReactNativeHeyteaGeolocation.storeKey];
    if (json) {
        resolve(json);
    }else {
        resolve(nil);
    }
}


+ (NSString *)storeKey {
    return @"AMapGeolocation";
}


- (id)json:(CLLocation *)location reGeocode:(AMapLocationReGeocode *)reGeocode {
    
    BOOL flag = AMapLocationDataAvailableForCoordinate(location.coordinate);
    if (reGeocode) {
        return @{
                 @"accuracy": @(location.horizontalAccuracy),
                 @"latitude": @(location.coordinate.latitude),
                 @"longitude": @(location.coordinate.longitude),
                 @"isAvailableCoordinate": @(flag),
                 @"altitude": @(location.altitude),
                 @"speed": @(location.speed),
                 @"direction": @(location.course),
                 @"timestamp": @(location.timestamp.timeIntervalSince1970 * 1000),
                 @"country": reGeocode.country ? reGeocode.country : @"",
                 @"province": reGeocode.province ?reGeocode.province :@"" ,
                 @"city": reGeocode.city ? reGeocode.city : @"",
                 @"cityCode": reGeocode.citycode ? reGeocode.citycode : @"",
                 };
       
    } else {
        return @{
                 @"accuracy": @(location.horizontalAccuracy),
                 @"latitude": @(location.coordinate.latitude),
                 @"longitude": @(location.coordinate.longitude),
                 @"isAvailableCoordinate": @(flag),
                 @"altitude": @(location.altitude),
                 @"speed": @(location.speed),
                 @"direction": @(location.course),
                 @"timestamp": @(location.timestamp.timeIntervalSince1970 * 1000),
                 };
    }
}


//开始定位
RCT_EXPORT_METHOD(startLocation) {
  _isStarted = YES;
  [_manager startUpdatingLocation];
}

//停止更新位置信息
RCT_EXPORT_METHOD(stopLocation){
  _isStarted = NO;
  [_manager stopUpdatingLocation];
}



@end
  