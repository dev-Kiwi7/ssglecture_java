package com.sinse.threadapp.ani;

import javax.swing.JProgressBar;

public class ProgressThread extends Thread{
	
	JProgressBar bar;
	int n;
	int velX;
	
	public ProgressThread(JProgressBar bar, int velX) {
		this.bar=bar;
		this.velX=velX;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
				bar.setValue(n);
				n=n+velX;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}




