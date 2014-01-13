package fr.p10.miage.robot.model;

//Tri Selection
public class Destroy extends Task{
	private final static int costB = 1;

	public Destroy(String name, boolean isUnique,
			Comparable[] table) {
		super(costB, name, isUnique, table);
		// TODO Auto-generated constructor stub
	}

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
	
	public String getName(){
		return name;
	}
}
