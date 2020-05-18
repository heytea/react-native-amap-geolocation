export interface AMapLocation {}

export function init(key: string): Promise<boolean>
export function start(): void
export function stop(): void
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