# About

A movie-finding app backed by The Movie DB.

We use Typescript here. The project was bootstrapped with
[Create React App](https://github.com/facebook/create-react-app).

## Plan

### Routes (pages/subpages)
1. Import routes from common module
2. Each route gets its own file
3. Each route file has to declare a unique top-level function
   1. So, like, we might have a /details route with function Details()
   2. I don't know what happens with more complicated layouts, but
   it's probably safe to just have separate routes for different screen
   sizes or configurations for now.
4. Use react-router-dom in App.tsx to map the strings to the files

### Home
1. Just...make a basic home screen with nice things like stars?
2. Include search widget from another file or something
3. I don't like the idea of doing it all un-styled, but that might
be the right approach here

### Search results
1. These get their own screen
2. Don't even think about messing much with the layout

### Details screen
1. Should be the simplest layout
2. Go ham with routing and refresh/update logic

# Getting this to work

## Available scripts

In the project directory, you can run:

### `yarn start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `yarn test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `yarn build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `yarn eject`

Just search up info about CRA's eject - it just replaces
the react-scripts dependency and messes with package.json.
Whatever.

## More resources

You can learn more in the [Create React App documentation](https://create-react-app.dev/docs/getting-started/).

To learn React, check out the [React documentation](https://reactjs.org/).
