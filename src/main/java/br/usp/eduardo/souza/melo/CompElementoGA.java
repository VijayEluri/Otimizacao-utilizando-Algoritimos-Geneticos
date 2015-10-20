package br.usp.eduardo.souza.melo;

import java.util.Comparator;

public class CompElementoGA implements Comparator<ElementoGA> {

	public int compare(ElementoGA arg0, ElementoGA arg1) {
		if (arg0.getavaliacao() > arg1.getavaliacao())
			return 1;
		if (arg0.getavaliacao() < arg1.getavaliacao())
			return -1;
		return 0;
	}	

}
