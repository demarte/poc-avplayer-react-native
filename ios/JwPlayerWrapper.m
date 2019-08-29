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

- (void)setFile:(NSString *)file
{
  if (![file isEqual:_file]) {
    config.file = [file copy];
  }
}

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher
{
  if ((self = [super init])) {
    _eventDispatcher = eventDispatcher;
    config = [[JWConfig alloc] init];
//    config.file = @"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8";
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
