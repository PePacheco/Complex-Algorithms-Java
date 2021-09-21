/*
	Disciplina: Projeto e Otimização de Algoritmos
	Trabalho 1: Divisão e conquista
	Autor: Pedro Gomes Rubbo Pacheco
*/

package TripleKaratsuba;

import java.lang.Math;

class KaratsubaTriplo {
	public static void main(String[] args) {
		System.out.println(run(args[0], args[1]));
	}

	// Adicionando 0s a esquerda
	public static String addZerosLeft(String number, int lenght) {
		while(number.length() < lenght) {
			number = "0" + number;
		}
		return number;
	}

	// Função para soma de duas Strings
	public static String sum(String n1, String n2) {
		int size = n1.length();
		String ret = "";
		int carry = 0;
		if(n2.length() > size) {
			size = n2.length();
			n1 = addZerosLeft(n1, size);
		}
		n2 = addZerosLeft(n2, size);

		// Loop passando por todos os digitos dos numeros da esquerda para a direita
		for(int i = size -1; i >= 0; i--) {
			int helper = Integer.parseInt(Character.toString(n1.charAt(i))) + Integer.parseInt(Character.toString(n2.charAt(i)));
			if(carry == 1) { // Caso o carry passado seja um, somar mais um no número, pois veio da soma passada
				helper++;
				carry = 0;
			}
			if(helper >= 10) { // Caso a soma seja maior que 10, enviar para o próximo digito um a mais
				carry = 1;
				helper -= 10;
			}
			// Somando o valor total ao digito na posição correspondente
			ret = String.valueOf(helper) + ret;
		}
		if(carry == 1) { // Caso o carry seja 1, passar um para a esquerda
			ret = "1" + ret;
		}

		return ret;
	}

	// Adicionando 0s a direita
	public static String addZerosRight(String n, int size) {
		for(int i = 0; i < size ; i++) {
			n += "0";
		}
		return n;
	}

	public static String run(String firstNumber, String secondNumber) { // Função principal recursiva
		int n1 = firstNumber.length();
		int n2 = secondNumber.length();
		firstNumber = addZerosLeft(firstNumber, n2);
		secondNumber = addZerosLeft(secondNumber, n1);
		if(n1 < 3 && n2 < 3) { // Se não der para dividir mais por 3, retorna a multiplicação
			return String.valueOf(Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
		}

		// As potencias a serem aplicadas na multiplicação e o deslocamento para partir os numeros
		int nMax = Math.max(n1, n2);
		int e2 = (int) Math.floor(nMax/3);
		int e1 = e2 * 2;

		// A separação dos numeros em tres partes
		String firstNumberP1 = firstNumber.substring(0, e2);
		String firstNumberP2 = firstNumber.substring(e2, e1);
		String firstNumberP3 = firstNumber.substring( e1, firstNumber.length());

		String secondNumberP1 = secondNumber.substring(0, e2);
		String secondNumberP2 = secondNumber.substring(e2, e1);
		String secondNumberP3 = secondNumber.substring(e1, secondNumber.length());

		// Criando os shifts para a esquerda do P1
		int shitP1P1 = (firstNumber.length() - firstNumberP1.length()) + (secondNumber.length() - secondNumberP1.length());
		int shitP1P2 = (firstNumber.length() - firstNumberP1.length()) + (secondNumber.length() - secondNumberP1.length() - secondNumberP2.length());
		int shitP1P3 = (firstNumber.length() - firstNumberP1.length());
		
		// Aplicando os shifts para a esquerda do P1
		String P1P1 = addZerosRight(run(firstNumberP1, secondNumberP1), shitP1P1);
		String P1P2 = addZerosRight(run(firstNumberP1, secondNumberP2), shitP1P2);
		String P1P3 = addZerosRight(run(firstNumberP1, secondNumberP3), shitP1P3);

		// Criando os shifts para a esquerda do P2
		int shiftP2P1 = (firstNumber.length() - firstNumberP1.length() - firstNumberP2.length()) + (secondNumber.length() - secondNumberP1.length());
		int shiftP2P2 = (firstNumber.length() - firstNumberP1.length() - firstNumberP2.length()) + (secondNumber.length() - secondNumberP1.length() - secondNumberP2.length());
		int shiftP2P3 = (firstNumber.length() - firstNumberP1.length() - firstNumberP2.length());

		// Aplicando os shifts para a esquerda do P2
		String P2P1 = addZerosRight(run(firstNumberP2, secondNumberP1), shiftP2P1);
		String P2P2 = addZerosRight(run(firstNumberP2, secondNumberP2), shiftP2P2);
		String P2P3 = addZerosRight(run(firstNumberP2, secondNumberP3), shiftP2P3);

		// Criando os shifts para a esquerda do P3
		int shiftP3P1 = (secondNumber.length() - secondNumberP1.length());
		int shiftP3P2 = (secondNumber.length() - secondNumberP1.length() - secondNumberP2.length());
		int shiftP3P3 = 0;

		// Aplicando os shifts para a esquerda do P3
		String P3P1 = addZerosRight(run(firstNumberP3, secondNumberP1), shiftP3P1);
		String P3P2 = addZerosRight(run(firstNumberP3, secondNumberP2), shiftP3P2);
		String P3P3 = addZerosRight(run(firstNumberP3, secondNumberP3), shiftP3P3);

		// Primeira soma
		String firstSum = sum(P3P2, P3P3);

		// O resto das somas
		String ret = sum(P1P1, sum(P1P2, sum(P1P3, sum(P2P1, sum(P2P2, sum(P2P3, sum(P3P1, firstSum)))))));

		// Retirando os 0 a esquerda do resultado final
		return ret.replaceFirst("^0+(?!$)", "");
	}
}