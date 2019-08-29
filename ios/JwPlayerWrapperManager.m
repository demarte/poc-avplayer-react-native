#import <Foundation/Foundation.h>
#import "JwPlayerWrapper.h"
#import "JwPlayerWrapperManager.h"
#import <React/RCTUIManager.h>

// import RCTBridge
#if __has_include(<React/RCTBridge.h>)
#import <React/RCTBridge.h>
#elif __has_include(“RCTBridge.h”)
#import “RCTBridge.h”
#else
#import “React/RCTBridge.h” // Required when used as a Pod in a Swift project
#endif

@implementation JwPlayerWrapperManager

@synthesize bridge = _bridge;

// Export a native module
RCT_EXPORT_MODULE();

// Return the native view that represents your React component
- (UIView *)view
{
  return [[JwPlayerWrapper alloc] initWithEventDispatcher:self.bridge.eventDispatcher];
}

- (dispatch_queue_t)methodQueue {
  return _bridge.uiManager.methodQueue;
}

RCT_EXPORT_VIEW_PROPERTY(file, NSString)
//RCT_EXPORT_VIEW_PROPERTY(onFullScreen, RCTDirectEventBlock);


// Export constants
// https://facebook.github.io/react-native/releases/next/docs/native-modules-ios.html#exporting-constants
- (NSDictionary *)constantsToExport
{
  return @{
           @"EXAMPLE": @"example"
           };
}
@end
