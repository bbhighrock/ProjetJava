package test;

import static org.junit.Assert.*;
import model.Battery;
import model.CentreRechargement;
import model.Robot;

import org.junit.Test;

public class CentreRechargementTest {

	@Test
	public void test() {
		CentreRechargement cr = new CentreRechargement(5, 5,3);
		Robot r1 = new Robot(1,new Battery(5, true), null, 3, 0, 0, cr);

		cr.mettreDansFileAttente(r1);
		//Test si r1 est dans la liste d'attente
		assertTrue(cr.getFileAttente().get(0).equals(r1));	
		
		cr.enleverDansFileAttente();	
		//Test si r1 n est plus dans la lisgte d'attente c est a dire si la liste est vide 
		assertTrue(cr.getFileAttente().size()==0);	


	
		
		
		

	}

}
