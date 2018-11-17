import com.franz.agraph.repository.AGRepositoryConnection;

import Struct.BasicStmt;
import connection.DatabaseConnecter;
import connection.Query;
import connection.Setting;
import createdata.DataCreator;
import entity.Country;
import entity.Location;
import entity.Person;
import entity.Time;

public class Main {
	public static void main(String[] args) {
		DatabaseConnecter databaseConnecter = DatabaseConnecter.getDatabaseConnecter("OOP_400");
//		DataCreator d = new DataCreator();
//		d.createData(400,"OOP_400");
//		databaseConnecter.setRepository("OOP_400");
		AGRepositoryConnection conn = databaseConnecter.getConnection();
		
		Query query = new Query(databaseConnecter);
//		query.printResultQuery0();
		query.printResultQuery1();
	}
}
