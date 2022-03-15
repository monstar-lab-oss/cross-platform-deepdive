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

### Broken in Kotlin 1.6.20

#### Nested `object` types

If an exported `object` contains another `object` like so:
```kotlin
object Parent {
    object Child
}
```

then the TypeScript definition should look like this:
```
class Parent {
    static get Child(): {
    }
}
```
But the export is made as
```
const Parent: {
    static get Child(): {
    }
}
```
instead, which is invalid for a TypeScript definition
file. This has to be corrected manually after each
export.

For a more practical example, refer to `RoutesObject`, in
this module.

#### Cross-module type comparison

If you add two or more Kotlin/JS modules to a project,
then the compilation process will sever certain prototype
inheritance chains. Here's an example:

Imagine a project that has four modules in total. Three are
K/JS modules called `K1`, `K2`, and `K3`; the other one is
a TypeScript module called `T1`.

- `T1` depends on `K2` and `K3`
- `K2` depends on `K1`
- `K3` is independent of `K1` and `K2`

In this setup, `T1` can access every type that each kotlin
module exports. This works fine, and you can use built-in
tools like `instanceof` from JavaScript and TypeScript to
make sure that objects created in K/JS code have the right
type.

Let's make a change.

- `K3` depends on `K1`

Now, `T1` can import `K1`'s classes and types from either
`K2` or from `K3`. It can even use a mix of imports without
the compiler (`tsc`, in our case) complaining. The problem
arises at runtime, though - to the Node module loader a
type in `K2` is never the same as a type in `K3`. The below
code will output the text `"Oh no."`.

```kotlin
// Base.kt in :k1
open class Base {}

// Derived.kt in :k3
class Derived: Base()
```

```typescript
// some code in :t1
import { Base } from '@kjs/k2';
import { Derived } from '@kjs/k3';

const d: Base = new Derived();

if (d instanceof Base) {
  console.log("Great!");
} else {
  console.log("Oh no.");
}
```

It's quite possible that your code won't get as far as that
`console.log` - exhaustive K/JS switch statements (which
are normally supported just fine) fail at runtime with
`kotlin.NoWhenBranchMatchedException`.

Luckily, you can work around this by only using types
together when you know they're from the same exported
module.

```typescript
import { Base } from '@kjs/k3';
import { Derived } from '@kjs/k3';
```

You can also bundle all of your K/JS modules into a single
K/JS module (e.g. a hypothetical `K4` that depends on `K2`
and `K3`), and only ever import from that. If only there
was a way for the compilation process to output a single
version of `K1` for `tsc` to use, then perhaps this would
not be an issue.

### Fixed in Kotlin 1.6.20

#### Nested `object` exports

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