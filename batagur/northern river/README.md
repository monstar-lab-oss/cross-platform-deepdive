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

## Caveats

### Importing class-namespace hybrids

The Kotlin/JS compiler sometimes creates a kind of object
that's unusual in regular TypeScript code: a namespace that
is also a class. This happens most frequently when using
`sealed class`es, like so:

```kotlin
package com.example

sealed class A {

  object Def: A()

  class B(val input: String): A()

  class C(val output: Int): A()
}
```

This creates a TypeScript definition like

```typescript
export namespace com.example {
  class A {
    protected constructor();
    static get Def(): {
    } & com.example.A;
  }
  namespace A {
    class B extends com.example.A {
      constructor(input: string);
      get input(): string;
    }
    class C extends com.example.A {
      constructor(output: number);
      get output(): number;
    }
  }
}
```

This, in turn, can be imported into other TypeScript files
in one of four ways.

```typescript
// You always need to import the 'com' namespace first
import { com } from 'example'

// 1. Use fully-qualified name
function getAnA(): com.example.A {
  return new com.example.A.B("")
}

// 2. Use 'import =' (import equals) syntax
import A = com.example.A
import B = com.example.A.B
function getAnA(): A {
  return new B("") // or 'new A.B("")'
}

// 3. Use 'const' class-import syntax
const A = com.example.A
const B = com.example.A.B
function getAnA(): typeof A {
  return new B("") // or 'new A.B("")'
}

// 4. Use 'type' type-import syntax
type A = com.example.A
function getAnA(): A {
  return new com.example.A.B("")
}
```

While all four of the above options look subtly different,
the runtime behavior is mostly the same for all of them.

Mostly.

In cases 1, 2, and 4, everything will work as expected -
`getAnA` creates a new `B` object, and calling code sees
that object as if it were an object of type `A`. In case 3,
the `typeof A` definition combines the properties of the
class with the properties of the namespace, and you might
see warnings about `B` not having a property called `C`.

Note that the `import =` syntax on case 2 doesn't currently
work in this module. I suspect there is something wrong
with the tsconfig settings, as at runtime both `com` and
`com.example.A` are undefined. The `com` namespace can
be undefined as it's a TypeScript-only concept, but the
class `com.example.A` should definitely still exist in the
JS environment.

## More resources

You can learn more in the [Create React App documentation](https://create-react-app.dev/docs/getting-started/).

To learn React, check out the [React documentation](https://reactjs.org/).
