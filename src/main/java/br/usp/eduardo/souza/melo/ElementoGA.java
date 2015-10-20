package br.usp.eduardo.souza.melo;

public class ElementoGA 
{
	/**
	 * Valores ja fechados de acordo com o que decidimos ontem
	 * qtdfun: Quantidade de funcionarios
	 * valor: com tamanho fixo de 40 ja q são 8 tarefas
	 * tempotar: tempo de kd tarefaxz
	 */
	protected int qtdfun = 5;
	protected int[] valor=new int[40];
	protected int[] tempotar={125,45,80,110,20,35,70,15};
	protected double avaliacao;
	
	public ElementoGA(){};
	public ElementoGA(int[] genes){
		valor = genes;
	}
	
	public int[] crossover(ElementoGA outropai, int tipo){
		switch(tipo){
		default:
		case 1:
			return this.crossoverUmPonto(outropai);
		case 2:
			return this.crossoverDoisPonto(outropai);
		case 3:
			return this.crossoverUniforme(outropai);
		}
	}
	/**
	 * Funcoes basicas q ja foram criadas, talvez tenham q ser alteradas
	 * @param outroPai
	 * @return
	 */
	public int[] crossoverUmPonto(ElementoGA outroPai)
	{	
		int[] aux1 = this.getValor();
		int[] aux2 = outroPai.getValor();
		int[] retorno1 = new int[aux1.length];
		
		//ponto onde ocorrerá o crossover
		int pontoCorte = (new Double(Math.random() * this.valor.length).intValue());
		
		if(pontoCorte == aux1.length -1)
			pontoCorte--;
		if(pontoCorte == 0)
			pontoCorte++;
		for(int i =0; i < pontoCorte; i++)
			retorno1[i] = aux1[i];
		for(int i = pontoCorte; i < aux2.length; i++)
			retorno1[i] = aux2[i];
		
		return retorno1;
	}
	
	
	public int[] crossoverDoisPonto(ElementoGA outroPai)
	{
		int[] aux1 = this.getValor();
		int[] aux2 = outroPai.getValor();
		int[] retorno1 = new int[aux1.length];
		
		int pontoCorte1 = (new Double(Math.random() * this.valor.length).intValue());
		int pontoCorte2 = (new Double(Math.random() *(this.valor.length - pontoCorte1+1)).intValue());
		pontoCorte2 = pontoCorte1 + pontoCorte2;
		
		if(pontoCorte1 == pontoCorte2)
			pontoCorte2++;
		
		for(int i = 0; i < pontoCorte1; i++)
		{
			retorno1[i] = aux1[i];
		}
		
		for(int i = pontoCorte1; i < pontoCorte2; i++)
		{
			retorno1[i] = aux2[i];
		}
		
		for(int i = pontoCorte2; i < aux1.length; i++)
		{
			retorno1[i] = aux1[i];
		}		
		
		return retorno1;
		
	}
	
	
	//Método de CrossOver Uniforme
	public int[] crossoverUniforme(ElementoGA outroPai)
	{
		//Recupera os ElementoGAs que serão utilizados
		int[] aux1 = this.getValor();
		int[] aux2 = outroPai.getValor();
		//Cria os ElementoGAs filhos
		int[] retorno = new int[aux1.length];
		
		//Laço responsavel pelo crossover
		for(int i=0;i<aux1.length;i++)
		{
			//Gera um numero entre 1 e 0
			int troca=(int)Math.random()*1;
			
			//Se o valor for 0 Filho 1 recebe dados do Pai 1 e Filho 2 do Pai 2
			if(troca==0)
			{
				retorno[i]=aux1[i];
			}
			//Se o valor for 1 Filho 2 recebe dados do Pai 1 e Filho 1 do Pai 2
			else
			{
				retorno[i]=aux2[i];
			}
		}
		//Cria os ElementoGAs de resposta
		//retorna o ElementoGA dos filhos
		return retorno;
		
	}
	
	public void mutacao(double chance) {
	   int i;
	   int tamanho=this.valor.length;
	   int aux;
	   double part;
	   for(i=0;i<tamanho;i++) {		
		if (java.lang.Math.random()<chance) {		
			part = java.lang.Math.random();
			if(part<0.125)
				aux = 5;
			else if(part<0.25)
				aux = 10;
			else if(part<0.375)
				aux = 15;
			else if(part<0.5)
				aux = 20;
			else if(part<0.625)
				aux = 25;
			else if(part<0.075)
				aux = 30;
			else if(part<0.875)
				aux = 50;
			else
				aux = 100;		   		   
		   this.valor[i]=aux;
		}
	   }
	}
	
	//Verificar a possibilidade de compatibilizar esse método com o inicializarElemento da classe pai
	public void inicializarCromossomo()
	{
		//int[] percetagemTrabalho= {100,100,100,100,100} ;
		int[] porcentagem = {0, 5, 10, 15, 20, 25, 30, 50, 100};
		for(int i=0;i<valor.length;i++)
			valor[i]=porcentagem[(int)(Math.random()*8)];
	}

	
	public boolean equals(ElementoGA outro) 
	{
		int [] valor1=getValor();
		int [] valor2=outro.getValor();
        for(int i=0;i<valor1.length;i++)
        {
        	if(valor1[i]!=valor2[i])
        		return false;
        }
        return true;
    }

	public void avaliacao(){}
	
	public int[] getValor()
	{
	return (this.valor);
	}
	public double getavaliacao()
	{
		return (this.avaliacao);
	}
	public int[] getTempo()
	{
		return this.tempotar;
	}

	public int getfunc()
	{
		return this.qtdfun;
	}
	public void calculaAvaliacao(){}
	
}
