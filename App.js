// import React, { Component } from "react";
// import { StyleSheet, View } from "react-native";
// import PlayerContainer from "./src/components/PlayerContainer";

// const styles = StyleSheet.create({
//   container: {
//     flex: 1
//   }
// });
// export default class App extends Component {
//   render() {
//     return (
//       <View style={styles.container}>
//         <PlayerContainer />
//       </View>
//     );
//   }
// }

import React, { Component, Fragment } from "react";
import { StyleSheet, View, Dimensions, Platform, Text } from "react-native";
import JwPlayerWrapperView from "./src/JwPlayerWrapperView";
import AndroidNativePlayer from "./src/AndroidNativePlayer";

let { width, height } = Dimensions.get("window");

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
              // flex: 1
              // paddingHorizontal: 17
            }}
            onEnd={() => console.log("here I go")}
          />
          {/* <JwPlayerWrapperView
            style={{
              height: heightX,
              width: widthX
              // flex: 1
              // paddingHorizontal: 17
            }}
          /> */}
        </View>
      );
    } else {
      return (
        <AndroidNativePlayer
          // supportedOrientations={['portrait', 'landscape']}
          style={{
            height: 800,
            width: widthX,
            flex: 0
          }}
        />
      );
    }
  }
}

const styles = StyleSheet.create({
  container: {
    // flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#ffffff",
    paddingVertical: 50,
    paddingHorizontal: 100
  }
});
