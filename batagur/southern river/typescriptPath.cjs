/**
 * SPDX-FileCopyrightText: 2022 Philip Cohn-Cort
 *
 * SPDX-License-Identifier: APACHE-2
 */

const path = require('path');

/**
 * Utility function - returns 'input' with the characters in 'suffix' removed from its end.
 */
const dropSuffix = (input, suffix) => {
  if (input.length < suffix.length || !input.endsWith(suffix)) {
    throw new Error('The path "' + input + '" does not end with "' + suffix + '".');
  }
  return input.slice(0, input.length - suffix.length);
}


/**
 * Convert an object with 'paths' from a tsconfig.json file into the Webpack resolve.alias format.
 */
const pathsToAliases = (paths) => {
  return Object.entries(paths)
    .map(([moduleName, knownPaths]) => {
      // NB: We only define one alias path for each entry in our tsconfig.json files.
      // Maybe we could make this more robust?
      return {
        // Here we remove the last two characters (the '/*' that tsconfig requires) from both properties.
        name: dropSuffix(moduleName, '/*'),
        // Path resolution here has to match up with that in webpack.config.js
        alias: path.resolve(__dirname, dropSuffix(knownPaths[0], '/*'))
      }
    })
}

module.exports = pathsToAliases;

