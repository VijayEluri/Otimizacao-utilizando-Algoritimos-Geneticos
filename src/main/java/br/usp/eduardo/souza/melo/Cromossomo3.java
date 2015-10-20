package br.usp.eduardo.souza.melo;

public class Cromossomo3 extends ElementoGA {
	// Vetores onde kd numero representa qual habilidade o funcionario tem
	// Por exemplo o funcionario 1 tem as habilidades
	// 1,3,5,8,10,11
	public int[] CapFuncionario1 = { 1, 3, 5, 8, 10, 11 };
	public int[] CapFuncionario2 = { 2, 3, 4, 5, 7, 10, 11 };
	public int[] CapFuncionario3 = { 2, 5, 6, 10 };
	public int[] CapFuncionario4 = { 4, 5, 7, 8, 9, 11 };
	public int[] CapFuncionario5 = { 1, 5, 6, 8, 9, 11 };

	// Vetores que armazenam qual a habildiade que a tarefa exige
	// por exemplo tarefa 1 necessita das habilidades
	// 1,3 e 10
	public int[] necessidades = { 1, 3, 10, 2, 5, 4, 7, 8, 1, 2, 10, 2, 9, 11,
			1, 10, 5, 6, 3, 11 };
	public int[] qtdtarefas = { 0, 3, 5, 8, 11, 14, 16, 18 };
	public boolean[] neccompletas = new boolean[necessidades.length];

	public Cromossomo3(int[] elem) {
		super(elem);
	}

	public Cromossomo3() {
		super();
	}

	// Falta arranja o jeito de penalizacao por funcionario q nao tenha a
	// caracteristica
	// Necessaria e tem tempo atribuido
	@Override
	public void calculaAvaliacao() {
		for (int i = 0; i < neccompletas.length; i++) {
			neccompletas[i] = false;
		}
		double avaliacao = 0.0;
		int func = this.getfunc();
		int[] avaliar = this.getValor();
		for (int i = 0; i < avaliar.length; i++) {
			avaliacao = avaliacao
					+ ((double) (avaliar[i] * 100) + (double) (100 * avaliar[i] * (100 - avaliar[i])));
		}

		int[] turnoempregado = new int[5];
		int[] tempotar = this.getTempo();
		int[] tempogasto = new int[tempotar.length];

		int tarnconcluida = 0;
		int penTarExcessiva = 0;
		int penEmpExcessivo = 0;
		int penNConcluida = 0;

		for (int i = 0; i < avaliar.length; i++) {
			// Armazena o tempo gasto em kd tarefa
			tempogasto[i / 5] = tempogasto[i / func] + avaliar[i];
			// Armazena o tempo gasto de kd empregado
			turnoempregado[i % 5] = turnoempregado[i % 5] + avaliar[i];
			setacaracteristica(i % 5, i / 5);
		}
		tarnconcluida = 4 * tarnconcluidas(tempogasto);
		penNConcluida = 2 * penNConcluida(tempogasto);
		penTarExcessiva = penTarExcessiva(tempogasto);
		penEmpExcessivo = penEmpExcessivo(turnoempregado);
		avaliacao = avaliacao / 100;
		avaliacao = avaliacao
				* (1 + tarnconcluida + penNConcluida + penTarExcessiva + penEmpExcessivo);
		this.avaliacao = avaliacao;

		int penalidadecar = calculapenalidade();
		// Falta colocar uma parte para penalizar no caso de o funcionario nao
		// ter as habildiades necessarias
		avaliacao = avaliacao * (1 + penalidadecar);
		this.avaliacao = 1.0 / (avaliacao + 1.0) * 100000;
		this.avaliacao = 1.0 / (avaliacao + 1.0) * 100000;

	}

	// Método que verifica se o empregado tem a caracteristica necessaria da
	// tarefa
	// e seta o valor da necessiade como true
	public void setacaracteristica(int empregado, int tarefa) {
		int fim = 0;
		if (tarefa == 7) {
			fim = 20;
		} else
			fim = qtdtarefas[tarefa + 1];

		if (empregado == 0) {
			for (int i = qtdtarefas[tarefa]; i < fim; i++) {
				for (int j = 0; j < CapFuncionario1.length; j++) {
					// se a capacidade for do mesmo numero da necessidade
					// atribui true
					if (CapFuncionario1[j] == necessidades[i])
						neccompletas[i] = true;
				}
			}
		} else if (empregado == 1) {
			for (int i = qtdtarefas[tarefa]; i < fim; i++) {
				for (int j = 0; j < CapFuncionario2.length; j++) {
					// se a capacidade for do mesmo numero da necessidade
					// atribui true
					if (CapFuncionario2[j] == necessidades[i])
						neccompletas[i] = true;
				}
			}
		} else if (empregado == 2) {
			for (int i = qtdtarefas[tarefa]; i < fim; i++) {
				for (int j = 0; j < CapFuncionario3.length; j++) {
					// se a capacidade for do mesmo numero da necessidade
					// atribui true
					if (CapFuncionario3[j] == necessidades[i])
						neccompletas[i] = true;
				}
			}

		} else if (empregado == 3) {
			for (int i = qtdtarefas[tarefa]; i < fim; i++) {
				for (int j = 0; j < CapFuncionario4.length; j++) {
					// se a capacidade for do mesmo numero da necessidade
					// atribui true
					if (CapFuncionario4[j] == necessidades[i])
						neccompletas[i] = true;
				}
			}

		} else if (empregado == 4) {
			for (int i = qtdtarefas[tarefa]; i < fim; i++) {
				for (int j = 0; j < CapFuncionario5.length; j++) {
					if (CapFuncionario5[j] == necessidades[i])
						neccompletas[i] = true;
				}
			}
		}
	}

	// Metodo que verifica quantas caracterististicas faltaram e calcula a
	// penalidade
	public int calculapenalidade() {
		int qtdnaocompletas = 0;
		for (int i = 0; i < neccompletas.length; i++) {
			if (neccompletas[i] == false)
				qtdnaocompletas++;
		}
		return qtdnaocompletas;
	}

	public int tarnconcluidas(int[] tempogasto) {
		int tarnconcluidas = 0;
		for (int i = 0; i < tempogasto.length; i++) {
			if (tempogasto[i] < tempotar[i])
				tarnconcluidas++;
		}
		return tarnconcluidas;
	}

	public int penTarExcessiva(int[] tempogasto) {
		int penalidade = 0;
		for (int i = 0; i < tempogasto.length; i++) {
			if (tempogasto[i] > tempotar[i])
				penalidade++;
		}
		return penalidade;
	}

	public int penNConcluida(int[] tempogasto) {
		int penalidade = 0;
		for (int i = 0; i < tempogasto.length; i++) {
			if (tempogasto[i] < tempotar[i])
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
