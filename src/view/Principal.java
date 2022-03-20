package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBilheteria;

public class Principal {

	public static void main(String[] args) {
		final int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for (int id = 1; id <= 300; id++) {
			Thread t = new ThreadBilheteria(id, semaforo);
			t.start();
		}
	}
}