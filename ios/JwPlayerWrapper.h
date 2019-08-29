#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <AVKit/AVKit.h>
#import <JWPlayer_iOS_SDK/JWPlayerController.h>

@class RCTEventDispatcher;

@interface JwPlayerWrapper : UIView
// Define view properties here with @property
//@property(nonatomic, strong) JWPlayerController *player;
@property(nonatomic, strong) AVPlayerViewController *playerViewController;
//@property(nonatomic, strong) AVPlayer *player;
//@property(nonatomic, assign) NSString *playList;
@property(nonatomic, assign) NSString *file;
//@property (nonatomic, copy) RCTDirectEventBlock onFullScreen;

// Initializing with the event dispatcher allows us to communicate with JS
- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher NS_DESIGNATED_INITIALIZER;
@end
