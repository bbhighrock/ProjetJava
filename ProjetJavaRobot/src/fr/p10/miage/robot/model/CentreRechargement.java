package fr.p10.miage.robot.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fr.p10.miage.robot.model.Robot;

public class CentreRechargement implements Runnable{
	//Initialisation de la file d'Attente de robots
	private ArrayList<Robot> fileAttente = new ArrayList<>();
	
	//Liste de robots une fois qu'ils ont été rechargé pour la collection et l'export d'informations
	private ArrayList<Robot> listeRobot = new ArrayList<>();
	
	//Le nombre de robots dans la file d'Attente
	private int max;
	
	public CentreRechargement(int max){		
		this.max = max;
	}
	
	public ArrayList<Robot> getFileAttente() {
		return fileAttente;
	}
	public void setFileAttente(ArrayList<Robot> fileAttente) {
		this.fileAttente = fileAttente;
	}

	public void run()
	{
		while(true)
		{
			//Tant que la file d'Attente est vide, on attend
			while (fileAttente.isEmpty())
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Si un robot est dans la file d'attente
			if(fileAttente.size()>=1)
			{
				//On recharge la batterie du robot
				fileAttente.get(0).getBatterie().setNbBarre(5);
				
				//On incrémente son nombre de rechargement
				fileAttente.get(0).countNbRechargement(); 

				//Affichage des informations du robot après rechargement
				System.out.println(fileAttente.get(0).affInfoR());

				//Collecter les infos d'un robot
				this.collecterInfo(fileAttente.get(0));
				
				//Export des infos des robots après rechargement
				this.exporterInfo();
				
				//On enlève le robot de la file d'attente de rechargement
				this.enleverDansFileAttente();
			}
		}
	}
	
	public void collecterInfo(Robot r){
		//Si le robot n'est pas encore dans cette liste, on le rajoute
		if(!listeRobot.contains(r))
		{
			//Ajout du robot à la liste
			listeRobot.add(r);
		}
	}

	public void exporterInfo(){
		//Création du fichier LogsRobots.txt
		File frobot = new File ("LogsRobots.txt");
		 
		try
		{
			//L'objet fw va nous permettre d'écrire dans le fichier f
		    FileWriter fw = new FileWriter (frobot);
		 
		    fw.write("Export des informations collectées sur les Robots après chaque rechargement\r\n\r\n");
		    
		    //On parcourt la liste des robots rechargés
		    for (int i=0;i<listeRobot.size();i++)
		    {
		    	//Récupération de l'ID
		    	fw.write("Robot n° ");
		        fw.write(String.valueOf(listeRobot.get(i).getId())+"\r\n");
		        //Récupération de la liste des tâches du robot
		        fw.write("Liste de tâches du robot : ");
		        for(int j=0;j<listeRobot.get(i).getListeTache().size();j++)
			        fw.write(String.valueOf(listeRobot.get(i).getListeTache().get(j).getName())+"/");
		        fw.write("\r\n");
		        //Récupération du nombre de tâches effectuées par le robot
		        fw.write("Nombre de tâches effectuées : "+String.valueOf(listeRobot.get(i).getNbTacheAccompli())+"\r\n");
		        //Récupération du niveau de la batterie du robot
		        fw.write("Niveau de rechargement de la batterie : "+String.valueOf(listeRobot.get(i).getBatterie().getNbBarre())+"\r\n");
		        //Récupération du nombre de rechargements effectués par le robot
		        fw.write("Nombre de rechargements : "+String.valueOf(listeRobot.get(i).getNbRechargement())+"\r\n");
		        fw.write("\r\n\r\n");
		    }
		    fw.flush();
		    //Fermeture du fichier
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

	//Un robot est dechargé et on le met dans la file du centre de rechargement
	public synchronized void mettreDansFileAttente(Robot robot){
		//tant que la file d'Attente est pleine
		while(this.fileAttente.size() >= this.max){
			//Si le robot possède toujours de la batterie, il ne se met pas en attente et retourne à sa tâche
			if(robot.getBatterie().isBattSuffisante())
				return;
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Il y a de la place dans la file, on ajoute le robot
		this.fileAttente.add(robot);
		//Le robot est en attente de rechargement
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Un robot est complétement rechargé et on libère une place dans la file
	public synchronized void enleverDansFileAttente(){
		//On le supprime de la liste et le deuxième dans la file devient le premier à recharger si il existe
		fileAttente.remove(fileAttente.get(0));
		//On notifie les threads en attente
		notifyAll();
	}
}
