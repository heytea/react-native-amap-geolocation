
Pod::Spec.new do |s|
  s.name         = "RNReactNativeHeyteaGeolocation"
  s.version      = "1.0.0"
  s.summary      = "RNReactNativeHeyteaGeolocation"
  s.description  = <<-DESC
                  RNReactNativeHeyteaGeolocation
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://github.com/author/RNReactNativeHeyteaGeolocation.git", :tag => "master" }
  s.source_files  = "RNReactNativeHeyteaGeolocation/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  s.dependency "AMapLocation" , '2.6.2'

end

  