import Color from "../../Color";

import React, { Component } from "react";
import { StyleSheet, View } from "react-native";
import JWPlayer from "react-native-jw-media-player";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: Color.transparent
  },
  player: {
    flex: 1
  }
});

class PlayerContainer extends Component {
  componentDidMount() {
    const playlistItem = {
      title: "Track",
      mediaId: -1,
      image: "http://image.com/image.png",
      desc: "My beautiful track",
      time: 0,
      // file:
      //   "https://aulapp-dev-media-convert.s3.amazonaws.com/output/dec86fca-285e-4b68-b98a-2a121ebc79b8111-hls.m3u8",

      file:
        "https://196skyfiregce-vimeo.akamaized.net/exp=1566917367~acl=%2F182592195%2F%2A~hmac=896ee3d165ba00c8c260962fd68250e992b6de9055749cad4dd1625ece6e2f24/182592195/video/599562015,1301281825,599562019,599562018,599562016/master.m3u8",

      // file:
      //   "https://r3---sn-t0a7sn7d.googlevideo.com/videoplayback?expire=1566858403&ei=QwhkXfemBsrj8gSJzYf4Cg&ip=35.170.77.200&id=o-AH5onRnalT62UX80yUBGPRgt1S-ZgGCYcC6EAg4ww66U&itag=18&source=youtube&requiressl=yes&mm=31%2C26&mn=sn-t0a7sn7d%2Csn-vgqskn76&ms=au%2Conr&mv=m&mvi=2&pl=13&initcwndbps=1733750&mime=video%2Fmp4&gir=yes&clen=8148987&ratebypass=yes&dur=269.490&lmt=1559309616222523&mt=1566836683&fvip=3&c=WEB&txp=2211222&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=ALgxI2wwRQIgAt5_dmEUHc4OMvvVNx8lbffzckYzW_U8r2ux0AQ-ZDMCIQDtI01VuKZrT9Mal8OgRXc3JeMKwiNl76EbxL_mk1DXKA%3D%3D&lsparams=mm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AHylml4wRQIgSIXfc07eMRx1LnmTOO9cKCCvVi140rVqzBUZYRl3GeMCIQD7rEHn6ERjxIIYUi1A2DaVWJZaWjJ9n2WexA1meUM_Hw%3D%3D",
      // file: "//www.youtube.com/watch?v=V9OmySqbBK4",
      // autostart: true,
      // file:
      //   "https://manifest.googlevideo.com/api/manifest/dash/expire/1566858660/ei/RAlkXYKYHtakxASZzYW4DQ/ip/200.159.109.170/id/c642e126a5d6e5fa/source/youtube/requiressl/yes/playback_host/r3---sn-bg07dnsr.googlevideo.com/mm/31%2C26/mn/sn-bg07dnsr%2Csn-gpv7dn7d/ms/au%2Conr/mv/m/mvi/2/pl/19/hfr/all/as/fmp4_audio_clear%2Cwebm_audio_clear%2Cwebm2_audio_clear%2Cfmp4_sd_hd_clear%2Cwebm2_sd_hd_clear/initcwndbps/420000/mt/1566836922/fvip/3/itag/0/sparams/expire%2Cei%2Cip%2Cid%2Csource%2Crequiressl%2Chfr%2Cas%2Citag/sig/ALgxI2wwRgIhAObdkDs8vbpIiIH2dqGH3z4cCmrz6gYfVL43hj2PGwvjAiEAoRTk4GzuMvMtFbX3KgJBBx7BljDczoIm4kNz5t22sdw%3D/lsparams/playback_host%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps/lsig/AHylml4wRQIhALPSmpkxDG40mFGw_KQVtYWLsY0RTjCy-VqYo1P6bjIZAiA9SSVnpHJFzyAu0ajgpuv1NJI6oTAkGOk7tvvejzwq3w%3D%3D",

      controls: true,
      repeat: false,
      displayDescription: true,
      displayTitle: true
    };
    this.JWPlayer.loadPlaylistItem(playlistItem);
    // this.JWPlayer.file(
    //   "https://196skyfiregce-vimeo.akamaized.net/exp=1566917367~acl=%2F182592195%2F%2A~hmac=896ee3d165ba00c8c260962fd68250e992b6de9055749cad4dd1625ece6e2f24/182592195/video/599562015,1301281825,599562019,599562018,599562016/master.m3u8"
    // );

    // for playlist
    // const playlist = [playlistItem, playlistItem]
    // this.JWPlayer.loadPlaylist(playlistItem);
  }
  onBeforePlay() {
    // eslint-disable-line
    // console.log('onBeforePlay was called');
  }

  onPlay() {
    // eslint-disable-line
    // console.log('onPlay was called');
  }

  onPause() {}

  onPlaylistItem() {}

  onPlayerError(error) {
    // eslint-disable-line
    // console.log('onPlayerError was called with error: ', error);
  }

  onBuffer() {
    // eslint-disable-line
    // console.log('onBuffer was called');
  }

  onTime({ position, duration }) {
    // eslint-disable-line
    // console.log('onTime was called with: ', position, duration);
  }

  onFullScreen() {}

  onFullScreenExit() {}

  render() {
    return (
      <View style={styles.container}>
        <JWPlayer
          ref={p => (this.JWPlayer = p)}
          style={styles.player}
          onBeforePlay={() => this.onBeforePlay()}
          onPlay={() => this.onPlay()}
          onPause={() => this.onPause()}
          onIdle={() => console.log("onIdle")}
          onPlaylistItem={event => this.onPlaylistItem(event)}
          onSetupPlayerError={event => this.onPlayerError(event)}
          onPlayerError={event => this.onPlayerError(event)}
          onBuffer={() => this.onBuffer()}
          onTime={event => this.onTime(event)}
          onFullScreen={() => this.onFullScreen()}
          onFullScreenExit={() => this.onFullScreenExit()}
          controls={true}
        />
      </View>
    );
  }
}

export default PlayerContainer;
