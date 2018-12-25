package queryaction;

import java.util.ArrayList;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

import filereader.QueryReader;
import setting.Config;
import setting.Query;

/**
 * Cài đặt các query cơ bản và nâng cao theo yêu cầu
 *
 * @author toanloi
 */
public class QueryAction {
    
    public QueryAction() {
    	
    }

    public TupleQueryResult getResult(String query, AGRepositoryConnection conn) {
        TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
        TupleQueryResult result = tupleQuery.evaluate();
        return result;
    }

    /**
     * @param result
     * @return kết quả của câu truy vấn
     */
    public String printRows(TupleQueryResult result) {
        Value x = null;
        String str = "";
        while (result.hasNext()) {
            BindingSet bind = result.next();
            Value s = bind.getValue("s");
            Value lk1 = bind.getValue("lk1");
            Value p = bind.getValue("p");
            Value lk2 = bind.getValue("lk2");
            Value o = bind.getValue("o");
            Value lk3 = bind.getValue("lk3");
            Value t = bind.getValue("t");
            if (s != null && !s.equals(x) && x!=null) { str += "\n"; }
            x = s;
            str = str + removePrefix(s) + " " + removePrefix(lk1) + " " + removePrefix(p) + " "
                    + removePrefix(lk2) + " " + removePrefix(o) + " " + removePrefix(lk3) + " " + removePrefix(t) + "\n";
        }
        result.close();
        return str;
    }

    /**
     * Xóa các prefix của các URI trước khi in ra
     *
     * @param uri_ : Value chứa URI truyền vào
     * @return String URI sau khi đã clear các prefix
     */
    private static String removePrefix(Value uri_) {
        if (uri_ == null) { return ""; }
        String uri = uri_.toString();
        uri = uri.replace(Config.ENTITY_PREFIX, "");
        uri = uri.replace(Config.PROPERTIES_PREFIX, "");
        uri = uri.replace(Config.RELATIONSHIP_PREFIX, "");
        if (uri.charAt(0) == '"') {
            int pos = uri.indexOf('^');
            uri = uri.substring(1, pos - 1);
        }
        return uri.replace("_", " ");
    }
    
    public ArrayList<Query> getListAdvancedQuery() {
		String pathAdvancedQuery = Config.DIR_QUERY_PATH + "/AdvancedQuery.txt";
		QueryReader reader = new QueryReader(pathAdvancedQuery);
		ArrayList<Query> listAdvancedQuery = reader.readFile();
		return listAdvancedQuery;
	}	
    
    public ArrayList<Query> getListNormalQuery() {	
		String pathNormalQuery = Config.DIR_QUERY_PATH + "/NormalQuery.txt";
		QueryReader reader = new QueryReader(pathNormalQuery);
		ArrayList<Query> listNormalQuery = reader.readFile();
		return listNormalQuery;
    }
    
    /**
	 * Tính thời gian chạy câu truy vấn thứ i đồng thời in kết quả ra màn hình
	 * @param number Số thứ tự câu truy ấn cần tính
	 */
	public String calTime(String Query, AGRepositoryConnection conn) {
		String str = "";

		long startTime = System.currentTimeMillis();
		TupleQueryResult result = getResult(Query, conn);
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;

		str += printRows(result) + "\n\n------------------------------------" + "\nTotal execution time = "
				+ runTime + "ms" + "\n";
		return (str);
	}
}
