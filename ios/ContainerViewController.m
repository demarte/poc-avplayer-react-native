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
  [self configureSpinner];
}

- (void)viewDidDisappear:(BOOL)animated {
  [super viewDidDisappear:animated];
  
  [NSNotificationCenter.defaultCenter removeObserver:self name:AVPlayerItemDidPlayToEndTimeNotification object:_player.currentItem];
  
  [_player removeObserver:self forKeyPath:@"playbackLikelyToKeepUp"];
  [_player removeObserver:self forKeyPath:@"playbackBufferEmpty"];
}

-(void)configurePlayerViewController {
  _player = [AVPlayer playerWithURL: _videoURL];
  _playerViewController = [AVPlayerViewController new];
  _playerViewController.player = _player;
  _playerViewController.showsPlaybackControls = false;
  [self.view addSubview:_playerViewController.view];
  
//  [_player addObserver:self
//            forKeyPath:@"status"
//               options:(NSKeyValueObservingOptionNew)
//               context:nil];
  
  [_player.currentItem addObserver:self forKeyPath:@"playbackBufferEmpty" options:NSKeyValueObservingOptionNew context:nil];
  [_player.currentItem addObserver:self forKeyPath:@"playbackLikelyToKeepUp" options:NSKeyValueObservingOptionNew context:nil];
  [_player.currentItem addObserver:self forKeyPath:@"playbackBufferFull" options:NSKeyValueObservingOptionNew context:nil];
  
}

-(void) configureSpinner {
  _spinner = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhiteLarge];
  [_spinner startAnimating];
  [self.view addSubview:_spinner];
  [_spinner setTranslatesAutoresizingMaskIntoConstraints:NO];
  
  // CenterX constraint
  [self.view addConstraint:[NSLayoutConstraint constraintWithItem:self.view
                                                        attribute:NSLayoutAttributeCenterX
                                                        relatedBy:NSLayoutRelationEqual
                                                           toItem:_spinner
                                                        attribute: NSLayoutAttributeCenterX
                                                       multiplier:1
                                                         constant:0]];
  // CenterY constraint
  [self.view addConstraint:[NSLayoutConstraint constraintWithItem:self.view
                                                        attribute:NSLayoutAttributeCenterY
                                                        relatedBy:NSLayoutRelationEqual
                                                           toItem:_spinner
                                                        attribute: NSLayoutAttributeCenterY
                                                       multiplier:1
                                                         constant:0]];
 
}

- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary *)change
                       context:(void *)context {
  
  //  if (object == _player && [keyPath isEqualToString:@"status"]) {
  //
  //    if (_player.status == AVPlayerStatusReadyToPlay) {
  //      NSLog(@"PLAYING...");
  //
  ////      [_player removeObserver:self forKeyPath:@"status"];
  //
  //      NSLog(@"Remove obeservable");
  //    }
  //  }
  
//  if (object == _player) {
//    if ([keyPath isEqualToString: @"status"]) {
//      if (_player.status == AVPlayerStatusReadyToPlay) {
//        NSLog(@"PLAYING...");
//      }
//    }
//  }
  if (object == _player.currentItem) {
    if ([keyPath isEqualToString: @"playbackBufferEmpty"]) {
      NSLog(@"Buffer Empty...");
    }
    if ([keyPath isEqualToString: @"playbackLikelyToKeepUp"]) {
      NSLog(@"Keep UP...");
      _playerViewController.showsPlaybackControls = true;
      [_spinner stopAnimating];
    }
//    if ([keyPath isEqualToString: @"playbackBufferFull"]) {
//      NSLog(@"Full...");
//      _playerViewController.showsPlaybackControls = true;
//    }
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
