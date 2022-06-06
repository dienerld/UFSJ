import rls from 'readline-sync';

// // Wait for user's response.
// const userName = rls.question('May I have your name? ');
// console.log(`Hi ${userName}!`);

// // Handle the secret text (e.g. password).
// const favFood = rls.question('What is your favorite food? ', {
// 	hideEchoBack: true, // The typed text on screen is hidden by `*` (default).
// });
// console.log(`Oh, ${userName} loves ${favFood}!`);

export const Console = (title = '-> '): string => {
	const res = 	rls.question(title);
	console.log('________________________________________\n');

	return res;
};
