package fr.p10.miage.robot.model;

//Tri Bulle
public class Build extends Task{
	private final static int costB = 2;
	public Build(String name, boolean isUnique,
			Comparable[] table) {
		super(costB, name, isUnique, table);
		// TODO Auto-generated constructor stub
	}

		protected boolean exchangeDone;
		protected void executTask( ) {
			exchangeDone = true;
			for (int i = table.length-1; i>0 && exchangeDone; i--) {
				bubbleSort(i);
			}
		}

		protected void bubbleSort( int i) {
			exchangeDone = false;
			for (int j=0; j<i; j++) 
				if ( (table[j].compareTo(table[j+1]) > 0)) {
					exchange(j , j+1);
					exchangeDone = true;
				}
		}
}
