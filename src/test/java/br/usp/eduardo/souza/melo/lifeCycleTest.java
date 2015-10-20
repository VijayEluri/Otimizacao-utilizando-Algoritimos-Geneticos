package br.usp.eduardo.souza.melo;

import org.junit.Test;

public class lifeCycleTest {

	@Test
	public void test() {
		GA horario = new GA(10, 10, 1, 1, 1, 1, 0.5, 1, ".");
		horario.executa();
	}

}
