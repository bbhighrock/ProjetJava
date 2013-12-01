package fr.p10.miage.robot.test;

import static org.junit.Assert.*;


public class CleanTest {

	@Test
	public void test() {
		assertEquals(1, 1);
		Comparable[] tableNonTrie = new Comparable[10];
		Comparable[] tableTrie = new Comparable[10];

		for(int i=0,nb=9;i<10;i++, nb--)
		{
			tableNonTrie[i]=nb;
			tableTrie[i]=i;

		}
		Clean cl = new Clean( "Clean", false, tableNonTrie);

		String trie = "",nonTrie ="";
		cl.executTask(tableNonTrie);
		for(int i=0;i<10;i++)
		{
			nonTrie += tableNonTrie[i];
			trie += tableTrie[i];

		}
		//System.out.println(trie);
		//System.out.println(nonTrie);
		assertEquals(nonTrie, trie);
	}

}
