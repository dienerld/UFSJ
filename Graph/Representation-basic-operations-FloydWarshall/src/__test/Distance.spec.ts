import { Distances } from '../Distance';

const Matrix = [
	[-1, 1, 2, 3, 5, 4],
	[1, 0, 2, 6, 8, 7],
	[2, 12, 0, 4, 6, 5],
	[3, 8, 10, 0, 10, 1],
	[5, 17, 19, 9, 0, 10],
	[4, 7, 9, 13, 15, 0],
];

describe('Initializing Distance', () => {
	test('must have #distance', () => {
		const Distance = Distances();
		expect(Distance).toHaveProperty('logDistances');
	});
});
describe('expected distance', () => {
	test('', () => {
		const Distance = Distances();
		expect(Distance.logDistances(Matrix)).not.toBeNull();
	});
});
