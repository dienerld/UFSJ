import { readFile } from 'fs';
import { exit } from 'process';
import { Core } from './Core';

const args = process.argv;
const core = Core();
const path = args[args.indexOf('-i') + 1];
const option = !!args[args.indexOf('-s')];
if (args.length <= 2) {
	console.log(`
-------------------------
          Ajuda
-------------------------
program [option] -i example.txt

-i recebe input com dados de entrada
-s oculta menu para informações do vertice e impressão do grafo
	`);
	exit(0);
}

const File = () => {
	let response: string[] = [];
	try {
		readFile((path), 'utf-8', (err, data) => {
			if (err) throw err;
			response = data.split(/\r?\n/);
			if (response[0] === '') {
				throw new Error('Sem dados inseridos');
			}
			while (response[response.length - 1].length <= 0) {
				response.pop();
			}
			core.START(response, option);
		});
	} catch (error) {
		console.log(error);
	}
};

File();
