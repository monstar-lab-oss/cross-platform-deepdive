# Light

This is the routing module. It can provide constants
and some basic functions for various platforms to use.

# Understanding Kotlin/JS

## Misconceptions

The biggest issue that can arise when looking at the
platform for the first time, is the meaning of the
'browser' and 'nodejs' configs. These are blocks that
go in the module's build.gradle file, and look like
so:

```groovy
kotlin {
    js(IR) {
        browser {
            // ... config goes here
        }
    }
}
```

```groovy
kotlin {
    js(IR) {
        nodejs {
            // ... config goes here
        }
    }
}
```

I basically always use NodeJS to build my javascript and
typescript projects. I pull in dependencies with Yarn or
npx or (rarely) npm, configure some transpilation with
tsc and Babel, then pack the thing up with Webpack. This
is how I was taught, and frankly I think it's a lot of
extra stuff for something that yarn should be able to do
by itself out of the box. But I digress. The key point I
want to make is that literally every backend project uses
NodeJS, and literally every frontend project uses NodeJS,
so what is the point of the 'browser' config?

My immediate response is that this was just a nice alias
for the real distinction we care about: is this a
frontend project or a backend project? Clearly 'browser'
means frontend, and 'nodejs' means backend. Kudos to
JetBrains for keeping some semantic meaning here without
causing confusion with 'kotlin/jvm' or 'kotlin/native',
as surely that is what must have happened when someone
proposed using the terms 'frontend' and 'backend'
directly.

Except it doesn't work like that. The 'browser' config is
for using the raw output of the Kotlin build process in
your HTML files as-is. It doesn't work with ES modules or
AMD modules or CommonJS modules or whatever they call
those things nowadays, or at least it only works in the
way VanillaJS imports do. I think. Webpack is in there too
somewhere, but I don't know what that's doing. Compiling
all the NPM dependencies into a single file, perhaps? It's
remarkably unclear.

Of course the package.json goes somewhere else entirely -
I am not certain whether that is a programming mistake or
some kind of statement about its use.

## Things that don't work

### Fixed in Kotlin 1.6.20

You can't export nested `object`s. So for code like
```kotlin
@JsExport
object A {
    val B
    object C {}
    class D {}
}

@JsExport
val e = A.C
```
You'll be able to access `A`, `A.B`, and `e`, but not `A.C`
or `A.D`. This might sound boring, until you realize that we
use constructs like `A.C` all the time, in the form of
```kotlin
object MyObject {
    companion object {
        const val IMPORTANT_VALUE = 4
    }
}
```