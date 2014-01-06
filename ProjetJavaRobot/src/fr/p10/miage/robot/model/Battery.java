package fr.p10.miage.robot.model;

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
		}else{
			//execption
			battSuffisante=false;
		}
	}
	public boolean isBattSuffisante() {
		return battSuffisante;
	}
	public void setBattSuffisante(boolean battSuffisante) {
		this.battSuffisante = battSuffisante;
	}

	@Override
	public String toString() {
		return "Battery : " + nbBarre ;
	}



}
