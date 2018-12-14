import com.franz.agraph.repository.AGRepositoryConnection;

import Struct.RandomProperties;
import connection.DatabaseConnecter;
import connection.Query;
import createdata.DataCreator;

public class Main {
	public static void main(String[] args) {
		DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter("OOP_5000000");
		databaseConnecter.setRepository("OOP_5000000");
		AGRepositoryConnection conn = databaseConnecter.getConnection();
		conn.clear();
//		DataCreator d = new DataCreator();
//		d.createData(15000,"OOP_15000");
//		databaseConnecter.setRepository("OOP_15000");
//		AGRepositoryConnection conn = databaseConnecter.getConnection();
//		AGValueFactory vf = conn.getValueFactory();
				
//		Query query = new Query(databaseConnecter);
//		long startTime= System.currentTimeMillis();
//		query.printResultQuery0();
//	
//		query.printResultQuery7();
//		long endTime=System.currentTimeMillis();
//		long runTime=(endTime-startTime);
//		
//		System.out.print(runTime + "ms");
		long startTime= System.currentTimeMillis();
		DataCreator creator = new DataCreator(databaseConnecter);
		creator.createData();
		creator.createRelationship(5000000);
		System.out.println(conn.size());
		long endTime=System.currentTimeMillis();
		long runTime=(endTime-startTime);
		System.out.print(runTime + "ms");
//		Query q = new Query(databaseConnecter);
//		q.printResultQuery0();
//		RandomProperties p = new RandomProperties();
//		for (int i = 0; i < 220; i++)
//			System.out.println(p.getRandomDate());
	}
	
	public static void calTime(int x, Query query) {
		
		return;
	}
}
