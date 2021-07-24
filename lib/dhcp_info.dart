
import 'dart:async';

import 'package:flutter/services.dart';


class WifiIpInfo {
  final String ip;
  final String netmask;
  final String broadcastIP;
  final String gateway;

  WifiIpInfo.fromMap(Map<dynamic, dynamic> map)
      : ip = map["ip"] as String,
        netmask = map["netmask"] as String,
        broadcastIP = map["broadcastIP"] as String,
        gateway = map["gateway"] as String;

}

class DhcpInfo {
  static const MethodChannel _channel =
      const MethodChannel('dhcp_info');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<WifiIpInfo> get getWifiIp async {
    final Map wifiInfoMap = Map<String, String>.from(await _channel.invokeMethod('getWifiIp'));
    return WifiIpInfo.fromMap(wifiInfoMap);
  }
}
