import { Console } from './Console';

const Menu = (): string[] => {
	console.log('\n============================================\n           Bem vindo ao Grafo\n============================================');
	console.log('1 - Imprimir Grafo\n2 - Pesquisa de Sucessores\n3 - Pesquisa de Antecessores\n4 - Sumário\n5 - Grau\n0 - Sair');
	const op: string[] = [];
	// eslint-disable-next-line default-case
	op.push(Console('-> (Default=0): '));
	switch (op[0]) {
	case '1':
		op.push('1');
		break;
	case '2':
	case '3':
	case '5':
		op.push(Console('Index do Vértice: '));
		break;
	case '4':
		op.push('4');
		break;
	default:
		op.push('0');
		break;
	}
	return op;
};

export { Menu };
