import Struct.RandomProperties;
import connection.DatabaseConnecter;
import connection.Query;
import createdata.DataCreator;
import createdata.DataReader;

public class Main {
	public static void main(String[] args) {
		DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter("OOP_5000");
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
		new DataCreator(databaseConnecter).createRelationship(5000,"OOP_5000");
		Query q = new Query(databaseConnecter);
		q.printResultQuery0();
	}
	
	public static void calTime(int x, Query query) {
		
		return;
	}
}
