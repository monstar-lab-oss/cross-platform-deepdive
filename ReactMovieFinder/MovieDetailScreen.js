import React, {Component} from 'react';
import type {Node} from 'react';
import {
  realm,
  addMovie,
  getAllMovies,
  deleteAllMovies,
} from './Database.js';


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
  Alert,
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
  const onNativePress = () => {
    console.log('We will invoke the native module here!');
    if(Platform.OS == 'ios'){
      CalendarModuleFoo.createCalendarEvent('testName', 'testLocation');
    }
    else{
      CalendarModule.createCalendarEvent('testName', 'testLocation');
    }

  };
  const onSavePress = () => {
    addMovie(route.params.title)
    Alert.alert(JSON.stringify(getAllMovies()));
  };

  const onRemovePress = () => {
    deleteAllMovies()
  };
  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <Image style={styles.poster} source={{uri:'https://image.tmdb.org/t/p/original' + route.params.poster_path}}/>
      <Text>{route.params.overview}</Text>
      <Button
      title="Click to invoke your native module!"
      color="#841584"
      onPress={onNativePress}/>
    <Button
    title="Click to save movie as favorite"
    color="#841584"
    onPress={onSavePress}/>
    <Button
    title="Click to remove all favorites"
    color="#841584"
    onPress={onRemovePress}/>
    </SafeAreaView>

  );
};


export default MovieDetailScreen;
