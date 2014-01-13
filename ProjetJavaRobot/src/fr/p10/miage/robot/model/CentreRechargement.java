package fr.p10.miage.robot.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fr.p10.miage.robot.model.Robot;

public class CentreRechargement implements Runnable{
	private ArrayList<Robot> fileAttente = new ArrayList<>();
	private ArrayList<Robot> listeRobot = new ArrayList<>();//pr contenir tous les robots qui se seront rechargé, pr exporté les info

	private int max;
	private int nbrechargementAfaire;//Nbr de rechargement qui sera effectué, avant de mettre fin au programme
	private boolean crEnMarche;
	public CentreRechargement(int max,int nb){		
		this.max = 3;
		this.nbrechargementAfaire = nb;
		crEnMarche =true;
	}
	public ArrayList<Robot> getFileAttente() {
		return fileAttente;
	}
	public void setFileAttente(ArrayList<Robot> fileAttente) {
		this.fileAttente = fileAttente;
	}
	public int getNbrechargementAfaire() {
		return nbrechargementAfaire;
	}

	public void setNbrechargementAfaire(int nbrechargementAfaire) {
		this.nbrechargementAfaire = nbrechargementAfaire;
	}
	public void run()
	{
		while(true)
		{
			//Si liste est vide, on endort le centre 
			while (fileAttente.isEmpty())
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileAttente.size()>=1)
			{
				fileAttente.get(0).getBatterie().setNbBarre(5); 
				fileAttente.get(0).countNbRechargement(); 

				System.out.println("Robot :" + fileAttente.get(0).getId() + " - Nb rechargement :" + fileAttente.get(0).getNbRechargement()
						+ " - Nb Tache accompli :" + fileAttente.get(0).getNbTacheAccompli());

				this.collecterInfo(fileAttente.get(0));
				this.exporterInfo();
				this.enleverDansFileAttente();
			}
		}
	}
	
	public boolean isCrEnMarche() {
		return crEnMarche;
	}
	public void setCrEnMarche(boolean crEnMarche) {
		this.crEnMarche = crEnMarche;
	}
	
	public void collecterInfo(Robot r){
		//SI le robot n'est pas encore dans cette liste, on le rajoute
		if(!listeRobot.contains(fileAttente.get(0)))
		{
			listeRobot.add(fileAttente.get(0));
		}
	}

	public void exporterInfo(){
		File f = new File ("LogsRobots.txt");
		 
		try
		{
		    FileWriter fw = new FileWriter (f);
		 
		    fw.write("Export des informations collectées sur les Robots après chaque rechargement\r\n\r\n");
		    
		    for (int i=0;i<listeRobot.size();i++)
		    {
		    	fw.write("Robot n° ");
		        fw.write(String.valueOf(listeRobot.get(i).getId())+"\r\n");
		        fw.write("Liste de tâches du robot : ");
		        for(int j=0;j<listeRobot.get(i).getListeTache().size();j++)
			        fw.write(String.valueOf(listeRobot.get(i).getListeTache().get(j).getName())+"/");
		        fw.write("\r\n");
		        fw.write("Nombre de tâches effectuées : "+String.valueOf(listeRobot.get(i).getNbTacheAccompli())+"\r\n");
		        fw.write("Niveau de rechargement de la batterie : "+String.valueOf(listeRobot.get(i).getBatterie().getNbBarre()+"/5")+"\r\n");
		        fw.write("Nombre de rechargements : "+String.valueOf(listeRobot.get(i).getNbRechargement())+"\r\n");
		        fw.write("\r\n\r\n");
		    }
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

	//Un robot est dechargé et on le met dans la file
	public synchronized void mettreDansFileAttente(Robot robot){
		while(this.fileAttente.size() >= this.max){
			//Si le robot possède toujours de la batterie, il ne se met en attente et retrourne à sa tache
			if(robot.getBatterie().isBattSuffisante())
				return;
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.fileAttente.add(robot);
		robot.setEstenREchargement(true);
	}

	//Un robot est complétement rechargé et on libère une place dans la file
	public synchronized void enleverDansFileAttente(){

		fileAttente.get(0).setEstenREchargement(false);
		fileAttente.remove(fileAttente.get(0));// supprime l element i+1 déplacé
		notifyAll();
	}
	
	public void setlstRobot(ArrayList<Robot> lstR) {
		listeRobot = lstR;
		
	}
}
