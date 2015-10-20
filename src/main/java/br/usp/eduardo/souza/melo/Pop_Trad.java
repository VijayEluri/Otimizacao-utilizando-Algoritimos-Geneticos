package br.usp.eduardo.souza.melo;

import java.util.*;

public class Pop_Trad extends Populacao {
/**
 * somaavaliacao Armazena a soma de todas as avaliacoes dos cromossomos pertencentes
 * a essa geracao
 * tamanhopopulacao A Quantidade de Cromossomos por geracao
 */
	public ElementoGA[] membros;
	public double somaavaliacao;
	public int tamanhopopulacao;
	public int tipoprob;
	private int[] mutDir_Masc;
	private double nMaiores_Masc;
	private int tiposel;
	
	/**
	 * PopTrad construtor que recebe a quantidade de cromossomos e o tipo de cromossomo
	 * que tera q ser criado
	 * @param tiposel 
	 */
	
	public Pop_Trad(int tamanho,int tipoprob, int tiposel)
	{
		this.somaavaliacao=0.0;
		this.tamanhopopulacao=tamanho;
		this.FitnessMedio=0.0;
		this.tiposel = tiposel;
		if(tipoprob==1){
			this.membros=new Cromossomo1[tamanho];
			for(int i=0;i<membros.length;i++){
				this.membros[i] = new Cromossomo1();
				this.membros[i].inicializarCromossomo();
			}
		}
		if(tipoprob==2){
			this.membros=new Cromossomo2[tamanho];
			for(int i=0;i<membros.length;i++){
				this.membros[i] = new Cromossomo2();
				this.membros[i].inicializarCromossomo();
			}
		}
		if(tipoprob==3){
			this.membros=new Cromossomo3[tamanho];
			for(int i=0;i<membros.length;i++){
				this.membros[i] = new Cromossomo3();
				this.membros[i].inicializarCromossomo();
			}
		}
		if(tipoprob==4){
			this.membros=new Cromossomo4[tamanho];
			for(int i=0;i<membros.length;i++){
				this.membros[i] = new Cromossomo4();
				this.membros[i].inicializarCromossomo();
			}
		}
		this.tipoprob=tipoprob;
	}
	
	//8 primeiros valores equivalem aos valores que podem ser assumidos pelo primeiro gene
	//{125,45,80,110,20,35,70,15};
	public int[] geraMasc(Double limiar){
		int sum = 0;
		double[] aux = new double[360]; 
		int perc =+ 1/this.membros.length;
		for(int i=this.tamanhopopulacao-limiar.intValue();i<this.tamanhopopulacao;i++){
				for(int j=0;j<this.membros[i].getValor().length;j++){
					switch(this.membros[i].getValor()[j]){
						case 100:
							sum++;
						case 50:
							sum++;
						case 30:
							sum++;
						case 25:
							sum++;
						case 20:
							sum++;
						case 15:
							sum++;
						case 10:
							sum++;
						case 5:
							sum++;
					}
					aux[j*9+sum] =+ perc;
					sum = 0;
				}
		}
		int[] mascara = new int[40];
		for (int i=0;i<aux.length;i++){
			Double indice = Math.floor(i/9.0);
			if(aux[i]>0.75)
				mascara[indice.intValue()] = 1;
			else
				mascara[indice.intValue()] = 0;
		}
		return mascara;
	}
	
	public void mutacao_dirigida(){
		Double aux = this.nMaiores_Masc;
		for(int i=aux.intValue();i<this.membros.length;i++)
			for(int j=0;j<this.membros[i].getValor().length;j++)
				if(this.mutDir_Masc[j] == 1)
					this.membros[i].mutacao(0.5);
	}
	
	@Override
	public void geracao(int tipocross,int tipomut,double taxamut) 
	{
		ElementoGA[] nova = new ElementoGA[tamanhopopulacao];
		ElementoGA pai1,pai2;
		int i;
		System.out.print(".");
		for(i=0;i<membros.length;++i) {
			if (this.tiposel == 1){
				pai1 =  membros[this.roleta()];
				pai2 =  membros[this.roleta()];
			}
			else{
				Double aux = this.tamanhopopulacao*0.05;
				pai1 = membros[this.torneio(aux.intValue())];
				pai2 = membros[this.torneio(aux.intValue())];
			}
					
			nova[i] = new ElementoGA();
			switch(this.tipoprob){
				default:
				case 1:
					nova[i] = new Cromossomo1(pai1.crossover(pai2,tipocross));
					break;
				case 2:
					nova[i] = new Cromossomo2(pai1.crossover(pai2,tipocross));
					break;
				case 3:
					nova[i] = new Cromossomo3(pai1.crossover(pai2,tipocross));
					break;
				case 4:
					nova[i] = new Cromossomo4(pai1.crossover(pai2,tipocross));
					break;
			}
			if(tipomut==1)
				nova[i].mutacao(taxamut);
	}
		this.membros = nova;
		if(tipomut == 2)
			mutacao_dirigida();
	}
	/**
	 * Metodo que verifica qual o cromossomo que tem o melhor fitness e retorna a posicao dele
	 * dentro do vetor membros da populacao atual
	 * @return
	 */
	public int DeterminaMelhor(ElementoGA[] membros)
	{
		int i=0;
		for(int j=0;j<membros.length;j++)
		{
			if(membros[j].avaliacao<=membros[i].avaliacao)
				i=j;
		}
		return i;
	}
	
	public int roleta() {
		//NumberFormat formatter = new DecimalFormat("#0.000000");
		int i;
		double aux=0.0;
		double limite=Math.random()*this.somaavaliacao;	
		for(i=0;( (i<membros.length)&&(aux<limite) );++i) {
		   aux+=membros[i].getavaliacao();
		}
		/*Como somamos antes de testar, então tiramos 1 de i pois
		  o anterior ao valor final consiste no elemento escolhido*/
		i--;	
			return(i);
	}
	
	public int torneio(int tamanhoTorneio) 
	{
		// tamanho inválido do torneio
		if (tamanhoTorneio < 2) {
			return -1;
		}

		// Lista do participantes do torneio
		ElementoGA[] torneio=new ElementoGA[tamanhoTorneio];
		// indivíduo selecionado
		int individuoSelecionado;

		// Escolherei entre os indivíduos da população para participar do
		// torneio, inserindo-os na lista dos participantes que competirão entre si
				
		for (int i = 0; i < tamanhoTorneio; i++) {
			
			// Um indivíduo pode ser selecionado mais de uma vez para o torneio, de acordo com o algoritmo.
			// Seleção do i-ésimo indivíduo dentro da minha lista da população.
			individuoSelecionado = (int) (Math.random() * this.membros.length);

			torneio[i]=membros[individuoSelecionado];
		}

		// Retorna o índice do indívíduo selecionado do torneio que contém a melhor avaliação.
		return DeterminaMelhor(torneio);
	}
	
	@Override
	public void avaliaTodos(int tipomut) {
		Comparator<ElementoGA> comp = new CompElementoGA();
		Arrays.sort(membros,comp);
		super.MaxElemento = this.membros[0];
		this.somaavaliacao = 0;
		for(int i=0;i<this.tamanhopopulacao;++i){
			this.membros[i].calculaAvaliacao();
			this.somaavaliacao+=this.membros[i].getavaliacao();
			if(this.membros[i].getavaliacao() > super.MaxElemento.getavaliacao())
				super.MaxElemento = this.membros[i];
		}
		double media = this.somaavaliacao/this.tamanhopopulacao;
		double dp = 0;
		for(int i=0;i<this.tamanhopopulacao;i++)
			dp =+ Math.pow((this.membros[i].getavaliacao()-media),2);
		dp = Math.sqrt(dp/(this.tamanhopopulacao-1));
		super.FitnessMedio = media;
		super.DesvioPadrao = dp;
		if(tipomut == 2){
			this.nMaiores_Masc = this.tamanhopopulacao*0.15;
			this.mutDir_Masc = geraMasc(this.nMaiores_Masc);
		}
	 }
}
