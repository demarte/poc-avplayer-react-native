//
//  ContainerViewController.h
//  pocplayermobile
//
//  Created by Ivan on 14/09/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVKit/AVKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ContainerViewController : UIViewController

@property(nonatomic, strong) AVPlayerViewController *playerViewController;
@property (nonatomic, strong) AVPlayer *player;
@property (nonatomic, strong) NSURL *videoURL;
@property (nonatomic, strong) UIActivityIndicatorView *spinner;

@end

NS_ASSUME_NONNULL_END
