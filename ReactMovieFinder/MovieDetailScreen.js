import React, {Component} from 'react';
import type {Node} from 'react';
import {
  SafeAreaView,
  StatusBar,
  StyleSheet,
  Text,
  Button,
  Image,
  useColorScheme,
  View,
  NativeModules,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

const styles = StyleSheet.create({
  highlight: {
    fontWeight: '700',
  },
  poster: {
   width: '100%',
   height: '50%',
 },
});
//<Text>This is {route.params.name}'s profile</Text>;


const MovieDetailScreen = ({ navigation, route }) => {
  const { CalendarModule } = NativeModules;
  const { CalendarModuleFoo } = NativeModules;

  const isDarkMode = useColorScheme() === 'dark';
  const backgroundStyle = {
    flex: 1,
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };
  const onPress = () => {
    console.log('We will invoke the native module here!');
    if(Platform.OS == 'ios'){
      CalendarModuleFoo.createCalendarEvent('testName', 'testLocation');
    }
    else{
      CalendarModule.createCalendarEvent('testName', 'testLocation');
    }

  };
  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <Image style={styles.poster} source={{uri:'https://image.tmdb.org/t/p/original' + route.params.poster_path}}/>
      <Text>{route.params.overview}</Text>
      <Button
      title="Click to invoke your native module!"
      color="#841584"
      onPress={onPress}
    />
    </SafeAreaView>

  );
};


export default MovieDetailScreen;
