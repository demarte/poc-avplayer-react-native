import React, { Component } from "react";
import { requireNativeComponent } from "react-native";
import PropTypes from "prop-types";

export default class JwPlayerWrapperView extends Component {
  render() {
    return (
      <JwPlayerWrapper
        {...this.props}
        file={"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"}
        // file={
        //   "https://aulapp-dev-media.s3.amazonaws.com/files/MANHATTAN+NEW+YORK+CITY+-+NY+%2C+UNITED+STATES+-+A+TRAVEL+TOUR+-+4K+UHD.mp4"
        // }
      />
    );
  }
}

JwPlayerWrapperView.propTypes = {
  file: PropTypes.string
};

const JwPlayerWrapper = requireNativeComponent(
  "JwPlayerWrapper",
  JwPlayerWrapperView
);
