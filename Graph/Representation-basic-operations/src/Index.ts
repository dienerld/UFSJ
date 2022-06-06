import { readFile } from 'fs';
import { Core } from './Core';

const args = process.argv;
const core = Core();
const path = args[args.indexOf('-i') + 1];

const File = () => {
	let response: string[] = [];
	readFile(path, 'utf-8', (err, data) => {
		if (err) throw err;
		response = data.split(/\r?\n/);
		while (response[response.length - 1].length <= 0) {
			response.pop();
		}

		core.START(response);
	});
};

File();
