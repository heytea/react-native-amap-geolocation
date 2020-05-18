export interface AMapLocation {
    /**
     * 定位精度 单位:米
     */
    accuracy: number
    /**
     * 区域编码 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回区域编码自2.9.0版本开始，当AMapLocation.LOCATION_TYPE_GPS时也可以返回区域编码
     */
    adCode: string
    /**
     * 地址 2.9.0之前的版本定位类型为AMapLocation.LOCATION_TYPE_GPS时不会返回地址信息自2.9.0版本开始，当类型为AMapLocation.LOCATION_TYPE_GPS时也可以返回地址信息(需要网络通畅，第一次有可能没有地址信息返回）
     */
    address: string
    /**
     * 海拔高度(单位：米) 默认值：0.0
     */
    altitude: number
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