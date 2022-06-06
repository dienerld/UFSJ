import rls from 'readline-sync';

export const Console = (title = '-> '): string => {
	const res = rls.question(title);
	console.log('________________________________________\n');

	return res;
};
