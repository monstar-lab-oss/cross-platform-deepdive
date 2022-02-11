import * as Light from 'Terrapin-light';

/**
 * Create a local alias to avoid conflict with the `Routes` value in 'react-router-dom'.
 *
 * There's probably a way to make it work with explicit @types declarations but I don't quite
 * know how to make Kotlin Multiplatform create that. Namespaces are weird.
 */
const LightRoutes = Light.com.cliabhach.terrapin.nav.Routes;

export default LightRoutes;