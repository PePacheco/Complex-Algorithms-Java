/*
	Disciplina: Projeto e Otimização de Algoritmos
	Trabalho 2: Programação dinâmica
	Autor: Pedro Gomes Rubbo Pacheco
*/

package DynamicProgramming;

import java.util.HashMap;

import javax.print.DocFlavor.STRING;

class DynamicProgramming {
	static HashMap<String, Integer> memory = new HashMap<>();
	public static void main(String[] args) {
		System.out.println("Recursivo: " + recursive(args[0], args[0].length(), 0, true));
		System.out.println("Com memória: " + recursiveWithMemory(args[0], args[0].length(), 0, true));
		// System.out.println("Iterando: " + iterating(args[0], args[0].length(), 0));
	}

	public static int recursive(String entry, int entryLength, int e1, boolean wasNot3Jump) {

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

		res = recursive(entry, entryLength, e1 + 1, true) + recursive(entry, entryLength, e1 + 2, true);
		if (wasNot3Jump) {
			res += recursive(entry, entryLength, e1 + 3, false);
		}
		
		return res;
	}

	public static int recursiveWithMemory(String entry, int entryLength, int e1, Boolean wasNot3Jump) {

		if (e1 >= entryLength) {
			return 0;
		}

		if (entry.charAt(e1) == '0') {
			return 0;
		}

		if (entry.charAt(e1) == '1' && e1 == entryLength - 1) {
			return 1;
		}

		if (memory.containsKey(entry.substring(0, e1) + String.valueOf(wasNot3Jump))) {
			return memory.get(entry.substring(0, e1) + String.valueOf(wasNot3Jump));
		}

		int res = 0;

		res = recursiveWithMemory(entry, entryLength, e1 + 1, true) + recursiveWithMemory(entry, entryLength, e1 + 2, true);
		if (wasNot3Jump) {
			res += recursiveWithMemory(entry, entryLength, e1 + 3, false);
		}

		memory.put(entry.substring(0, e1) + String.valueOf(wasNot3Jump), res);
		
		return res;
	}

	public static int iterating(String entry, int entryLength, int e1) {

		int res = 0;

		return res;
	}

}