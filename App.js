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
import { StyleSheet, View, Dimensions } from "react-native";
import JwPlayerWrapperView from "./src/JwPlayerWrapperView";

let { width, height } = Dimensions.get("window");

const videoWidth = width;
const videoHeight = width / (16 / 9);

export default class App extends Component {
  state = {
    widthX: videoWidth,
    heightX: videoHeight
  };

  render() {
    const { heightX, widthX } = this.state;
    return (
      <View style={styles.container}>
        <JwPlayerWrapperView style={{ height: heightX, width: widthX }} />
      </View>
    );
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
