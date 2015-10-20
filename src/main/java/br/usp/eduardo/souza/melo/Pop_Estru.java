package br.usp.eduardo.souza.melo;

public class Pop_Estru extends Populacao {

	/**
	 * Criacao do pocket e do current que sera utilizado pocket[0] e current[0]
	 * no caso formariam um nó unico Avaliacaopocket armazena o fitness do
	 * pocket do no em questao
	 */
	public ElementoGA[] pocket = new ElementoGA[13];
	public ElementoGA[] current = new ElementoGA[13];
	public int tipoprob;

	public Pop_Estru(int tipoprob) {
		for (int i = 0; i < 13; i++) {
			if (tipoprob == 1) {
				Cromossomo1 p1 = new Cromossomo1();
				Cromossomo1 p2 = new Cromossomo1();
				p1.inicializarCromossomo();
				p2.inicializarCromossomo();
				p1.avaliacao();
				p2.avaliacao();
				if (p1.avaliacao < p2.avaliacao) {
					this.pocket[i] = p1;
					this.current[i] = p2;
				} else {
					this.pocket[i] = p2;
					this.current[i] = p1;
				}
			} else if (tipoprob == 2) {
				Cromossomo2 p1 = new Cromossomo2();
				Cromossomo2 p2 = new Cromossomo2();
				p1.inicializarCromossomo();
				p2.inicializarCromossomo();
				p1.avaliacao();
				p2.avaliacao();
				if (p1.avaliacao < p2.avaliacao) {
					this.pocket[i] = p1;
					this.current[i] = p2;
				} else {
					this.pocket[i] = p2;
					this.current[i] = p1;
				}
			} else if (tipoprob == 3) {
				Cromossomo3 p1 = new Cromossomo3();
				Cromossomo3 p2 = new Cromossomo3();
				p1.inicializarCromossomo();
				p2.inicializarCromossomo();
				p1.avaliacao();
				p2.avaliacao();
				if (p1.avaliacao < p2.avaliacao) {
					this.pocket[i] = p1;
					this.current[i] = p2;
				} else {
					this.pocket[i] = p2;
					this.current[i] = p1;
				}
			} else if (tipoprob == 4) {
				Cromossomo4 p1 = new Cromossomo4();
				Cromossomo4 p2 = new Cromossomo4();
				p1.inicializarCromossomo();
				p2.inicializarCromossomo();
				p1.avaliacao();
				p2.avaliacao();
				if (p1.avaliacao < p2.avaliacao) {
					this.pocket[i] = p1;
					this.current[i] = p2;
				} else {
					this.pocket[i] = p2;
					this.current[i] = p1;
				}
			}
		}
		this.tipoprob = tipoprob;
		this.FitnessMedio = 0.0;
	}

	@Override
	public void geracao(int tipocross, int tipomut, double taxamut) {
		if (tipocross == 1) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					current[(i * 3) + j].valor = current[(i * 3) + j]
							.crossoverUmPonto(pocket[i]);
					current[(i * 3) + j].avaliacao();
					if (current[(i * 3) + j].avaliacao < pocket[(i * 3) + j].avaliacao) {
						ElementoGA aux = current[(i * 3) + j];
						current[(i * 3) + j] = pocket[(i * 3) + j];
						pocket[(i * 3) + j] = aux;
					}
				}
			}
		} else if (tipocross == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					current[(i * 3) + j].valor = current[(i * 3) + j]
							.crossoverDoisPonto(pocket[i]);
					current[(i * 3) + j].avaliacao();
					if (current[(i * 3) + j].avaliacao < pocket[(i * 3) + j].avaliacao) {
						ElementoGA aux = current[(i * 3) + j];
						current[(i * 3) + j] = pocket[(i * 3) + j];
						pocket[(i * 3) + j] = aux;
					}
				}
			}
		} else if (tipocross == 3) {
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					current[(i * 3) + j].valor = current[(i * 3) + j]
							.crossoverUniforme(pocket[i]);
					current[(i * 3) + j].avaliacao();
					if (current[(i * 3) + j].avaliacao < pocket[(i * 3) + j].avaliacao) {
						ElementoGA aux = current[(i * 3) + j];
						current[(i * 3) + j] = pocket[(i * 3) + j];
						pocket[(i * 3) + j] = aux;
					}
				}
			}
		}
		if (tipomut == 1) {
			int escolhido = (int) Math.random() * 13;
			current[escolhido].mutacao(taxamut);
			current[escolhido].avaliacao();
			if (current[escolhido].avaliacao < pocket[escolhido].avaliacao) {
				// realiza a troca do current pelo pocket
				ElementoGA aux = current[escolhido];
				current[escolhido] = pocket[escolhido];
				pocket[escolhido] = aux;
			}

		} else if (tipomut == 2) {
			int escolhido = (int) Math.random() * 13;
			// current[escolhido].mutacaodir(taxamut);
			current[escolhido].avaliacao();

			if (current[escolhido].avaliacao < pocket[escolhido].avaliacao) {
				// realiza a troca do current pelo pocket
				ElementoGA aux = current[escolhido];
				current[escolhido] = pocket[escolhido];
				pocket[escolhido] = aux;
			}
		}
	}

	@Override
	public void avaliaTodos(int tipomut) {
		// TODO Auto-generated method stub

	}

}
