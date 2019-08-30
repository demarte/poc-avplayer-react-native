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

import React, { Component } from "react";
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
      return <JwPlayerWrapperView style={{ height: heightX, width: widthX }} />;
    } else {
      return <AndroidNativePlayer style={{ height: heightX, width: widthX }} />;
    }
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  }
});
