#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "ContainerViewController.h"
#import <React/RCTComponent.h>

@class RCTEventDispatcher;

@interface JwPlayerWrapper : UIView
// Define view properties here with @property
//@property(nonatomic, strong) JWPlayerController *player;
@property(nonatomic, strong) ContainerViewController *containerViewController;
//@property(nonatomic, strong) AVPlayer *player;
//@property(nonatomic, assign) NSString *playList;
@property(nonatomic, assign) NSString *file;
//@property (nonatomic, copy) RCTDirectEventBlock onFullScreen;
@property (nonatomic, copy) RCTBubblingEventBlock onEnd;

// Initializing with the event dispatcher allows us to communicate with JS
- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher NS_DESIGNATED_INITIALIZER;
@end
