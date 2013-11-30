package fr.p10.miage.robot.model;

import java.util.ArrayList;

import model.Battery;
import model.CentreRechargement;
import model.Task;

public class Robot {


	private Battery batterie;
	private ArrayList<Task> listeTache=new ArrayList<>();
	private int nbTacheMax;
	private int nbTacheAccompli;
	private int nbRechargement;
	private CentreRechargement cR;
	private int id;
	public Robot(int i,Battery batterie, ArrayList<Task> listeTache,
			int nbTacheMax, int nbTacheAccompli, int nbRechargement,CentreRechargement cRech) {
		this.batterie = batterie;
		this.nbTacheMax = nbTacheMax;
		this.nbTacheAccompli = nbTacheAccompli;
		this.nbRechargement = nbRechargement;
		this.listeTache = listeTache;
		cR = cRech;
		id=i;
	}

	public Battery getBatterie() {
		return batterie;
	}
	public void setBatterie(Battery batterie) {
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
		this.cR.lancerRechargement();
	}

	public void run()
	{
		int i=0;
		int costBatTask;
		while(this.batterie.getNbBarre() > 0)
		{
//			if(this.batterie.isBattSuffisante())
//			{
				this.listeTache.get(i).executTask();
				costBatTask = this.listeTache.get(i).getCostBattery();
				this.batterie.setNbBarre(this.batterie.getNbBarre() - costBatTask);
				this.nbTacheAccompli++;

//			}
//			else
//			{
//				this.goRechargement();
//			}
			i++;
			i=i%(this.listeTache.size());
			System.out.println("hop");
		}
	}
public String toString()
{
	return "Robot : " + id + " - " + batterie + " - nbREchargement : " + nbRechargement + 
			" nbTask Accomplies : " + nbTacheAccompli ;
}

}
