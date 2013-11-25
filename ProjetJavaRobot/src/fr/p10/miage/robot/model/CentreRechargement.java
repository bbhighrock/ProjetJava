package fr.p10.miage.robot.model;

import java.util.ArrayList;

public class CentreRechargement {
	private ArrayList<Robot> fileAttente;
	private int max, curr;

	public CentreRechargement(int max, int curr){
		this.max = 3;
		this.curr = curr;
	}

	public synchronized void lancerRechargement(){

	}

	public void collecterInfo(){

	}

	public void exporterInfo(){

	}

	//Un robot est dechargé et on le met dans la file
	public synchronized void mettreDansFileAttente(){
		// TODO Auto-generated method stub
		while(this.curr == this.max){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		curr ++;
		notifyAll();
	}

	//Un robot est complétement rechargé et on libère une place dans la file
	public synchronized void enleverDansFileAttente(){
		while(this.curr == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.curr--;
		notifyAll();
	}
}
