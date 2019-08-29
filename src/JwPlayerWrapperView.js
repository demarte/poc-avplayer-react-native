import React, { Component } from "react";
import { requireNativeComponent } from "react-native";
import PropTypes from "prop-types";

export default class JwPlayerWrapperView extends Component {
  render() {
    return (
      <JwPlayerWrapper
        {...this.props}
        file={"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"}
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
