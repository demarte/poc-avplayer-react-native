import React, { Component } from "react";
import { requireNativeComponent } from "react-native";

const Player = requireNativeComponent("Player");
export default class AndroidNativePlayer extends Component {
  render() {
    return (
      <Player
        {...this.props}
        file={"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"}
      />
    );
  }
}
