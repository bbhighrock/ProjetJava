package fr.p10.miage.robot.test;

import static org.junit.Assert.*;

public class DestroyTest {

	@Test
	public void test() {
		Comparable[] tableNonTrie = new Comparable[10];
		Comparable[] tableTrie = new Comparable[10];

		for(int i=0,nb=9;i<10;i++, nb--)
		{
			tableNonTrie[i]=nb;
			tableTrie[i]=i;

		}
		Destroy dest = new Destroy( "Destroy", false, tableNonTrie);

		String trie = "",nonTrie ="";
		dest.executTask(tableNonTrie);
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
