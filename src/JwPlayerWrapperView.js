import React, { Component } from "react";
import { requireNativeComponent } from "react-native";
import PropTypes from "prop-types";

export default class JwPlayerWrapperView extends Component {
  setNativeProps = nativeProps => {
    if (this._root) {
      this._root.setNativeProps(nativeProps);
    }
  };

  render() {
    console.log(JSON.stringify(this.props));

    return (
      <JwPlayerWrapper
        ref={e => (this._root = e)}
        {...this.props}
        file={"https://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"}
        onFullScreen={event =>
          this.props.onFullScreen && this.props.onFullScreen(event.nativeEvent)
        }
      />
    );
  }
}

JwPlayerWrapperView.propTypes = {
  // zoomEnabled: PropTypes.string,
  file: PropTypes.string
};

const JwPlayerWrapper = requireNativeComponent(
  "JwPlayerWrapper",
  JwPlayerWrapperView
);
