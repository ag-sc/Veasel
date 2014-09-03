package entityLinker;

import java.io.IOException;
import java.util.LinkedList;

import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import databaseConnectors.DatabaseConnector;
import databaseConnectors.JDBMConnector;
import datatypes.EntityOccurance;
import evaluation.EvaluationEngine;
import evaluation.RandomEvaluator;


public class EntityLinkerSandbox {

	public static void main(String[] args) {
		
		String input = "Romeo and Juliet is a tragedy written by William Shakespeare early in his career about two young star-crossed lovers whose deaths ultimately reconcile their feuding families. It was among Shakespeare's most popular plays during his lifetime and, along with Hamlet, is one of his most frequently performed plays. Today, the title characters are regarded as archetypal young lovers.";
		
		long start, end;
		double passedTime;
		EvaluationEngine evaluator = new RandomEvaluator();
		LinkedList<EntityOccurance> entityList;
		DatabaseConnector connector;
		try {
			start = System.nanoTime();
			connector = new JDBMConnector("../../data/Wikipedia Anchor/db/anchorKeyMap",
															"anchorKeyMap");
			EntityLinker linker = new EntityLinker(evaluator, connector);
			entityList = linker.link(input.toLowerCase());
			
			end = System.nanoTime();
			passedTime = (end - start) / 1000000.0;
			System.out.println("Calculation time: " + passedTime + " ms");
			for(EntityOccurance eo: entityList){
				System.out.println(eo);
			}
			
			RecordManager recman = RecordManagerFactory.createRecordManager("../../data/Wikipedia/Mappingbased Properties/db/db_02");
			PrimaryHashMap<Integer, String> intToUri = recman.hashMap("intToUri");
			PrimaryHashMap<String, Integer> uriToInt = recman.hashMap("UriToInt");
			
			Integer index = uriToInt.get("Romeo_and_Juliet");
			System.out.println("index: " + index);
			String value = intToUri.get(index);
			System.out.println("value: " + value);
			recman.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}