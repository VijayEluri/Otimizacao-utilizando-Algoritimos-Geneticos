package br.usp.eduardo.souza.melo;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Quantidade de gerações Tipo do problema Quantidade de cromossomos
		 * Tipo de Mutação Tipo de Crossover Taxa de Mutacao
		 */

		Interface inter = new Interface();
		String[] ent = inter.exec();
		GA horario = new GA(Integer.parseInt(ent[0]), Integer.parseInt(ent[1]),
				Integer.parseInt(ent[2]), Integer.parseInt(ent[3]),
				Integer.parseInt(ent[4]), Integer.parseInt(ent[5]),
				Double.parseDouble(ent[7]), Integer.parseInt(ent[6]), ent[8]);
		horario.executa();
		System.out.println("\nExecucao finalizada.\nEncontre os resultados em "+ent[8]);
	}

}
