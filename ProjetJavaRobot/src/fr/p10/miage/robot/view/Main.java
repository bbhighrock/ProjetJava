package fr.p10.miage.robot.view;

import java.util.ArrayList;
import java.util.Random;

import model.Battery;
import model.CentreRechargement;
import model.Clean;
import model.Destroy;
import model.Repare;
import model.Robot;
import model.Task;

public class Main {

	public static void main(String[] args) {
		Random r = new Random();
		int intRandom;
		Comparable[] table = new Comparable[20];
		for(int i=0;i<20;i++)
		{
			intRandom=r.nextInt();//
			table[i]=intRandom;
		}
		//Phase de création d'une liste de tache commune à tous les robots
		ArrayList<Task> lTaskAll = new ArrayList<>();
		Clean cl = new Clean( "Clean", false, table);
		Destroy destr= new Destroy("Destroy", false, table);
		lTaskAll.add(cl);
		lTaskAll.add(destr);

		CentreRechargement cr = new CentreRechargement(5, 5,3);

		cr.start();

		ArrayList<Robot> lstR = new ArrayList<>();

		Robot r1 = new Robot(1,new Battery(5, true), lTaskAll, 3, 0, 0, cr);
			Robot r2 = new Robot(2,new Battery(5, true), lTaskAll, 3, 0, 0, cr);

		lstR.add(r1);
			lstR.add(r2);

		//Assignation de tache spéciale
		Repare rep = new Repare("Repare", true, table);
		r1.getListeTache().add(rep);

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
			cr.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--Resultat run des robots");

		for(int i=0;i<lstR.size();i++)
		{
			System.out.println(lstR.get(i));
		}	


	}

}
