package fr.p10.miage.robot.model;

//Tri Insertion
public class Clean extends Task {
	protected void executTask() {
		for (int i=1; i < table.length; i++) {
			insertElement(i);
		}
	}

	protected void insertElement(int indiceElementToPlace) {
		int j = indiceElementToPlace;
		while ((j> 0) && (table[j-1].compareTo(table[j]) > 0)) {
			exchange(j-1, j);
			j = j-1;
		}
	}
}
