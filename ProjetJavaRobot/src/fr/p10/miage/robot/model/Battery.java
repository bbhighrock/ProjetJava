package fr.p10.miage.robot.model;

import fr.p10.miage.robot.model.ExceptionBatt;

public class Battery {
	private int nbBarre;
	private boolean battSuffisante;

	public Battery(int nbBarre, boolean battSuffisante) {
		this.setNbBarre(nbBarre);
		this.battSuffisante = battSuffisante;
	}
	
	public int getNbBarre() {
		return nbBarre;
	}
	
	public void setNbBarre(int nbBarre) {
		if(nbBarre>0){
			this.nbBarre = nbBarre;
		}else if(nbBarre == 0){
			//exception
			this.nbBarre = 0;
			battSuffisante=false;
		}else{
			ExceptionBatt e = new ExceptionBatt("Il est interdit de donner a un robot une batterie < 0 ");
			try {
				throw (ExceptionBatt) e;
			} catch (ExceptionBatt e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public boolean isBattSuffisante() {
		return battSuffisante;
	}
	
	public void setBattSuffisante(boolean battSuffisante) {
		this.battSuffisante = battSuffisante;
	}

	public String affichNbBarre() {
		return "Etat de la Batterie : " + nbBarre;
	}
}
