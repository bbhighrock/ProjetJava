package fr.p10.miage.robot.model;

//Tri Bulle
public class Build extends Task{
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
