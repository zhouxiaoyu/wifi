#import "DhcpInfoPlugin.h"
#if __has_include(<dhcp_info/dhcp_info-Swift.h>)
#import <dhcp_info/dhcp_info-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "dhcp_info-Swift.h"
#endif

@implementation DhcpInfoPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftDhcpInfoPlugin registerWithRegistrar:registrar];
}
@end
