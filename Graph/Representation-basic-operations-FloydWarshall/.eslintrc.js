module.exports = {
	env: {
		es2021: true,
		node: true,
		jest: true,
	},
	ignorePatterns: [".eslintrc.js"],
	root: true,
	extends: [
		"plugin:@typescript-eslint/recommended",
		"airbnb-base",
		"eslint:recommended",
	],
	parser: "@typescript-eslint/parser",
	parserOptions: {
		ecmaVersion: 12,
		sourceType: "module",
	},
	plugins: ["@typescript-eslint/eslint-plugin", "jest"],
	rules: {
		"jest/no-disabled-tests": "warn",
		"jest/no-focused-tests": "error",
		"jest/no-identical-title": "error",
		"jest/prefer-to-have-length": "warn",
		"jest/valid-expect": "error",
		indent: ["error", "tab"],
		"no-tabs": "off",
		"max-len": "off",
		"no-plusplus": "off",
		"linebreak-style": ["error", "unix"],
		quotes: ["error", "single"],
		semi: ["error", "always"],
		"no-console": "off",
		"import/prefer-default-export": "off",
		"import/extensions": [
			"error",
			"ignorePackages",
			{
				ts: "never",
			},
		],
		"@typescript-eslint/explicit-module-boundary-types": "off",
	},
	settings: {
		"import/resolver": {
			typescript: {},
		},
	},
};
