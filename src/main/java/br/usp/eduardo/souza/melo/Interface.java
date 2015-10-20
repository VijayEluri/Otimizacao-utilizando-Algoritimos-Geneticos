package br.usp.eduardo.souza.melo;

import java.io.*;

public class Interface {
	public String[] exec(){
		String[] result = new String[10];
		try{
			System.out.println("Digite a quantidade total de geracoes:");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
			result[0] = in.readLine();
			System.out.println("Digite a quantidade total de cromossomos:");
			result[1] = in.readLine();
			System.out.println("Digite 1,2,3 ou 4 para escolher o tipo de problema:");
			result[2] = in.readLine();
			System.out.println("Digite 1 para populacao tradicional e 2 para populacao estruturada:");
			result[3] = in.readLine();
			System.out.println("Digite 1 para mutacao tradicional e 2 para mutacao dirigida:");
			result[4] = in.readLine();
			System.out.println("Escolha um número equivalente ao tipo de crossover:\n1-Um ponto\n2-Dois pontos\n3-Uniforme");
			result[5] = in.readLine();
			System.out.println("Digite 1 para selecao por roleta e 2 para selecao por torneio:");
			result[6] = in.readLine();
			System.out.println("Digite um valor para taxa de mutacao:");
			result[7] = in.readLine();
			System.out.println("Digite um endereco para a saida dos arquivos:");
			result[8] = in.readLine();
		}
		catch (Exception e) {
			System.out.println("Erro:\n" + e.toString() + "\n" + e.getMessage());
		}
		return result;
	}
}
