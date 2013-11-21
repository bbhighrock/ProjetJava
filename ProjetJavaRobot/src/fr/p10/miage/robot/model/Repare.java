package fr.p10.miage.robot.model;

//Tri par Insertion Dichotomique
public class Repare extends Task {
	protected void executTask( ) {

		for (int i=1; i < table.length;i++ ) {
			Comparable toPlace = table[i]; // nouvel élément à ranger
			int gauche = 0;
			int droite = i;
			int milieu;
			
			// recherche dichotomique de sa place dans le sous tableau trié
			while ( gauche < droite) {
				milieu = (gauche + droite)/2;
				if ((toPlace.compareTo(table[milieu])) > 0)
					gauche = milieu+1;
				else
					droite = milieu;
			}

			// décalage des éléments du tableau pour placer le nouvel élément
			for (int j=i; j > gauche; j-- ) 
				exchange(j-1, j);
		}
	}
}
