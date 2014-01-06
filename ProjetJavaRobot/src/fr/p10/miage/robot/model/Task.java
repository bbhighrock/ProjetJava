package fr.p10.miage.robot.model;

public abstract class Task implements ExecutTask {
	private int costBattery;
	private String name;
	private boolean isUnique;
	
	protected Comparable [] table;
	protected abstract void executTask();

	
	protected Task(int costBattery, String name, boolean isUnique,
			Comparable[] table) {
		super();
		this.costBattery = costBattery;
		this.name = name;
		this.isUnique = isUnique;
		this.table = table;
	}
	
	public void executTask (Comparable [] table1){ // la méthode trier concrète
		if (table1.length > 0){
			table = table1;
			executTask(); // appel de la méthode trier différée
			table  = null;
		}
	}

	protected final void exchange (int i, int j) {
		Comparable temp = table[i];
		table[i] = table[j];
		table[j] = temp;
	}

	public String toString (Comparable [] table1) {
		String content = "";
		for (int i =0; i < table1.length; i++)
			content = content + " " + i + " : " + table1[i] + "\n";
		return content;
		}
	
	public int getCostBattery()
	{
		return costBattery;
	}
}
