package fr.p10.miage.robot.model;

import java.util.ArrayList;

import fr.p10.miage.robot.model.Battery;
import fr.p10.miage.robot.model.CentreRechargement;
import fr.p10.miage.robot.model.Task;

public class Robot implements Runnable {


	private Battery batterie;
	private ArrayList<Task> listeTache=new ArrayList<>();
	private int nbTacheMax;
	private int nbTacheAccompli;
	private int nbRechargement;
	private CentreRechargement cR;
	private int id;
	private boolean EstenREchargement, robotEnMarche;
	public Robot(int i,Battery batterie, ArrayList<Task> listeTache,
			int nbTacheMax, int nbTacheAccompli, int nbRechargement,CentreRechargement cRech) {
		this.batterie = batterie;
		this.nbTacheMax = nbTacheMax;
		this.nbTacheAccompli = nbTacheAccompli;
		this.nbRechargement = nbRechargement;
		this.listeTache = listeTache;
		cR = cRech;
		id=i;
		EstenREchargement =false;
		robotEnMarche = true;
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
	public void countNbRechargement() {
		this.nbRechargement++;
	}

	public void goRechargement()
	{System.out.println("y");
	cR.mettreDansFileAttente(this);
	System.out.println("y");

	}

	public void run()
	{
		int i=0;
		int costBatTask;
		int k = 0;
		//for(int c=0;c<2;c++)
		while(this.batterie.isBattSuffisante())
		{

			this.listeTache.get(i).executTask();
			costBatTask = this.listeTache.get(i).getCostBattery();
			this.batterie.setNbBarre(this.batterie.getNbBarre() - costBatTask);
			this.nbTacheAccompli++;

			if(this.batterie.isBattSuffisante() && cR.isCrEnMarche())
			{

				this.goRechargement();
				while(this.EstenREchargement)
				{System.out.println("f");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
			i++;
			i=i%(this.listeTache.size());
			System.out.println("hop" + this.batterie);
			k++;
		}
		System.out.println("fin robot " +k);
		robotEnMarche = false;
	}
	public String toString()
	{
		return "Robot : " + id + " - " + batterie + " - nbREchargement : " + nbRechargement + 
				" nbTask Accomplies : " + nbTacheAccompli ;
	}

	public void setEstenREchargement(boolean b) {
		EstenREchargement=b;	
	}

	public boolean isMarche() {
		// TODO Auto-generated method stub
		return robotEnMarche;
	}
}
