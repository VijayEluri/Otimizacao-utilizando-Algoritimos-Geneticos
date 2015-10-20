package br.usp.eduardo.souza.melo;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

public class GA {
	/**
	 * PopAtual: Populacao Inicial da geracao atual PopNova: Populacoa gerada
	 * apos mutacao e crossover chanceMutacao: taxa de mutacao qtdgeracoes:
	 * Quantas vezes será efetuado o laço tipopop: Qual o tipo da populacao q
	 * sera criada se 1 poptrad se 2 popestr tipocross: Qual o tipo de crossover
	 * q sera utilizado tipomut:qual o tipo de mutacao q sera utilizado Fitness
	 * Medio: Arranjo do tamanho da quantidade de geracoes para armazenar o
	 * fitness medio de cada geracao
	 */
	public Populacao PopAtual;
	public double chancemutacao;
	public int qtdgeracoes;
	public int tipocross;
	public int tipomut;
	public double[] fitnessmedio;
	private int qtdcrom;
	private int tipoprob;
	private int tipopop;
	private int tiposel;
	private String outFile;

	/**
	 * Contrutor da classe
	 * 
	 * @param tpSel
	 * @param tipopop
	 *            : tipo de populacao a ser criada
	 * @param qtdGer
	 *            : Quantidade de geracoes
	 * @param tipoprob
	 *            : Tipo de Problema
	 * @param qtdcrom
	 *            : Quantidade de cromossomos a ser criado
	 * @param taxamut
	 *            : Taxa de mutação
	 * @param outFile 
	 */
	public GA(int qtdGer, int qtdcrom, int tipoprob, int tipopop, int tipomut,
			int tipocross, double taxamut, int tpSel, String outFile) {
		/**
		 * Cria a populacao tradicional
		 */
		this.PopAtual = new Pop_Trad(qtdcrom, tipoprob, tpSel);
		this.qtdgeracoes = qtdGer;
		this.fitnessmedio = new double[qtdGer];
		this.tipocross = tipocross;
		this.tipomut = tipomut;
		this.chancemutacao = taxamut;
		this.qtdcrom = qtdcrom;
		this.tipoprob = tipoprob;
		this.tipopop = tipopop;
		this.tiposel = tpSel;
		this.outFile = outFile;
	}

	public GA(int qtdGer, int tipoprob, int tipomut, int tipocross, double taxa) {
		this.qtdgeracoes = qtdGer;
		this.PopAtual = new Pop_Estru(tipoprob);
		this.tipocross = tipocross;
		this.tipomut = tipomut;
		this.chancemutacao = taxa;
	}

	/**
	 * Metodo para execucao Falta Completar
	 * 
	 * @param tipopop
	 *            tipo da pop
	 * @param tipoprob
	 *            tipo do problema
	 * @param tipocross
	 *            tipo do crossover
	 */
	public void executa() {

		NumberFormat formatter = new DecimalFormat("#0.0000");
		try {
			ICsvMapWriter writer = new CsvMapWriter(new FileWriter(
					geraFileName() + "_Indicadores.txt"),
					CsvPreference.EXCEL_PREFERENCE);
			HashMap<String, ? super Object> data1;
			String[] header = { "Populacao Atual", "Fitness Medio",
					"Fitness Maximo", "Desvio Padrao" };
			writer.writeHeader(header);
			System.out.println("\nRealizando operacoes geneticas");
			for (int i = 0; i < this.qtdgeracoes; i++) {

				if (this.tipomut == 2 && i > (this.qtdgeracoes * 0.5)) {
					this.PopAtual.avaliaTodos(2);
					this.PopAtual
							.geracao(this.tipocross, 2, this.chancemutacao);
				} else {
					this.PopAtual.avaliaTodos(1);
					this.PopAtual
							.geracao(this.tipocross, 1, this.chancemutacao);
				}

				data1 = new HashMap<String, Object>();
				data1.put(header[0], i + 1);
				data1.put(header[1],
						formatter.format(this.PopAtual.FitnessMedio));
				data1.put(header[2], formatter.format(this.PopAtual.MaxElemento
						.getavaliacao()));
				data1.put(header[3],
						formatter.format(this.PopAtual.DesvioPadrao));
				writer.write(data1, header);
			}
			writer.close();
			writer = new CsvMapWriter(new FileWriter(geraFileName()
					+ "_Cromoss.txt"), CsvPreference.EXCEL_PREFERENCE);
			int[] Max = ((Pop_Trad) this.PopAtual).MaxElemento.getValor();
			String[] header2 = { "Tarefas", "Funcionario1", "Funcionario2",
					"Funcionario3", "Funcionario4", "Funcionario5" };
			Integer k = 1;
			int j = 1;
			writer.writeHeader(header2);
			data1 = new HashMap<String, Object>();
			data1.put(header2[0], "Tarefa1");
			for (int i = 0; i < Max.length; i++) {
				data1.put(header2[j], Max[i]);
				// data1.put(header2[j],i);
				j++;
				if (j == 6) {
					k++;
					writer.write(data1, header2);
					data1 = new HashMap<String, Object>();
					data1.put(header2[0], "Tarefa" + k.toString());
					j = 1;
				}
			}
			writer.close();
		} catch (Exception e) {
			System.out
					.println("Erro:\n" + e.toString() + "\n" + e.getMessage());
		}
	}

	private String geraFileName() {
		String FileName;
		if (this.tipopop == 1)
			FileName = "PopTrad_";
		else
			FileName = "PropEstrut_";
		if (this.tipomut == 1)
			FileName = FileName + "MutTrad_";
		else
			FileName = FileName + "MutDirig_";
		FileName = this.outFile + FileName + "Cross" + this.tipocross + "_Prob"
				+ this.tipoprob + "_QtdGer" + this.qtdgeracoes + "_QtdElem"
				+ this.qtdcrom + "_Sel" + this.tiposel;

		return FileName;
	}
}
