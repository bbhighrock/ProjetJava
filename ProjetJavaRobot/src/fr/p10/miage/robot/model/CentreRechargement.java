package fr.p10.miage.robot.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fr.p10.miage.robot.model.Robot;

public class CentreRechargement implements Runnable{
	private ArrayList<Robot> fileAttente = new ArrayList<>();
	private ArrayList<Robot> listeRobot = new ArrayList<>();

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
		int c=0;
		boolean roboEnMarche=true;
		while (fileAttente.isEmpty())
		{
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(listeRobot.size());
		}
		while(true)
		{
			//Si liste est vide, on endort le centre 
			while (fileAttente.isEmpty()&& roboEnMarche)
			{
				for(int k=0;k<listeRobot.size();k++)
				{
					roboEnMarche = roboEnMarche && listeRobot.get(k).isMarche();
					System.out.println("robot"+roboEnMarche);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//System.out.println(c+"-");
			if(fileAttente.size()>=1)
			{
				System.out.println("55");

				fileAttente.get(0).getBatterie().setNbBarre(5); 
				fileAttente.get(0).countNbRechargement(); 
				//if(fileAttente.size()>=1)
				
				//if exist maj info sion on ajoute
				//if(fileAttente.get(0)
				System.out.println("Robot :" + fileAttente.get(0).getId() + " - Nb rechargement :" + fileAttente.get(0).getNbRechargement()
						+ " - Nb Tache accompli :" + fileAttente.get(0).getNbTacheAccompli());
				
				this.enleverDansFileAttente();
				c++;

			}


		}
//		System.out.println("toto");
//		//Liberation de ts les robot en file d'attentes
//		while(!(fileAttente.isEmpty()))
//			this.enleverDansFileAttente();
//		System.out.println("fin cr");
//		crEnMarche=false;
	}
	
	


	public boolean isCrEnMarche() {
		return crEnMarche;
	}
	public void setCrEnMarche(boolean crEnMarche) {
		this.crEnMarche = crEnMarche;
	}
	
	public void collecterInfo(){
		
	}

	public void exporterInfo(){
		File f = new File ("LogsRobots.txt");
		 
		try
		{
		    FileWriter fw = new FileWriter (f);
		 
		    for (double d : data)
		    {
		        fw.write (String.valueOf (d));
		        fw.write ("\r\n");
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
		//System.out.println("hh");


		//System.out.println(this.fileAttente.size() + " - " + max);

		while(this.fileAttente.size() >= this.max){
			System.out.println("jj");
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
		
	//	System.out.println(" size " + fileAttente.size());
		this.fileAttente.add(robot);
	//	System.out.println(" size " + fileAttente.size());

		robot.setEstenREchargement(true);
		//		curr ++;
		//		try {
		//			wait();
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

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
