/**
 * SPDX-FileCopyrightText: 2022 Philip Cohn-Cort
 *
 * SPDX-License-Identifier: APACHE-2
 */

// Convert back and forth between relative and absolute paths
const path = require('path');
// Also import this helpful function I wrote to convert the paths into a resolve.alias-compatible format
// C.f. https://stackoverflow.com/a/47253895
// and the errors given when running webpack on the command line, especially
//
//  Invalid configuration object. Webpack has been initialised using a configuration object that does not match the API schema.
//    - configuration.resolve.alias should be one of these:
//      object { <key>: string } | [object { alias?, name?, onlyModule? }]
//
const pathsToAliases = require('./typescriptPath.cjs');

// This is where the typescript configuration lives. We pass it along to the ts-loader plugin here
// to make sure both webpack and non-webpack builds are consistent
const typescriptConfigFile = 'tsconfig.json'

// We import these options for use with the 'alias' property defined below.
const { compilerOptions } = require('./' + typescriptConfigFile);

const isLocal = true;

module.exports = {
  mode: isLocal ? 'development' : 'production',
  entry: './src/index.ts',
  devtool: 'source-map',
  resolve: {
    extensions: ['.js', '.jsx', '.json', '.ts', '.tsx'],
    // This 'modules' section should make importing our modules a little easier
    modules: [
      // The default config just contains 'node_modules'...
      "node_modules",
      // ...but here we add all files under the 'src' directory too
      path.resolve(__dirname, "src"),
    ],
    // TypeScript paths are defined as arrays - Webpack only supports plain strings.
    alias: pathsToAliases(compilerOptions.paths),
  },
  output: {
    libraryTarget: 'umd',
    path: path.join(__dirname, '.webpack'),
    filename: '[name].cjs',
  },
  target: 'node',
  module: {
    rules: [
      {
        // all files with a `.ts` or `.tsx` extension will be handled by `ts-loader`
        test: /\.tsx?$/,
        loader: 'ts-loader',
        options: {
          configFile: typescriptConfigFile
        }
      },
    ],
  },
};
