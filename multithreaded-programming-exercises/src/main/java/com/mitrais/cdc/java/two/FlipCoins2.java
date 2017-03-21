package com.mitrais.cdc.java.two;

import java.util.concurrent.*;

import com.mitrais.cdc.java.one.CoinFlipper;

/**
 * Second variation: main class implements Runnable.
 */

public class FlipCoins2 implements Runnable {

	public FlipCoins2() {
		// Note that you cannot do tasks.execute(this) directly from main.
		// When you are in main, no instance of the surrounding class has
		// yet been created, so there *IS* no "this". So, here main calls
		// the constructor of the class, and that constructor does
		// tasks.execute(this).

		// Similarly, you cannot make
		// an instance of an inner class directly from main. Again, this is
		// because
		// in main, the outer class has not yet been built.

		int poolSize = 10;

		// TODO: Instantiate ExcecutorService class with poolsize as stated
		// above
		ExecutorService exeserv = Executors.newFixedThreadPool(poolSize);
		
		// TODO execute this 5 times (or several times) concurrently
		exeserv.execute(new CoinFlipper());
		exeserv.execute(new CoinFlipper());
		exeserv.execute(new CoinFlipper());
		exeserv.execute(new CoinFlipper());
		exeserv.execute(new CoinFlipper());
		
		// TODO: do not forget to shutdown the executor
		exeserv.shutdown();
		if (!exeserv.isTerminated()) {
			exeserv.shutdownNow();
		}
	}

	public static void main(String[] args) {
		new FlipCoins2();
	}

	@Override
	public void run() {
		// TODO: Just copy run() method from question number 1 :)
		int counter = 0;
		for (int i = 1; i <= 100; i++) {
			if (ThreadLocalRandom.current().nextInt(2) == 0) {
				counter++;
			} else {
				counter = 0;
			}
			
			if (counter >= 3) {
				System.out.println(Thread.currentThread().getName()+" got "+counter+" heads in a row!!");
			}
		}
	}
}