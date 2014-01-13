package fr.p10.miage.robot.view;

import java.util.ArrayList;
import java.util.Random;

import fr.p10.miage.robot.model.*;

public class Main {

	public static void main(String[] args) {

		//Création d'un tableau d'entiers non triés
		Random r = new Random();
		int intRandom;
		Comparable[] table = new Comparable[20];
		for(int i=0; i<20; i++)
		{
			intRandom = r.nextInt();
			table[i] = intRandom;
		}
		//Phase de création d'une liste de taches communes à tous les robots
		ArrayList<Task> lTaskAll = new ArrayList<>();
		
		Clean cl = new Clean("Clean", false, table);
		Destroy destr= new Destroy("Destroy", false, table);
		
		lTaskAll.add(cl);
		lTaskAll.add(destr);
		
		//Phase de création du centre de rechargement
		CentreRechargement cr = new CentreRechargement(3);
		Thread c = new Thread(cr);
		c.start();

		//Phase de création d'une liste de robots
		ArrayList<Thread> lstR = new ArrayList<>();

		//Phase de création des robots
		Robot r1 = new Robot(1,new Battery(5, true), lTaskAll, 3, 0, 0, cr);
		Thread robot = new Thread(r1);
		Robot r2 = new Robot(2,new Battery(5, true), lTaskAll, 3, 0, 0, cr);
		Thread robot2 = new Thread(r2);
		Robot r3 = new Robot(3,new Battery(5, true), lTaskAll, 3, 0, 0, cr);
		Thread robot3 = new Thread(r3);
		Robot r4 = new Robot(4,new Battery(5, true), lTaskAll, 3, 0, 0, cr);
		Thread robot4 = new Thread(r4);
		Robot r5 = new Robot(5,new Battery(5, true), lTaskAll, 3, 0, 0, cr);
		Thread robot5 = new Thread(r5);
		
		//Ajout des robots dans la liste de robots
		lstR.add(robot);
		lstR.add(robot2);
		lstR.add(robot3);
		lstR.add(robot4);
		lstR.add(robot5);

		//Assignation des taches spéciales aux robots 1, 3 et 5
		Repare rep = new Repare("Repare", true, table);
		r1.getListeTache().add(rep);
		Dig dig = new Dig("Creuser", true, table);
		r3.getListeTache().add(dig);
		Build build = new Build("Construire", true, table);
		r5.getListeTache().add(build);

		//Démarrage de tous les threads robot
		for(int i=0;i<lstR.size();i++)
		{
			lstR.get(i).start();
		}		

		//Attente de la fin d'execution des threads robot et du thread rechargement
		for(int i=0;i<lstR.size();i++)
		{
			try 
			{
				lstR.get(i).join();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		try {
			c.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


// Problèmes non gérés pour le moment
	//Test du fichier
	//Si un robot a une batterie plus vide qu'un autre, il n'est pas forcément prioritaire sur le rechargement
	//La moyenne des temps d'execution de chaque tâche par les robots n'est pas exportée dans le fichier
