package fr.p10.miage.robot.test;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import fr.p10.miage.robot.model.Task;

public class TestTasks {
  @Test
  		private Task tache;
  		private Comparable [] monTableau = {new Integer(5),new Integer(3),
		  										new Integer(9),new Integer(4)};
  		
		
		@BeforeClass
		public void setUpClass(){
			tache = new TriInsertion();
			tache = new TriSelection();
		}
		
		@AfterClass
		public void tearDownClass(){
			rps = null;
		}
		
		/*DATA PROVIDERS*/
		@DataProvider(name = "testWinData")
		Object[][] testWinData() {
			return (new Object[][] {{RPSEnum.PAPER, RPSEnum.ROCK}, 
									{RPSEnum.SCISSORS,RPSEnum.PAPER }, 
									{RPSEnum.ROCK, RPSEnum.SCISSORS}});
		}
		
		@DataProvider(name = "testTieData")
		Object[][] testTieData() {
			return (new Object[][] {{RPSEnum.PAPER, RPSEnum.PAPER}, 
									{RPSEnum.ROCK, RPSEnum.ROCK}, 
									{RPSEnum.SCISSORS,RPSEnum.SCISSORS}});
		}
		
		@DataProvider(name = "testLostData")
		Object[][] testLostData() {
			return (new Object[][] {{RPSEnum.ROCK, RPSEnum.PAPER}, 
									{RPSEnum.SCISSORS, RPSEnum.ROCK}, 
									{RPSEnum.PAPER,RPSEnum.SCISSORS}});
		}
		
		
		/****** TEST ******/
		
		//AVEC PARAMETRES
		
		@Test
		@Parameters({"ROCK", "SCISSORS"})
		public void testWinPlay(String a, String b){
			assertEquals(Result.WIN, rps.play(RPSEnum.valueOf(a),RPSEnum.valueOf(b)));
		}
		
		@Test
		@Parameters({"PAPER", "PAPER"})
		public void testTiePlay(String a, String b){
			assertEquals(Result.TIE, rps.play(RPSEnum.valueOf(a),RPSEnum.valueOf(b)));
		}
		
		@Test
		@Parameters({"PAPER", "SCISSORS"})
		public void testLostPlay(String a, String b){
			assertEquals(Result.LOST, rps.play(RPSEnum.valueOf(a),RPSEnum.valueOf(b)));
		}
		
		
		//AVEC DATA PROVIDERS
		
		@Test(dataProvider = "testWinData")
		public void testWinPlay(RPSEnum a, RPSEnum b){
			assertEquals(Result.WIN,rps.play(a,b));
		}
		
		@Test(dataProvider = "testTieData")
		public void testTiePlay(RPSEnum a, RPSEnum b){
			assertEquals(Result.TIE,rps.play(a,b));
		}
		
		@Test(dataProvider = "testLostData")
		public void testLostPlay(RPSEnum a, RPSEnum b){
			assertEquals(Result.LOST,rps.play(a,b));
		}
  }
