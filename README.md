# Multiplatform Film-Finder app

This is a rather simple Kotlin tech demo. The following directories exist:

1. /water -> This is the networking layer. It's explicitly multiplatform,
so other modules are encouraged to include it as a dependency.
2. /light -> This is the navigation routing layer. It's multiplatform,
just like water.
3. /filmfinder -> This is the Android app. It contains multiple modules
within itself
4. /filmfinder/shell -> This is the Android app's UI layer.
5. /filmfinder/app -> This is the Android app's artifact module.
6. /batagur -> This is the Web frontend. It may contain multiple modules.

# Purpose

To demonstrate how to make one or more multiplatform Kotlin libraries
function within a realistic app-development scenario. Here, the goal is
to provide a browsing/searching experience for
[The Movie DB](https://developer.themoviedb.org/).

# Status

The Android app can be built independently, and offers very basic search
functionality. The Web frontend is non-functional at this time, but I plan
to connect a React basis to that in due course.
