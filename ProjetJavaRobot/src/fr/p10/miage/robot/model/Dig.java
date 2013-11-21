package fr.p10.miage.robot.model;

//Tri Rapide (QuickSort)
public class Dig extends Task{
	protected void executTask(int begin, int end) {

		int indicePivot;

		if (begin < end) {

			//System.out.println(toString(table)); 
			indicePivot = partition(begin, end); // le pivot
			executTask(begin, indicePivot-1); // sous-tableau gauche <= pivot : Invariant 1
			executTask(indicePivot + 1, end); // sous-tableau droit > pivot : Invariant 1
		}
	}

	protected int choixPivot(int begin, int end) {
		return begin;
	}

	protected int partition(int begin, int end) {

		int indicePivot=choixPivot(begin,end); // recherche d'un pivot

		Comparable pivot = table[indicePivot]; // la valeur du pivot
		exchange( begin, indicePivot); // il est mis au debut du tableau

		int versDroite=begin+1; // on partionne le tableau sans le pivot
		int versGauche=end;
		while(versDroite < versGauche ){
			while( (table[versDroite].compareTo(pivot)< 0) && (versDroite < versGauche) )
				versDroite++ ; // recherche d'un élément < pivot
			while( (table[versGauche].compareTo(pivot) > 0) && (versDroite <= versGauche) )
				versGauche-- ; // recherche d'un élément > pivot
			if (versDroite < versGauche)
				exchange( versDroite, versGauche); // échange entre les deux éléments trouvés}
		}
		if (table[versGauche].compareTo(pivot)< 0)
			exchange( begin, versGauche); // le pivot est mis à sa place
		return versGauche;
	}

	protected void executTask( ) {
		executTask(0,table.length-1);
	}
}
