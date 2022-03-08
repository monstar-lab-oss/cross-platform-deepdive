// Imports go here

import { com } from '@terrapin/light';
import TimeA = com.cliabhach.terrapin.nav.TimeA

async function doStuff() {

  console.log("***Start!***\n");

  const L = global['Terrapin-light'];
  const W = global['Terrapin-water'];

  console.log("Found light? " + (L != undefined));
  console.log("Found water? " + (W != undefined));
  console.log("Found another light? ", TimeA.path);

  console.log("***Mid!***\n");

  console.log("***End!***\n");
}

doStuff();

export { doStuff }