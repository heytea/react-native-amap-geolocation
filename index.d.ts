export enum GPS_STATUS {
    /**
     * 卫星定位状态--选择的定位模式中不包含卫星定位 Android 4.4以上的手机设置中开启了定位（位置）服务，但是选择的模式为省电模式，不包含卫星定位
     * 建议选择包含gps定位的模式（例如：高精度、仅设备）
     */
    GPS_STATUS_MODE_SAVING = 3,
    /**
     * 卫星定位状态--没有GPS定位权限 如果没有GPS定位权限无法进行卫星定位, 建议在安全软件中授予GPS定位权限
     */
    GPS_STATUS_NOGPSPERMISSION = 4,
    /**
     * 卫星定位状态--手机中没有GPS Provider，无法进行卫星定位
     */
    GPS_STATUS_NOGPSPROVIDER = 1,
    /**
     * 卫星定位状态--GPS开关关闭 建议开启GPS开关，提高定位质量
     * Android 4.4以下的手机是gps开关关闭-建议开启gps开关
     * Android 4.4以上的手机设置中关闭了定位（位置）服务-建议开启定位服务，并选择包含gps的定位模式
     */
    GPS_STATUS_OFF = 2,
    /**
     * 卫星定位状态--正常
     */
    GPS_STATUS_OK = 0,
}

export interface LocationQualityReport {
    /**
     * 获取提示语义,状态良好时，返回的是内容为空 根据当前的质量报告，给出相应的建议
     */
    adviseMessage: string
    /**
     * 获取当前的卫星数， 只有在非低功耗模式下此值才有效
     */
    gpsSatellites: number
    /**
     * 获取卫星状态信息，只有在非低功耗模式下此值才有效
     */
    gpsStatus: GPS_STATUS
    /**
     * 获取网络定位时的网络耗时 单位：毫秒
     */
    netUseTime: number
    /**
     * 获取网络连接类型（2G、3G、4G、WIFI)
     */
    networkType: string
    /**
     * 是否安装了高危位置模拟软件 首次定位可能没有结果
     */
    isInstalledHighDangerMockApp: boolean
    /**
     * wifi开关是否打开 如果wifi关闭建议打开wifi开关，提高定位质量
     */
    isWifiAble: boolean
}

export enum COORD {
    /**
     * GCJ02坐标系
     */
    COORD_TYPE_GCJ02 = "",
    /**
     * WGS84坐标系
     */
    COORD_TYPE_WGS84 = ""
}

export enum ERROR_CODE {
    /**
     * 定位错误码：定位失败，飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关
     */
    ERROR_CODE_AIRPLANEMODE_WIFIOFF = 18,
    /**
     * 定位错误码：KEY错误,可以通过AMapLocation.getLocationDetail()获取详细信息来跟注册的KEY信息进行对照
     */
    ERROR_CODE_FAILURE_AUTH = 7,
    /**
     * 定位错误码：错误的基站信息，请检查是否安装SIM卡
     */
    ERROR_CODE_FAILURE_CELL = 11,
    /**
     * 定位错误码：网络连接异常,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_FAILURE_CONNECTION = 4,
    /**
     * 定位错误码：初始化异常,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_FAILURE_INIT = 9,
    /**
     * 定位错误码：定位结果错误,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_FAILURE_LOCATION = 6,
    /**
     * 定位错误码：获取到的请求参数为空，可能获取过程中出现异常,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_FAILURE_LOCATION_PARAMETER = 3,
    /**
     * 定位错误码：缺少定位权限,请检查是否配置定位权限,并在安全软件和设置中给应用打开定位权限
     */
    ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12,
    /**
     * 定位错误码：卫星定位失败，可用卫星数不足
     */
    ERROR_CODE_FAILURE_NOENOUGHSATELLITES = 14,
    /**
     * 定位错误码：网络定位失败，请检查设备是否插入sim卡、开启移动网络或开启了wifi模块
     */
    ERROR_CODE_FAILURE_NOWIFIANDAP = 13,
    /**
     * 定位错误码：解析XML出错,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_FAILURE_PARSER = 5,
    /**
     * 定位错误码：定位位置可能被模拟
     */
    ERROR_CODE_FAILURE_SIMULATION_LOCATION = 15,
    /**
     * 定位错误码：定位失败，由于设备仅扫描到单个wifi，不能精准的计算出位置信息。
     */
    ERROR_CODE_FAILURE_WIFI_INFO = 2,
    /**
     * 定位错误码：一些重要参数为空,如context,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_INVALID_PARAMETER = 1,
    /**
     * 定位错误码：定位失败，没有检查到SIM卡，并且关闭了WIFI开关，请打开WIFI开关或者插入SIM卡
     */
    ERROR_CODE_NOCGI_WIFIOFF = 19,
    /**
     * 定位错误码：定位服务启动失败，请检查是否配置service并且manifest中service标签是否配置在application标签内
     */
    ERROR_CODE_SERVICE_FAIL = 10,
    /**
     * 定位错误码：其他错误,可以通过AMapLocation.getLocationDetail()获取详细信息
     */
    ERROR_CODE_UNKNOWN = 9
}
export interface AMapLocation {
    errorCode: ERROR_CODE
    errorInfo: string
    /**
     * 定位精度 单位:米
     */
    accuracy: number
    /**
     * 地址 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回地址信息自2.9.0版本开始，当类型为AMapLocation.LOCATION_TYPE_GPS时也可以返回地址信息(需要网络通畅，第一次有可能没有地址信息返回）
     */
    address: string
    /**
     * 区域编码 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回区域编码自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回区域编码
     */
    adCode: string
    /**
     * 海拔高度(单位：米) 默认值：0.0
     */
    altitude: number
    /**
     * 获取兴趣面名称
     */
    aoiName: string
    /**
     * 获取方向角(单位：度） 默认值：0.0
     */
    bearing: number
    /**
     * 返回支持室内定位的建筑物ID信息
     */
    buildingId: string
    /**
     * 获取城市名称 
     */
    city: string
    /**
     * 获取城市编码
     */
    cityCode: string
    /**
     * 室内外置信度 室内：且置信度取值在[1 ～ 100]，值越大在在室内的可能性越大 室外：且置信度取值在[-100 ～ -1] ,值越小在在室内的可能性越大 无法识别室内外：置信度返回值为 0
     */
    conScenario: number
    /**
     * 获取坐标系类型 高德定位sdk会返回两种坐标系 AMapLocation.COORD_TYPE_GCJ02 -- GCJ02坐标系 AMapLocation.COORD_TYPE_WGS84 -- WGS84坐标系,国外定位时返回的是WGS84坐标系
     */
    coordType: string
    /**
     * 获取国家名称
     */
    country: string
    /**
     * 获取位置语义信息
     */
    description: string
    /**
     * 获取区的名称 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回区的名称自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回区的名称
     */
    district: string
    /**
     * 获取室内定位的楼层信息
     */
    floor: string
    /**
     * 获取卫星信号强度，仅在卫星定位时有效,值为 #GPS_ACCURACY_BAD，#GPS_ACCURACY_GOOD，#GPS_ACCURACY_UNKNOWN
     */
    gpsAccuracyStatus: number
    /**
     * 获取纬度
     */
    latitude: number
    /**
     * 获取定位信息描述
     */
    locationDetail: string
    /**
     * 获取定位质量
     */
    locationQualityReport: LocationQualityReport
    /**
     * 获取定位结果来源
     */
    locationType: number
    /**
     * 获取经度
     */
    longitude: number
    /**
     * 获取兴趣点名称 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回兴趣点名称自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回兴趣点名称
     */
    poiName: string
    /**
     * 获取定位提供者
     */
    provider: string
    /**
     * 获取省的名称 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回省份名称自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回省份名称
     */
    province: string
    /**
     * 获取当前可用卫星数量, 仅在卫星定位时有效,
     */
    satellites: number
    /**
     * 获取当前速度(单位：米/秒) 默认值：0.0
     * 3.1.0之前的版本只有定位类型为 AMapLocation.LOCATION_TYPE_GPS时才有值
     * 自3.1.0版本开始，不限定定位类型，当定位类型不是AMapLocation.LOCATION_TYPE_GPS时，可以通过 AMapLocationClientOption.setSensorEnable(boolean) 控制是否返回速度值，当设置为true时会通过手机传感器获取速度,如果手机没有对应的传感器会返回0.0
     */
    speed: number
    /**
     * 获取街道名称 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回街道名称
     * 自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回街道名称
     */
    street: string
    /**
     * 获取门牌号 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回门牌号
     * 自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回门牌号
     */
    streetNum: string
    /**
     * 获取定位结果的可信度 只有在定位成功时才有意义 非常可信 AMapLocation.TRUSTED_LEVEL_HIGH 可信度一般AMapLocation.TRUSTED_LEVEL_NORMAL 可信度较低 AMapLocation.TRUSTED_LEVEL_LOW 非常不可信 AMapLocation.TRUSTED_LEVEL_BAD
     */
    trustedLevel: number
    time: number
}

/**
 * 初始化
 * @param key 高德SDK应用key
 */
export function init(key: string): Promise<boolean>
/**
 * 开始定位
 */
export function startLocation(): void
/**
 * 停止定位
 */
export function stopLocation(): void
/**
 * 本地定位服务是否已经启动，用于用户检查服务是否已经启动
 */
export function isStarted(): Promise<boolean>
export function getLastKnownLocation(): Promise<AMapLocation>
export function setOnceLocation(value: boolean): void
export function setWifiScan(value: boolean): void
export function setInterval(interval: number): void
export function setSensorEnable(value: boolean): void
export function setOpenAlwaysScanWifi(value: boolean): void
export function setNeedAddress(value: boolean): void
export function setOnceLocationLatest(value: boolean): void
export function setMockEnable(value: boolean): void
export function setLocationCacheEnable(value: boolean): void
export function setGpsFirst(value: boolean): void
export function setHttpTimeout(value: number): void
export function setGpsFirstTimeout(value: number): void
export function setLocationMode(mode: string): void
export function setLocationPurpose(purpose: string): void
export function setGeoLanguage(language: string): void