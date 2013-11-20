package Modele;

public class Batterie {
	private int nbBarre;
	private boolean battSuffisante;
	
	protected Batterie(int nbBarre, boolean battSuffisante) {
		this.nbBarre = nbBarre;
		this.battSuffisante = battSuffisante;
	}
	public int getNbBarre() {
		return nbBarre;
	}
	public void setNbBarre(int nbBarre) {
		this.nbBarre = nbBarre;
	}
	public boolean isBattSuffisante() {
		return battSuffisante;
	}
	public void setBattSuffisante(boolean battSuffisante) {
		this.battSuffisante = battSuffisante;
	}



}
