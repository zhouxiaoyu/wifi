package com.example.dhcp_info;

import android.app.Application;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import androidx.annotation.NonNull;



import java.util.HashMap;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import static android.content.Context.WIFI_SERVICE;

/**
 * DhcpInfoPlugin
 */
public class DhcpInfoPlugin implements FlutterPlugin, MethodCallHandler{
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private static Registrar registrar;



  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "dhcp_info");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {

    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("getWifiIp")) {



       WifiManager wifiManager = (WifiManager) CustomApplication.context.getSystemService(WIFI_SERVICE);
      DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();

      HashMap<String, String> rs =  new HashMap<>();
      rs.put("ip", intToIp(dhcpInfo.ipAddress));
      rs.put("netmask", intToIp(dhcpInfo.netmask));
      rs.put("broadcastIP", intToIp((dhcpInfo.ipAddress & dhcpInfo.netmask) | ~dhcpInfo.netmask));
      rs.put("gateway", intToIp(dhcpInfo.gateway));

      result.success(rs);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }


//  public HashMap<String, String> getWifiIP(Context context) {
//    HashMap<String, String> rs;
//    try {
//      WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
//      DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
//
//      rs new HashMap<>();
//      rs.put("ip", intToIp(dhcpInfo.ipAddress));
//      rs.put("netmask", intToIp(dhcpInfo.netmask));
//      rs.put("broadcastIP", intToIp((dhcpInfo.ipAddress & dhcpInfo.netmask) | ~dhcpInfo.netmask));
//      rs.put("gateway", intToIp(dhcpInfo.gateway));
//      return result;
//    } catch (Exception e) {
//      Log.e("WiFiplugin", e.getMessage());
//    }
//    return null;
//  }


  String intToIp(int i) {
    return (i & 0xFF) + "." +
      ((i >> 8) & 0xFF) + "." +
      ((i >> 16) & 0xFF) + "." +
      ((i >> 24) & 0xFF);
  }
}
