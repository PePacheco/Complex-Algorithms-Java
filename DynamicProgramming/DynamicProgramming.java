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
		System.out.println("Recursivo: " + recursive(args[0], args[0].length(), 0, true));
		System.out.println("Com memória: " + recursiveWithMemory(args[0], args[0].length(), 0, true));
		System.out.println("Iterando: " + notRecursive(args[0], args[0].length()));
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

	public static int notRecursive(String entry, int entryLength) {
		int res = 1;

		HashMap<String, Integer> memory = new HashMap<>();
		boolean wasTriple = false;

		for (int i = 0; i < entryLength; i++) {
			if (i == 0 ){
				memory.put(Integer.toString(i) + String.valueOf(wasTriple), res);
			}
			int d1 = 0, d2 = 0, t1 = 0, t2 = 0;
			if (i < entryLength && entry.charAt(i) == '1') {
				if (memory.containsKey(Integer.toString(i -1) + String.valueOf(wasTriple)) && entry.charAt(i - 1) == '1') {
					d1 += memory.get(Integer.toString(i-1) + String.valueOf(wasTriple));
					// System.out.println("D1: "+ d1);
				} 
				if (memory.containsKey(Integer.toString(i-2) + String.valueOf(wasTriple)) && entry.charAt(i - 2) == '1') {
					d2 += memory.get(Integer.toString(i-2) + String.valueOf(wasTriple));
					// System.out.println("D2: " + d2);
				}

				if (i > 2) {
					memory.put(Integer.toString(i) + String.valueOf(!wasTriple), memory.get(Integer.toString(i-3) + String.valueOf(wasTriple)));
				}

				if (memory.containsKey(Integer.toString(i-1) + String.valueOf(!wasTriple)) && entry.charAt(i - 1) == '1') {
					t1 += memory.get(Integer.toString(i-1) + String.valueOf(!wasTriple));
					// System.out.println("T1: " + t1);
				}

				if (memory.containsKey(Integer.toString(i-2) + String.valueOf(!wasTriple)) && entry.charAt(i - 2) == '1') {
					t2 += memory.get(Integer.toString(i-2) + String.valueOf(!wasTriple));
					// System.out.println("T2: " + t2);
				}

				res = t1 + t2 + d1 + d2;
				if (i != 0) {
					memory.put(Integer.toString(i) + String.valueOf(wasTriple), res);
				}
				if (i == entryLength - 1 && memory.containsKey(Integer.toString(i-3) + String.valueOf(wasTriple))) {
					res += memory.get(Integer.toString(i-3) + String.valueOf(wasTriple));
				}
			}
		}
		return res;
	}

}