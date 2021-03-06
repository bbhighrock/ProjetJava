package fr.p10.miage.robot.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.p10.miage.robot.model.*;


public class BuildTest {

	@Test
	public void test() {
		Comparable[] tableNonTrie = new Comparable[10];//Tableau qui sera non trié
		Comparable[] tableTrie = new Comparable[10];//Tableaux qui sera trié et qui servira de comparaison

		//On remplit 1 trié, l'autre non
		for(int i=0,nb=9;i<10;i++, nb--)
		{
			tableNonTrie[i]=nb;
			tableTrie[i]=i;
		}

		//On transforme et compare des String
		String trie = "",nonTrie = "";
		for(int i=0;i<10;i++)
		{
			nonTrie += tableNonTrie[i];
			trie += tableTrie[i];
		}
		
		System.out.println("Avant trie");
		System.out.println(nonTrie);
		
		//comparaison des tableaux avant le trie
		assertFalse(trie.equalsIgnoreCase(nonTrie));		

		Build bu = new Build( "Build", false, tableNonTrie);

		trie = "";
		nonTrie = "";
		bu.executTask(tableNonTrie);
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
