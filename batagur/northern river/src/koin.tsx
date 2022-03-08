import { insertKoin } from '@terrapin/water';

/**
 * This object contains a reference to our Dependency Injection graph.
 *
 * Code in :water can read the token and use it to access DI objects
 * such as our HttpClient.
 */
const koinToken = insertKoin();

export default koinToken;