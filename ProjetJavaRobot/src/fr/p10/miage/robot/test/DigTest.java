package fr.p10.miage.robot.test;

import static org.junit.Assert.*;
import model.Dig;

public class DigTest {

	@Test
	public void test() {
		Comparable[] tableNonTrie = new Comparable[10];//Tableau qui sera non trié
		Comparable[] tableTrie = new Comparable[10];//Tableaux qui sera trié et qui servira de comparaison

		for(int i=0,nb=9;i<10;i++, nb--)
		{
			tableNonTrie[i]=nb;
			tableTrie[i]=i;

		}

		String trie = "",nonTrie ="";
		for(int i=0;i<10;i++)
		{
			nonTrie += tableNonTrie[i];
			trie += tableTrie[i];

		}
		System.out.println("Avant trie");
		System.out.println(nonTrie);
		//comparaison des tableaux avant le trie
		assertFalse(trie.equalsIgnoreCase(nonTrie));		

		Dig dig = new Dig( "Dig", false, tableNonTrie);

		trie = "";
		nonTrie ="";
		dig.executTask(tableNonTrie);
		for(int i=0;i<10;i++)
		{
			nonTrie += tableNonTrie[i];
			trie += tableTrie[i];

		}
		System.out.println("Après trie");
		System.out.println(nonTrie);
		//Comparaison après le trie
		assertEquals(nonTrie, trie);
	}

}
