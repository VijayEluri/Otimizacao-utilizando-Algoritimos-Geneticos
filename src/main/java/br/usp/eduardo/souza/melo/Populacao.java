package br.usp.eduardo.souza.melo;

public abstract class Populacao {

	public double FitnessMedio;

	public ElementoGA MaxElemento;

	public double DesvioPadrao;

	public abstract void avaliaTodos(int tipomut);

	public abstract void geracao(int tipocross, int tipomut, double taxamut);

}
