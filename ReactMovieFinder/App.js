    /**
     * Sample React Native App
     * https://github.com/facebook/react-native
     *
     * @format
     * @flow strict-local
     */

    import React from 'react';
    import { NavigationContainer } from '@react-navigation/native';
    import { createNativeStackNavigator } from '@react-navigation/native-stack';
    import HomeScreen from './HomeScreen.js';
    import MovieDetailScreen from './MovieDetailScreen.js';

    import type {Node} from 'react';
    import {
      SafeAreaView,
      StatusBar,
      StyleSheet,
      Text,
      Button,
      useColorScheme,
      View,
    } from 'react-native';

    import {
      Colors,
    } from 'react-native/Libraries/NewAppScreen';

    const styles = StyleSheet.create({

    });

    const Stack = createNativeStackNavigator();

    const App: () => Node = () => {
      return (
        <NavigationContainer>
        <Stack.Navigator>
              <Stack.Screen
                name="Home"
                component={HomeScreen}
                options={{ title: 'Home' }}
              />
              <Stack.Screen name='Detail'  component={MovieDetailScreen} />
            </Stack.Navigator>
        </NavigationContainer>

      );
    };


    export default App;
