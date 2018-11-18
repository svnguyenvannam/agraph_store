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
		printRows_3(query, conn);
	}
	
	/**
	 * In ra thông tin của Person Sơn_Tùng_M-TP
	 */
	public void printResultQuery1(){
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type ent:Person . "
				+ "?s prs:tên_hiển_thị \"Sơn_Tùng_M-TP\" . "
				+ "?s ?p ?o . "
				+ "filter (?p != rdf:type && ?p != rel:)"
				+ "}";
		printRows_3(query, conn);
	}
	
	/**
	 * In ra thông tin của Person Kim Đồng
	 */
	public void printResultQuery2() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type ent:Person . "
				+ "?s prs:tên_hiển_thị \"Kim_Đồng\" . "
				+ "?s ?p ?o . "
				+ "filter (?p != rdf:type && ?p != rel:)"
				+ "}";
		printRows_3(query, conn);
	}
	
	/**
	 * In ra thông tin các thực thể tên là Kim Đồng 
	 */
	public void printResultQuery3() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s prs:tên_hiển_thị \"Kim_Đồng\" . "
				+ "?s ?p ?o . "
				+ "filter (?p != rdf:type && ?p != rel:)"
				+ "}";
		printRows_3(query, conn);
	}
	
	/**
	 * In ra nơi mà Nguyễn Phú Trọng đã đến 
	 */
	public void printResultQuery4() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?idx rdf:type ent:Location . "
				+ "?idx prs:tên_hiển_thị ?o . "
				+ "?idy ?p ?idx . "
				+ "?idy rdf:type ent:Person . "
				+ "?idy prs:tên_hiển_thị ?s. "
				+ "filter (?s = \"Nguyễn_Phú_Trọng\" && ?p = rel:đã_đến)"
				+ "}";
		printRows_3(query, conn);
	}
	
	/**
	 * In ra số người đã đến Location Vịnh Hạ Long
	 */
	public void printResultQuery5() {
		query = "select (count(?idy) as ?x)"
				+ "where {"
				+ "?idx rdf:type ent:Location . "
				+ "?idx prs:tên_hiển_thị \"Vịnh_Hạ_Long\" . "
				+ "?idy rel:đã_đến ?idx . "
				+ "?idy rdf:type ent:Person . "
				+ "}";
		printRows_1(query, conn);
	}
	
	/**
	 * In ra những người Liên Kết 12 cả nước Pháp và Đức
	 */
	public void printResultQuery6() {
		query = "select ?x "
				+ "where {"
				+ "?idx rdf:type ent:Country . "
				+ "?idx prs:tên_hiển_thị \"Pháp\" . "
				+ "?idy rdf:type ent:Country . "
				+ "?idy prs:tên_hiển_thị \"Đức\" . "
				+ "?idz rel:đã_đến ?idx . "
				+ "?idz rel:đã_đến ?idy ."
				+ "?idz rdf:type ent:Person . "
				+ "?idz prs:tên_hiển_thị ?x"
				+ "}";
		printRows_1(query, conn);
	}
	
	/**
	 * 
	 */
	public void printResultQuery7() {
		
	}
	
	/**
	 * 
	 */
	public void printResultQuery8() {
		
	}
	
	/**
	 * 
	 */
	public void printResultQuery9() {
		
	}
	
	/**
	 * 
	 */
	public void printResultQuery10() {
		
	}
	
	/**
	 * In ra kết quả trong truy vấn ?s ?p ?o
	 * @param query : Truy vấn truyền vào, phải là truy vấn dạng select ?s ?p ?o 
	 * @param conn : Đối tượng AGRepositoryConnection dùng để kết nối với Repository 
	 */
	private void printRows_3(String query, AGRepositoryConnection conn) {
		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			System.out.format("%s %s %s\n", removePrefix(s), removePrefix(p), removePrefix(o));
		}
	}
	
	/**
	 * In ra kết quả trong truy vấn ?x 
	 * @param query :  Truy vấn truyền vào, phải là truy vấn dạng select ?x
	 * @param conn : Đối tượng AGRepositoryConnection dùng để kết nối với Repository 
	 */
	private void printRows_1(String query, AGRepositoryConnection conn) {
		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value x = bind.getValue("x");
			System.out.format("%s\n", removePrefix(x));
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
		return uri.replace("_", " ");
	}
}
