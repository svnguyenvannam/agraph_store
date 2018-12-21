package query;

import java.util.ArrayList;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

import filereader.QueryReader;
import setting.Setting;
import setting.StructQuery;

/**
 * Cài đặt các query cơ bản và nâng cao theo yêu cầu 
 * @author toanloi
 */
public class Query {
	private AGRepositoryConnection conn;
	private ArrayList<StructQuery> listNormalQuery;
	private ArrayList<StructQuery> listAdvancedQuery;
	
	public Query(AGRepositoryConnection conn) {
		this.conn = conn;
		this.getListQuery();
	}
	
	/**
	 * Đọc query từ file text bằng đối tượng QueryReader
	 */
	private void getListQuery() {
		QueryReader reader = new QueryReader();
		listNormalQuery = reader.getListNormalQuery();
		listAdvancedQuery = reader.getListAdvancedQuery();
	}
	
	/**
	 * Nhận vào số i và in ra kết quả câu truy vấn đơn giản thứ i
	 * @param number Số thứ tự câu truy vấn
	 */
	public void getResultNormalQuery(int number) {
//		listNormalQuery.get(number).printDescriptionQuery();
		TupleQueryResult result = getResult(listNormalQuery.get(number).Query, conn);
//		printRows(result);
	}
	
	public void getResultAdvancedQuery(int number) {
//		listAdvancedQuery.get(number).printDescriptionQuery();
		TupleQueryResult result = getResult(listAdvancedQuery.get(number).Query, conn);
//		printRows(result);
	}
	
	public TupleQueryResult getResult(String query, AGRepositoryConnection conn) {
		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		return result;
	}
	
	/**
	 * In ra kết quả trong truy vấn ?s ?p ?o
	 * @param query : Truy vấn truyền vào, phải là truy vấn dạng select ?s ?p ?o 
	 * @param conn : Đối tượng AGRepositoryConnection dùng để kết nối với Repository 
	 */
	private void printRows(TupleQueryResult result) {
		Value x = null;
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value lk1 = bind.getValue("lk1");
			Value p = bind.getValue("p");
			Value lk2 = bind.getValue("lk2");
			Value o = bind.getValue("o");
			Value lk3 = bind.getValue("lk3");
			Value t = bind.getValue("t");
			if (s != null && !s.equals(x)) System.out.println("------------------"); 
			System.out.format("%s %s %s %s %s %s %s \n", removePrefix(s), removePrefix(lk1), removePrefix(p), 
					removePrefix(lk2), removePrefix(o), removePrefix(lk3), removePrefix(t));
			x = s;
		}
		result.close();
	}
	
	/**
	 * Xóa các prefix của các URI trước khi in ra 
	 * @param uri_ : Value chứa URI truyền vào 
	 * @return String URI sau khi đã làm sạch các prefix 
	 */
	private static String removePrefix(Value uri_) {
		if (uri_ == null) return "";
		String uri = uri_.toString();
		uri = uri.replace(Setting.ENTITY_PREFIX, "");
		uri = uri.replace(Setting.PROPERTIES_PREFIX, "");
		uri = uri.replace(Setting.RELATIONSHIP_PREFIX, "");
		if (uri.charAt(0) == '"') {
			int pos = uri.indexOf('^');
			uri = uri.substring(1, pos - 1);
		}
		return uri.replace("_", " ");
	}
}
