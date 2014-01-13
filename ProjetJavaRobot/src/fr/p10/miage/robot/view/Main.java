package fr.p10.miage.robot.view;

import java.util.ArrayList;
import java.util.Random;

import fr.p10.miage.robot.model.*;

public class Main {

	public static void main(String[] args) {
		Random r = new Random();
		int intRandom;
		Comparable[] table = new Comparable[20];
		for(int i=0;i<20;i++)
		{
			intRandom=r.nextInt();
			table[i]=intRandom;
		}
		
		//Phase de création d'une liste de tache commune à tous les robots
		ArrayList<Task> lTaskAll = new ArrayList<>();
		Clean cl = new Clean( "Clean", false, table);
		Destroy destr= new Destroy("Destroy", false, table);
		lTaskAll.add(cl);
		lTaskAll.add(destr);

		CentreRechargement cr = new CentreRechargement(5,3);
		Thread c = new Thread(cr);
		c.start();

		
		// Phase de création des robots
		ArrayList<Thread> lstR = new ArrayList<>();

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
		lstR.add(robot);
		lstR.add(robot2);
		lstR.add(robot3);
		lstR.add(robot4);
		lstR.add(robot5);

		//Assignation de tache spéciale
		Repare rep = new Repare("Repare", true, table);
		r1.getListeTache().add(rep);
		Dig dig = new Dig("Creuser", true, table);
		r3.getListeTache().add(rep);
		Build build = new Build("Construire", true, table);
		r5.getListeTache().add(rep);
		
		
		for(int i=0;i<lstR.size();i++)
		{
			lstR.get(i).start();
		}		

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
