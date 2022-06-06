import { readFileSync } from 'fs';
import { createMatrixAdj } from './createMatrixAdj';

const args = process.argv;
const path = args[args.indexOf('-i') + 1];
const pathCut = args[args.indexOf('-c') + 1];
if (!path.match('.txt')) {
  new Error('Arquivo invalido');
}

const responseFile = readFileSync(path, 'utf-8').trim().split(/\r?\n/);
const responseFileCut = readFileSync(pathCut, 'utf-8').trim().split(/\r?\n/);

// console.log(responseFile);

console.log(createMatrixAdj(responseFile));
