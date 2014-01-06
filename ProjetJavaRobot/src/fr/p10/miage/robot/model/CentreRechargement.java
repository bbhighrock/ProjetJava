package fr.p10.miage.robot.model;

import java.util.ArrayList;

public class CentreRechargement  implements Runnable{
	private ArrayList<Robot> fileAttente = new ArrayList<>();
	private int max;
	private int nbrechargementAfaire;//Nbr de rechargement qui sera effectué, avant de mettre fin au programme
	private boolean crEnMarche;
	public CentreRechargement(int max,int nb){
		this.max = max;
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
		while(c<3)
		{
			System.out.println(c+"-");
			if(!(fileAttente.isEmpty()))
			{

				fileAttente.get(0).getBatterie().setNbBarre(5); 
				fileAttente.get(0).countNbRechargement(); 
				//if(fileAttente.size()>=1)
				this.enleverDansFileAttente();
				c++;

			}

		}
		//Liberation de ts les robot en file d'attentes
		while(!(fileAttente.isEmpty()))
			this.enleverDansFileAttente();
		System.out.println("fin cr");
		crEnMarche=false;
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

	}

	//Un robot est dechargé et on le met dans la file
	public synchronized void mettreDansFileAttente(Robot robot){
		//	System.out.println("hh");
		System.out.println(this.fileAttente.size() + " - " + max);

		while(this.fileAttente.size() >= this.max){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		fileAttente.add(robot);

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
}
