package fr.p10.miage.robot.model;

//Tri Insertion
public class Clean extends Task {
	private final static int costB = 1;
	public Clean(String name, boolean isUnique,
			Comparable[] table) {
		super(costB, name, isUnique, table);
		// TODO Auto-generated constructor stub
	}

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
	
	public String getName(){
		return name;
	}
}
