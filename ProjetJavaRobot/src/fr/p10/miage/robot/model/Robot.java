package fr.p10.miage.robot.model;

import java.util.ArrayList;
import java.util.Date;

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
	{
		cR.mettreDansFileAttente(this);

	}

	public void run()
	{
		int i=0;
		int costBatTask;
		int k = 0;
		
		while(true)
		{
			Date dstartDate = new Date();
			this.listeTache.get(i).executTask();
			Date dEndDate = new Date();
			long lexecTime = dEndDate.getTime() - dstartDate.getTime();
			System.out.println("Temp exe millisecond: " + lexecTime);

			costBatTask = this.listeTache.get(i).getCostBattery();
			this.batterie.setNbBarre(this.batterie.getNbBarre() - costBatTask);
			this.nbTacheAccompli++;
			this.goRechargement();
			while(this.EstenREchargement)
			{
				System.out.println("f");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//			}
			i++;
			i=i%(this.listeTache.size());
			System.out.println("hop" + this.batterie);
			k++;
		}
	}
	public String affInfoR()
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

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
