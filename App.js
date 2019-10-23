import React, {Component} from "react";
import {Dimensions, Platform, StyleSheet, View} from "react-native";
import JwPlayerWrapperView from "./src/JwPlayerWrapperView";
import AndroidNativePlayer from "./src/AndroidNativePlayer";

let { width } = Dimensions.get("window");

const videoWidth = width;
const videoHeight = width / (16 / 9);

export default class App extends Component {
  state = {
    widthX: videoWidth,
    heightX: videoHeight
  };

  render() {
    return <View style={styles.container}>{this.renderPlayer()}</View>;
  }

  renderPlayer() {
    const { heightX, widthX } = this.state;
    if (Platform.OS === "ios") {
      return (
        <View>
          <JwPlayerWrapperView
            style={{
              height: heightX,
              width: widthX
            }}
            onEnd={() => console.log("here I go")}
          />
        </View>
      );
    } else {
      return (
        <AndroidNativePlayer
          style={{
            height: heightX,
            width: widthX,
          }}
        />
      );
    }
  }
}

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#ffffff",
    paddingVertical: 50,
    paddingHorizontal: 100
  }
});
