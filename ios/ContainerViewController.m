//
//  ContainerViewController.m
//  pocplayermobile
//
//  Created by Ivan on 14/09/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "ContainerViewController.h"

@interface ContainerViewController ()

@end

@implementation ContainerViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  self.view.backgroundColor = UIColor.redColor;
  
  [self configurePlayerViewController];
}

- (void)viewDidDisappear:(BOOL)animated {
  [super viewDidDisappear:animated];
  
  [NSNotificationCenter.defaultCenter removeObserver:self name:AVPlayerItemDidPlayToEndTimeNotification object:_player.currentItem];
}

-(void)configurePlayerViewController {
  self.player = [AVPlayer playerWithURL: _videoURL];
  self.playerViewController = [AVPlayerViewController new];
  self.playerViewController.player = _player;
  [self.view addSubview:_playerViewController.view];
  
  [_player addObserver:self
            forKeyPath:@"status"
               options:(NSKeyValueObservingOptionNew)
               context:nil];
}

- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary *)change
                       context:(void *)context
{
  if (object == _player && [keyPath isEqualToString:@"status"]) {
    if (_player.status == AVPlayerStatusReadyToPlay) {
      NSLog(@"PLAYING...");
      
//      [_player removeObserver:self forKeyPath:@"status"];
      
      NSLog(@"Remove obeservable");
      
//      [NSNotificationCenter.defaultCenter addObserver:self selector:@selector(didReachEnd) name:AVPlayerItemDidPlayToEndTimeNotification object: _player.currentItem];
    }
  }
}

//- (void)didReachEnd {
//  NSLog(@"END....");
//}

//- (void)playerItemDidReachEnd {
//  NSLog(@"END....!!!!");
//  self.onEnd(@{});
//}

//- (void)playerItemDidReachEnd:(NSNotification*)notification {
//  self.onEnd(@{});
//}

@end
