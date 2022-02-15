// Imports go here

require('@terrapin/light');
require('@terrapin/water');

async function doStuff() {

  console.log("***Start!***\n");

  const L = global['Terrapin-light'];
  const W = global['Terrapin-water'];

  console.log("Found light? " + (L != undefined));
  console.log("Found water? " + (W != undefined));

  console.log("***Mid!***\n");

  console.log("***End!***\n");
}

doStuff();

export { doStuff }