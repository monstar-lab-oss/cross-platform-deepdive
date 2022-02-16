import React, {Component} from 'react';
import MainMovieList from './MainMovieList.js';
import type {Node} from 'react';

import {
  FlatList,
  ActivityIndicator,
  TouchableOpacity,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  Button,
  Image,
  useColorScheme,
  View,
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
  sectionContainer: {
    marginTop: 32,
    flex: 1,
    flexDirection: 'column',
    paddingHorizontal: 24,
  },
  columns: {

    justifyContent: 'space-around',
  },
  item: {
    width: 100,
    flex: 1,
    flexWrap: 'wrap',
  },
  poster: {
   width: 150,
   height: 200,
 },
});


class Test extends Component {

  constructor(props) {
    super(props);

    this.state = {
      data: [],
      isLoading: true,
      count: 0,

    };
  }

  async getMovies() {
      try {
      //  const response = await fetch('https://reactnative.dev/movies.json');
        const response = await fetch('http://api.themoviedb.org/3/discover/movie?api_key=4cb1eeab94f45affe2536f2c684a5c9e');
        const jsonResponse = await response.json();
        this.setState({ data: jsonResponse.results });
      } catch (error) {
        console.log(error);
      } finally {
        this.setState({ isLoading: false });
      }
    }

  componentDidMount() {
      this.getMovies();
    }


  onPress = () => {
    this.setState({
      count: this.state.count + 1
    })
  }

  getListViewItem = (item) => {
        //Alert.alert(item.title);
        //navigation.navigate('Profile', { name: item.title })
        const { navigate } = this.props.navigation;
        navigate('Detail', { overview: item.overview ,poster_path:item.poster_path })
    }

 render() {
   const { data, isLoading } = this.state;

    return (
      <SafeAreaView style={styles.sectionContainer}>
      {isLoading ? <ActivityIndicator/> : (
         <FlatList
           columnWrapperStyle={styles.columns}
           numColumns={2}
           data={data}
           keyExtractor={({ id }, index) => id}
           renderItem={({ item }) => (
             //source:{uri:'https://image.tmdb.org/t/p/original/k2twTjSddgLc1oFFHVibfxp2kQV.jpg'}>
             <TouchableOpacity
              style={styles.button}
              onPress={this.getListViewItem.bind(this, item)}
             >
            <View style={styles.container} >

            <Image style={styles.poster} source={{uri:'https://image.tmdb.org/t/p/original' + item.poster_path}}/>
              <Text style={styles.item}>
                {item.title}{"\n"}{item.release_date}
              </Text>
            </View>
            </TouchableOpacity>

           )}
         />
       )}
        <TouchableOpacity
         style={styles.button}
         onPress={this.onPress}
        >
         <Text>Click me</Text>
        </TouchableOpacity>
        <View >
          <Text>
            You clicked { this.state.count } times
          </Text>
        </View>
      </SafeAreaView>
    )
  }
}


const HomeScreen = ({ navigation }) => {
  const isDarkMode = useColorScheme() === 'dark';
  const backgroundStyle = {
    flex: 1,
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <MainMovieList />
      <Button
        title="Go to Jane's profile"
        onPress={() =>
          navigation.navigate('Profile', { name: 'Jane' })
        }/>

    </SafeAreaView>

  );
};

export default Test;
