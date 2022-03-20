package controller;

import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread {

	static int ingressos = 100;
	private int totalingressos;
	private int id;
	private int tempo;
	private Semaphore semaforo;

	public ThreadBilheteria(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if (login()) {
			if (Compra()) {
				try {
					semaforo.acquire();
					validacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		}
	}

	private boolean login() {
		tempo = (int) (Math.random() * 1951 + 50);
		try {
			if (tempo > 1000) {
				System.out.println("Login #" + id + " deu errado e não poderá realizar a compra");
			}
			sleep(tempo);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean Compra() {
		tempo = (int) (Math.random() * 2001 + 1000);
		try {
			if (tempo > 2500) {
				System.out
						.println("Tempo de entrada na sessão #" + id + " foi excedido e não poderá efeturar a compra");
				return false;
			}
			sleep(tempo);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void validacao() {
		totalingressos = (int) ((Math.random() * 4) + 1);
		if (ingressos >= totalingressos) {
			System.out.println("#" + id + " comprou " + totalingressos + " ingressos");
			ingressos -= totalingressos;
			System.out.println("Restam apenas " + ingressos + " ingressos");
		} else {
			System.out.println("Número de pedidos é maior que o estoque");
		}
	}
}