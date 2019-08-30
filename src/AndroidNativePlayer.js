import React, { Component } from "react";
import { requireNativeComponent } from "react-native";

const Player = requireNativeComponent("Player");
export default class AndroidNativePlayer extends Component {
  render() {
    return <Player {...this.props} />;
  }
}
