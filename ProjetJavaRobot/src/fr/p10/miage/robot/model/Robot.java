package Modele;

import java.util.ArrayList;

public class Robot {

	private Batterie batterie;
	private ArrayList<Task> listeTache=new ArrayList<>();
	private int nbTacheMax;
	private int nbTacheAccompli;
	private int nbRechargement;
	private centreRechargement cR;
	protected Robot(Batterie batterie, ArrayList<Tache> listeTache,
			int nbTacheMax, int nbTacheAccompli, int nbRechargement,centreRechargement cRrech) {
		this.batterie = batterie;
		this.nbTacheMax = nbTacheMax;
		this.nbTacheAccompli = nbTacheAccompli;
		this.nbRechargement = nbRechargement;
		cR = cRech;
	}
	public Batterie getBatterie() {
		return batterie;
	}
	public void setBatterie(Batterie batterie) {
		this.batterie = batterie;
	}
	public ArrayList<Task> getListeTache() {
		return listeTache;
	}
	public void setListeTache(ArrayList<Task> listeTache) {
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

	public void goRechargement()
	{
		this.cR.rechargement();
	}

	public void run()
	{
		int i=0;
		while(true)
		{
			if(this.batterie.isBattSuffisante())
			{
				this.listeTache[i].execute();

			}
			else
			{
				this.goRechargement();
			}
			i++;
			i=i%(this.listeTache.size());
		}
	}

}
