package fr.p10.miage.robot.model;



import java.util.ArrayList;

public class Robot { MERDE

	private Batterie batterie;
	private ArrayList<Tache> listeTache=new ArrayList<>();
	private int nbTacheMax;
	private int nbTacheAccompli;
	private int nbRechargement;
	protected Robot(Batterie batterie, ArrayList<Tache> listeTache,
			int nbTacheMax, int nbTacheAccompli, int nbRechargement) {
		this.batterie = batterie;
		this.nbTacheMax = nbTacheMax;
		this.nbTacheAccompli = nbTacheAccompli;
		this.nbRechargement = nbRechargement;
	}
	public Batterie getBatterie() {
		return batterie;
	}
	public void setBatterie(Batterie batterie) {
		this.batterie = batterie;
	}
	public ArrayList<Tache> getListeTache() {
		return listeTache;
	}
	public void setListeTache(ArrayList<Tache> listeTache) {
		this.listeTache = listeTache;
	}
	public int getNbTacheMax() {
		return nbTacheMax;
	}
	public void setNbTacheMax(int nbTacheMax) {
		this.nbTacheMax = nbTacheMax;
	}
	public int getNbTacheAccompli() {
		return nbTacheAccompli;
	}
	public void setNbTacheAccompli(int nbTacheAccompli) {
		this.nbTacheAccompli = nbTacheAccompli;
	}
	public int getNbRechargement() {
		return nbRechargement;
	}
	public void setNbRechargement(int nbRechargement) {
		this.nbRechargement = nbRechargement;
	}
	
	
	
	
	
}
