// Can we import the Terrapin-light type definitions safely?
// Answer: Yes!
import type { com } from '@terrapin/light';

type RoutesObjectType = typeof com.cliabhach.terrapin.nav.RoutesObject;

/**
 * Create a local alias to avoid conflict with the `Routes` value in 'react-router-dom'.
 *
 * There's probably a way to make it work with explicit @types declarations but I don't quite
 * know how to make Kotlin Multiplatform create that. Namespaces are weird.
 */
const Light = window[<any>'Terrapin-light'] as any;
const LightRoutes: RoutesObjectType = Light.com.cliabhach.terrapin.nav.RoutesObject;

export default LightRoutes;