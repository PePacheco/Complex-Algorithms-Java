/*
	Disciplina: Projeto e Otimização de Algoritmos
	Trabalho 2: Programação dinâmica
	Autor: Pedro Gomes Rubbo Pacheco
*/

package DynamicProgramming;

import java.util.HashMap;

class DynamicProgramming {
	static HashMap<String, Integer> memory = new HashMap<>();
	public static void main(String[] args) {
		System.out.println(recursive(args[0], args[0].length(), 0));
		System.out.println(recursiveWithMemory(args[0], args[0].length(), 0));
	}

	public static int recursive(String entry, int entryLength, int e1) {

		if (e1 >= entryLength) {
			return 0;
		}

		if (entry.charAt(e1) == '0') {
			return 0;
		}

		if (entry.charAt(e1) == '1' && e1 == entryLength - 1) {
			return 1;
		}

		int res = 0;

		res = recursive(entry, entryLength, e1 + 1) + recursive(entry, entryLength, e1 + 2) + recursive(entry, entryLength, e1 + 3);
		
		return res;
	}

	public static int recursiveWithMemory(String entry, int entryLength, int e1) {

		if (e1 >= entryLength) {
			return 0;
		}

		if (entry.charAt(e1) == '0') {
			return 0;
		}

		if (entry.charAt(e1) == '1' && e1 == entryLength - 1) {
			return 1;
		}

		if (memory.containsKey(entry.substring(0, e1))) {
			return memory.get(entry.substring(0, e1));
		}

		int res = 0;

		res = recursive(entry, entryLength, e1 + 1) + recursive(entry, entryLength, e1 + 2) + recursive(entry, entryLength, e1 + 3);

		memory.put(entry.substring(0, e1), res);
		
		return res;
	}

	public static int iterating(String entry, int entryLength, int e1) {

		int res = 0;

		return res;
	}

}