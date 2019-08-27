#import <Foundation/Foundation.h>
#import "JwPlayerWrapper.h"
#import <JWPlayer_iOS_SDK/JWPlayerController.h>


// import RCTEventDispatcher
#if __has_include(<React/RCTEventDispatcher.h>)
#import <React/RCTEventDispatcher.h>
#elif __has_include("RCTEventDispatcher.h")
#import "RCTEventDispatcher.h"
#else
//#import "React/RCTEventDispatcher.h" // Required when used as a Pod in a Swift project
#endif

@interface JwPlayerWrapper () <JWPlayerDelegate>
@end

@implementation JwPlayerWrapper : UIView  {
  
  RCTEventDispatcher *_eventDispatcher;
  JWConfig *config;
}

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher
{
  if ((self = [super init])) {
    _eventDispatcher = eventDispatcher;
    config = [[JWConfig alloc] init];
//    config.file = @"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8";
//    config.file = @"https://aulapp-dev-media-convert.s3.amazonaws.com/output/41ebf52c-6251-4e66-abea-a3e1d445df21110-hls.m3u8";
    config.file = @"https://196skyfiregce-vimeo.akamaized.net/exp=1566926153~acl=%2F182592195%2F%2A~hmac=064516dd5bbcacfc40255c7caff9c2576011c706aae9719d0c45c778eb8ac5f5/182592195/video/599562015,1301281825,599562019,599562018,599562016/master.m3u8";
    config.controls = YES;  //default
    config.playbackRateControls = TRUE;
    config.audioSwitchingEnabled = TRUE;
    config.stretching = TRUE;
    self.player = [[JWPlayerController alloc] initWithConfig:config];
    self.player.delegate = self;
  }
  return self;
}

-(void)layoutSubviews
{
  [super layoutSubviews];
  self.player.forceFullScreenOnLandscape = YES;
  self.player.forceLandscapeOnFullScreen = YES;
  self.player.view.autoresizingMask = UIViewAutoresizingFlexibleBottomMargin|UIViewAutoresizingFlexibleHeight|UIViewAutoresizingFlexibleLeftMargin|UIViewAutoresizingFlexibleRightMargin|UIViewAutoresizingFlexibleTopMargin|UIViewAutoresizingFlexibleWidth;
  self.player.view.frame = self.bounds;
  self.player.view.frame =  CGRectMake(0, 0, CGRectGetWidth(self.bounds), CGRectGetHeight(self.bounds));
  [self setAutoresizesSubviews:TRUE];
  [self addSubview:self.player.view];
  
}

- (void)onFullscreen:(JWEvent<JWFullscreenEvent> *)event
{
  //  NSLog(@"-=======================================ON FULLSCREEN x EVENT '%d'---------------------", event.fullscreen);
  if(self.onFullScreen)
  {
    self.onFullScreen(@{@"fullScreen":@(event.fullscreen)});
  }
}
@end
