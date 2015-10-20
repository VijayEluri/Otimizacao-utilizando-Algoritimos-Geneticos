package br.usp.eduardo.souza.melo;

public class Cromossomo1 extends ElementoGA {

	public Cromossomo1() {
		super();
	}

	public Cromossomo1(int[] elem) {
		super(elem);
	}

	/**
	 * Método de Calculo do valor da avaliacao para o cromossomo em avaliacao
	 */
	@Override
	public void calculaAvaliacao() {
		// Variavel inicializada com valor 0.0
		double avaliacao = 0.0;
		// Variavel que armazena o tempo das funcionalidades
		int func = this.getfunc();
		// Variavel que armazena o gene a ser avaliado
		int[] avaliar = this.getValor();

		for (int i = 0; i < avaliar.length; i++)
			// Calcula o custo de acordo com o metodo da professora
			avaliacao = avaliacao
					+ (((avaliar[i] * 100) + (100 * avaliar[i] * (100 - avaliar[i]))));
		// Cria uma variavel para armazenar quanto kd empregado trabalhou
		int[] turnoempregado = new int[5];
		// Cria uma variavel para armazenar quanto kd tarefa precisa
		int[] tempotar = this.getTempo();
		// Cria uma variavel para armazenar quanto kd tarefa ocupou
		int[] tempogasto = new int[tempotar.length];
		// Armazena a quantidade de tarefas nao concluidas
		int tarnconcluida = 0;

		// Armazena a penalidade que sera feita por tarefas nao concluidas
		int penNConcluida = 0;

		// Armazena a penalidade que sera feita por tarefa acima do tempo
		int penTarExcessiva = 0;

		// Armazena a penalidade que sera feita por empregado trabalhando mais
		// de 100 horas
		int penEmpExcessivo = 0;

		for (int i = 0; i < avaliar.length; i++) {
			// Armazena o tempo gasto em kd tarefa
			tempogasto[i / 5] = tempogasto[i / func] + avaliar[i];
			// Armazena o tempo gasto de kd empregado
			turnoempregado[i % 5] = turnoempregado[i % 5] + avaliar[i];
		}
		tarnconcluida = 4 * tarnconcluidas(tempogasto);
		penNConcluida = 2 * penNConcluida(tempogasto);
		penTarExcessiva = penTarExcessiva(tempogasto);
		penEmpExcessivo = penEmpExcessivo(turnoempregado);
		avaliacao = avaliacao / 100;
		avaliacao = avaliacao
				* (tarnconcluida + penNConcluida + penTarExcessiva + penEmpExcessivo);
		this.avaliacao = 1.0 / (avaliacao + 1.0) * 100000;
	}

	public int tarnconcluidas(int[] tempogasto) {
		int tarnconcluidas = 0;
		for (int i = 0; i < tempogasto.length; i++) {
			if (tempogasto[i] < tempotar[i])
				tarnconcluidas++;
		}
		return tarnconcluidas;
	}

	public int penNConcluida(int[] tempogasto) {
		int penalidade = 0;
		for (int i = 0; i < tempogasto.length; i++) {
			if (tempogasto[i] < tempotar[i])
				penalidade++;
		}
		return penalidade;
	}

	public int penTarExcessiva(int[] tempogasto) {
		int penalidade = 0;
		for (int i = 0; i < tempogasto.length; i++) {
			if (tempogasto[i] > tempotar[i])
				penalidade++;
		}
		return penalidade;
	}

	public int penEmpExcessivo(int[] turnoempregado) {
		int penalidade = 0;
		for (int i = 0; i < turnoempregado.length; i++) {
			if (turnoempregado[i] > 100)
				penalidade++;
		}
		return penalidade;
	}

}
