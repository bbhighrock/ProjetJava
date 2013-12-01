package fr.p10.miage.robot.test;

import static org.junit.Assert.*;

public class RepareTest {

	@Test
	public void test() {
		Comparable[] tableNonTrie = new Comparable[10];
		Comparable[] tableTrie = new Comparable[10];

		for(int i=0,nb=9;i<10;i++, nb--)
		{
			tableNonTrie[i]=nb;
			tableTrie[i]=i;

		}
		Repare rep = new Repare( "Repare", false, tableNonTrie);

		String trie = "",nonTrie ="";
		rep.executTask(tableNonTrie);
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
