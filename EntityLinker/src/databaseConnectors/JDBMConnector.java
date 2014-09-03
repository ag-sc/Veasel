package databaseConnectors;

import java.io.IOException;
import java.util.LinkedList;
import datatypes.TermFrequency;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;

public class JDBMConnector extends DatabaseConnector{
	private RecordManager recman;
	private PrimaryHashMap<String, LinkedList<TermFrequency>> dbMap;
	
	public JDBMConnector(String dbName, String tableName) throws IOException{
		recman = RecordManagerFactory.createRecordManager(dbName);
		dbMap = recman.hashMap(tableName);
	}
	
	public void close(){
		try {
			recman.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public LinkedList<String> lookUpFragment(String fragment) {
		LinkedList<String> list = new LinkedList<String>();
		
		LinkedList<TermFrequency> tmp = dbMap.get(fragment);
		if(tmp != null){
			for(TermFrequency t: tmp){
				list.add(new String(t.term));
			}
		}
		
		return list;
	}

}