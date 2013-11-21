package fr.p10.miage.robot.model;

public class Destroy extends Task{
	protected void executTask( ) {
		for( int i= 0; i <= table.length - 2; i++)
			exchange(i, searchMin(i,table.length-1) );
	}

	protected int searchMin (int begin, int end) {
		int indice;
		Comparable min = table[begin];
		indice = begin; 
		for (int i = begin + 1; i <= end; i++)
			if (min.compareTo(table[i]) > 0 ) {
				min = table[i];
				indice = i;
			}
		return indice;
	}
}
