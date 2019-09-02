#import <Foundation/Foundation.h>
#import "JwPlayerWrapper.h"


// import RCTEventDispatcher
#if __has_include(<React/RCTEventDispatcher.h>)
#import <React/RCTEventDispatcher.h>
#elif __has_include("RCTEventDispatcher.h")
#import "RCTEventDispatcher.h"
#else
//#import "React/RCTEventDispatcher.h" // Required when used as a Pod in a Swift project
#endif

//@interface JwPlayerWrapper () <JWPlayerDelegate> //Rever
//@end

@implementation JwPlayerWrapper : UIView  {
  
  RCTEventDispatcher *_eventDispatcher;
//  JWConfig *config;
  NSURL *videoURL;
  AVPlayer *player;
}

- (void)setFile:(NSString *)file
{
  if (![file isEqual:_file]) {
    videoURL = [NSURL URLWithString: file];
    player = [AVPlayer playerWithURL:videoURL];
    self.playerViewController = [AVPlayerViewController new];
    self.playerViewController.player = player;
  }
}

- (instancetype)initWithEventDispatcher:(RCTEventDispatcher *)eventDispatcher
{
  if ((self = [super init])) {
    _eventDispatcher = eventDispatcher;
//    config = [[JWConfig alloc] init];
////    config.file = @"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8";
//    config.controls = YES;  //default
//    config.playbackRateControls = TRUE;
//    config.audioSwitchingEnabled = TRUE;
//    config.stretching = TRUE;
//    self.player = [[JWPlayerController alloc] initWithConfig:config];
//    self.player.delegate = self;
    
//    NSURL *videoURL = [NSURL URLWithString:@"https://r2---sn-p5qlsndk.googlevideo.com/videoplayback?expire=1567112149&ei=dednXZjXEK-q8gTfm53gCg&ip=18.204.207.211&id=o-AGOuZms8TOF7caGxdi6p878DY_YlGMgw5AT3ZCV8FrlM&itag=18&source=youtube&requiressl=yes&mm=31%2C29&mn=sn-p5qlsndk%2Csn-p5qs7nee&ms=au%2Crdu&mv=u&mvi=1&pl=21&mime=video%2Fmp4&gir=yes&clen=36200256&ratebypass=yes&dur=607.619&lmt=1551354413893877&mt=1567090150&fvip=2&c=WEB&txp=5531432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRgIhAObi75Ahe9XI1ukphisO1TGLHq7QuTayXET5iaepAwfLAiEAzHiV0PzDSl-HOJqUOYCF-RhWH14e2UJ0CZ23tcjSNho%3D&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl&lsig=AHylml4wRAIgIZrz1-8NEywUsj4-4QCRXlYr7j7WbGOUGeAMDumnJwMCIFlzCOQX4fRHhADKxaEM4PoAoVxJqTpZul_OA_bTsCFL"];
//    AVPlayer *player = [AVPlayer playerWithURL:videoURL];
//    self.playerViewController = [AVPlayerViewController new];
//    self.playerViewController.player = player;
  }
  return self;
}

-(void)layoutSubviews
{
  [super layoutSubviews];
  self.playerViewController.view.autoresizingMask = UIViewAutoresizingFlexibleBottomMargin|UIViewAutoresizingFlexibleHeight|UIViewAutoresizingFlexibleLeftMargin|UIViewAutoresizingFlexibleRightMargin|UIViewAutoresizingFlexibleTopMargin|UIViewAutoresizingFlexibleWidth;
  self.playerViewController.view.autoresizingMask = UIViewAutoresizingFlexibleBottomMargin|UIViewAutoresizingFlexibleHeight|UIViewAutoresizingFlexibleLeftMargin|UIViewAutoresizingFlexibleRightMargin|UIViewAutoresizingFlexibleTopMargin|UIViewAutoresizingFlexibleWidth;
  self.playerViewController.view.frame = self.bounds;
  self.playerViewController.view.frame =  CGRectMake(0, 0, CGRectGetWidth(self.bounds), CGRectGetHeight(self.bounds));
  [self setAutoresizesSubviews:TRUE];
  [self addSubview:self.playerViewController.view];
//  self.player.forceFullScreenOnLandscape = YES;
//  self.player.forceLandscapeOnFullScreen = YES;
//  self.player.view.autoresizingMask = UIViewAutoresizingFlexibleBottomMargin|UIViewAutoresizingFlexibleHeight|UIViewAutoresizingFlexibleLeftMargin|UIViewAutoresizingFlexibleRightMargin|UIViewAutoresizingFlexibleTopMargin|UIViewAutoresizingFlexibleWidth;
//  self.player.view.frame = self.bounds;
//  self.player.view.frame =  CGRectMake(0, 0, CGRectGetWidth(self.bounds), CGRectGetHeight(self.bounds));
//  [self setAutoresizesSubviews:TRUE];
//  [self addSubview:self.player.view];
  
}

//- (void)onFullscreen:(JWEvent<JWFullscreenEvent> *)event
//{
//  if(self.onFullScreen)
//  {
//    self.onFullScreen(@{@"fullScreen":@(event.fullscreen)});
//  }
//}
@end
