package connection;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

import entity.Entity;

/**
 * Cài đặt các query cơ bản và nâng cao theo yêu cầu 
 * @author toanloi
 */
public class Query {
	private DatabaseConnecter databaseConnecter;
	private AGRepositoryConnection conn;
	private String query;
	
	public Query(DatabaseConnecter databaseConnecter) {
		this.databaseConnecter = databaseConnecter;
		this.conn = databaseConnecter.getConnection();
	}
	
	/**
	 * In ra tất cả các triple trong db  
	 */
	public void printResultQuery0() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s ?p ?o"
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra thông tin của Person Sơn Tùng M-TP
	 */
	public void printResultQuery1(){
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type ent:Person . "
				+ "?s prs:Tên_Hiển_Thị \"Sơn Tùng M-TP\" . "
				+ "?s ?p ?o . "
				+ "filter (?p != rdf:type && ?p != rel:)"
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra thông tin của Person Hang Nguyen
	 */
	public void printResultQuery2() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type ent:entity.Person. "
				+ "?s prs:TenHienThi \"Hang Nguyen\"."
				+ "?s ?p ?o."
				+ "filter (?p != rdf:type)"
				+ "bind(str(?s) as ?s) "
				+ "bind(str(?p) as ?p) "
				+ "bind(str(?o) as (o) "
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra Location công ty Apple được thành lập 
	 */
	public void printResultQuery3() {
		query = "select ?s ?";
	}
	
	/**
	 * In ra kết quả trong truy vấn ?s ?p ?o
	 * @param query : Truy vấn truyền vào, phải là truy vấn dạng select ?s ?p ?o 
	 * @param conn : Đối tượng AGRepositoryConnection dùng để kết nối với Repository 
	 */
	private void printRows(String query, AGRepositoryConnection conn) {
		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			System.out.format("%s    %s     %s\n", removePrefix(s), removePrefix(p), removePrefix(o));
		}
	}
	
	/**
	 * Xóa các prefix của các URI trước khi in ra 
	 * @param uri_ : Value chứa URI truyền vào 
	 * @return String URI sau khi đã làm sạch các prefix 
	 */
	private static String removePrefix(Value uri_) {
		String uri = uri_.toString();
		uri = uri.replace(Setting.ENTITY_PREFIX, "");
		uri = uri.replace(Setting.PROPERTIES_PREFIX, "");
		uri = uri.replace(Setting.RELATIONSHIP_PREFIX, "");
		if (uri.charAt(0) == '"') {
			int pos = uri.indexOf('^');
			uri = uri.substring(1, pos - 1);
		}
		return uri;
	}
}
