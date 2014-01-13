package fr.p10.miage.robot.model;

import java.util.ArrayList;
import java.util.Date;

import fr.p10.miage.robot.model.Battery;
import fr.p10.miage.robot.model.CentreRechargement;
import fr.p10.miage.robot.model.Task;

public class Robot implements Runnable {

	//Assignation des spécificités d'un robot
	private Battery batterie;
	private ArrayList<Task> listeTache=new ArrayList<>();
	private int nbTacheMax;
	private int nbTacheAccompli;
	private int nbRechargement;
	private CentreRechargement cR;
	private int id;
	private boolean EstenREchargement;
	@SuppressWarnings("unchecked")
	public Robot(int i,Battery batterie, ArrayList<Task> listeTache,
			int nbTacheMax, int nbTacheAccompli, int nbRechargement,CentreRechargement cRech) {
		this.batterie = batterie;
		this.nbTacheMax = nbTacheMax;
		this.nbTacheAccompli = nbTacheAccompli;
		this.nbRechargement = nbRechargement;
		this.listeTache = (ArrayList<Task>) listeTache.clone();
		cR = cRech;
		id=i;
		EstenREchargement =false;
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
		
		while(true)
		{
			//Calcul du temps d'exécution d'une tâche
			Date dstartDate = new Date();
			
			//Exécution d'une tâche
			this.listeTache.get(i).executTask();
			Date dEndDate = new Date();
			long lexecTime = dEndDate.getTime() - dstartDate.getTime();
			System.out.println("Temp exe millisecond: " + lexecTime);

			//Calcul du coût de la batterie
			costBatTask = this.listeTache.get(i).getCostBattery();
			
			//Mise à jour de sa batterie actuelle après exécution de la tâche
			this.batterie.setNbBarre(this.batterie.getNbBarre() - costBatTask);
			
			//Mise à jour du nombre de tâches accomplies
			this.nbTacheAccompli++;
			
			//Rechargement du robot après exécution d'une tâche
			this.goRechargement();
			
			//Tant qu'il est en rechargement, il ne fait plus de tâches
			while(this.EstenREchargement)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++; //On passe à la tâche suivante du même robot
			i=i%(this.listeTache.size()); //Afin d'éviter de dépasser le nb de tâches. Il revient à 0 une fois ttes les tâches effectuées 
		}
	}
	public String affInfoR()
	{
		return "Robot : " + id + " - " + batterie.affichNbBarre() + " - nbREchargement : " + nbRechargement + 
				" nbTask Accomplies : " + nbTacheAccompli ;
	}

	public void setEstenREchargement(boolean b) {
		EstenREchargement=b;	
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
