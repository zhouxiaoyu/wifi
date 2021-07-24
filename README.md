# dhcp_info

A flutter plugin to get DHCP info.

## Getting Started

Pubspec #
  
  1 Add to pubspec

  get_DHCP_info:
    git:
      url: https://github.com/zhouxiaoyu/wifi

   2 get System permissions
   
      Android 
   
       <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /> in AndroidMainifest
         
     3 run pub get 
     
     4 dart  import 'package:dhcp_info/dhcp_info.dart';
     
     5 Use
        WifiIpInfo info = await DhcpInfo.getWifiIp;
        
        Example info.ip / info.netmask /  info.gateway  /  info.broadcastIP 
     



 
 
