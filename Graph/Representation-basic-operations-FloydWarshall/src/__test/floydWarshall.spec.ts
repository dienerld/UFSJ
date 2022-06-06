import { FloydWarshall } from '../FloydWarshall';
import { Node } from '../Node';

const input = [
	[-1, 1, 2, 3, 5, 4],
	[1, 0, 2, Infinity, Infinity, Infinity],
	[2, Infinity, 0, 4, 6, Infinity],
	[3, Infinity, Infinity, 0, 10, 1],
	[5, Infinity, Infinity, 9, 0, Infinity],
	[4, 7, Infinity, Infinity, Infinity, 0],
];

const expec = [
	[-1, 1, 2, 3, 5, 4],
	[1, 0, 2, 6, 8, 7],
	[2, 12, 0, 4, 6, 5],
	[3, 8, 10, 0, 10, 1],
	[5, 17, 19, 9, 0, 10],
	[4, 7, 9, 13, 15, 0],
];

const graph: Node[] = [];

describe('initializing floydWarshall', () => {
	test('must have #convertObjectToMatrix and #floydWarshall', () => {
		const fw = FloydWarshall();
		expect(fw).toHaveProperty('convertObjectToMatrix');
		expect(fw).toHaveProperty('floydWarshall');
	});
});

describe('When running #convertObjectToMatrix and #floydWarshall', () => {
	test('expected output matrix', () => {
		const fw = FloydWarshall();
		expect(fw.convertObjectToMatrix(graph)).toEqual([[-1]]);
		expect(fw.floydWarshall(input)).toEqual(expec);
	});
});
