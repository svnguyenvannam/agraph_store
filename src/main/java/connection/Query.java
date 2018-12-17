package connection;

import org.eclipse.rdf4j.model.IRI;
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
	 * In ra thông tin của person "Rose_Somchai_Lock"
	 */
	public void printResultQuery1(){
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type class:Person . "
				+ "?s prs:tên_hiển_thị \"Rose_Somchai_Lock\" . "
				+ "?s ?p ?o . "
				+ "filter (strstarts(str(?p), \"http://btl/properties/\" ))"
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra thông tin các sự kiện "Vietnam_Tour_Ho_Chi_Minh_City" 
	 * sẽ được tổ chức tại France
	 */
	public void printResultQuery2() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type class:Event."
				+ "?s prs:tên_hiển_thị \"Vietnam_Tour_Ho_Chi_Minh_City\"."
				+ "?idx rdf:type class:Country."
				+ "?idx prs:tên_hiển_thị \"France\"."
				+ "?s rel:sẽ_được_tổ_chức_tại ?idx."
				+ "?s ?p ?o ."
				+ "filter (strstarts(str(?p), \"http://btl/properties/\"))"
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In thông tin người đầu tiên tìm thấy sống ở Location 	"Okayama,_Japan"
	 */
	public void printResultQuery3() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?idx rdf:type class:Location."
				+ "?idx prs:tên_hiển_thị \"Okayama,_Japan\"."
				+ "?s rdf:type class:Person."
				+ "?s rel:sống_ở ?idx."
				+ "?s ?p ?o."
				+ "filter (strstarts(str(?p), \"http://btl/properties/\" ))"
				+ "}"
				+ "limit 8";
		printRows(query, conn);
	}
	
	/**
	 * In ra số nơi mà công ty "Nintendo" phá sản
	 */
	public void printResultQuery4() {
		query = "select (count(?o) as ?s) "
				+ "where {"
				+ "?idy rdf:type class:Organization . "
				+ "?idy prs:tên_hiển_thị \"Nintendo\". "
				+ "?idy rel:phá_sản_tại ?idx . "
				+ "?idx rdf:type class:Location . "
				+ "?idx prs:tên_hiển_thị ?o . "
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra số người sống ở Location "Toronto,_Canada"
	 */
	public void printResultQuery5() {
		query = "select (count(?idy) as ?s)"
				+ "where {"
				+ "?idx rdf:type class:Location . "
				+ "?idx prs:tên_hiển_thị \"Toronto,_Canada\" . "
				+ "?idy rel:sống_ở ?idx . "
				+ "?idy rdf:type class:Person . "
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra tên những người đã đến cả nước Grenada và Liberia
	 */
	public void printResultQuery6() {
		query = "select ?s "
				+ "where {"
				+ "?idx rdf:type class:Country . "
				+ "?idx prs:tên_hiển_thị \"Grenada\" . "
				+ "?idy rdf:type class:Country . "
				+ "?idy prs:tên_hiển_thị \"Liberia\" . "
				+ "?idz rel:sống_ở ?idx . "
				+ "?idz rel:sống_ở ?idy ."
				+ "?idz rdf:type class:Person . "
				+ "?idz prs:tên_hiển_thị ?s"
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra những người liên kết 12 ở việt nam nhưng lại liên kết 11 ở Mỹ 
	 */
	public void printResultQuery7() {
		query =  "select ?s"
				+ "where {"
				+ "?idx rdf:type ent:Country."
				+ "?idx prs:tên_hiển_thị \"Việt_Nam\"."
				+ "?idy rdf:type ent:Country."
				+ "?idy prs:tên_hiển_thị \"Mỹ\"."
				+ "?idz rel:liên_kết_12 ?idx."
				+ "?idz rel:liên_kết_11 ?idy."
				+ "?idz prs:tên_hiển_thị ?s."
				+ "}";
		printRows(query, conn);
	}
	
	/**
	 * In ra thông tin những nước có dân số thấp hơn 100000 nhưng có GPD cao hơn 5000 
	 */
	public void printResultQuery8() {
		query = "select ?s ?p ?o "
				+ "where {"
				+ "?s rdf:type class:Country."
				+ "?s prs:dân_số ?ds."
				+ "?s prs:GDP ?gdp."
				+ "?s ?p ?o."
				+ "filter (?ds < 100000 && ?gdp > 5000 && strstarts(str(?p),\"http://btl/properties/\"))"
				+ "}";
		printRows(query, conn);
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
	
	public void printResultQuery(int x) {
		if (x == 1) {
			printResultQuery1();
		} else if (x == 2) {
			printResultQuery2();
		}
	}
	
	/**
	 * In ra kết quả trong truy vấn ?s ?p ?o
	 * @param query : Truy vấn truyền vào, phải là truy vấn dạng select ?s ?p ?o 
	 * @param conn : Đối tượng AGRepositoryConnection dùng để kết nối với Repository 
	 */
	private void printRows(String query, AGRepositoryConnection conn) {
		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		Value x = null;
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("s");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			if (!s.equals(x)) System.out.println("------------------------------------------"); 
			System.out.format("%s %s %s\n", removePrefix(s), removePrefix(p), removePrefix(o));
			x = s;
		}
	}
	
//	/**
//	 * In ra kết quả trong truy vấn ?x 
//	 * @param query :  Truy vấn truyền vào, phải là truy vấn dạng select ?x
//	 * @param conn : Đối tượng AGRepositoryConnection dùng để kết nối với Repository 
//	 */
//	private void printRows_1(String query, AGRepositoryConnection conn) {
//		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
//		TupleQueryResult result = tupleQuery.evaluate();
//		while (result.hasNext()) {
//			BindingSet bind = result.next();
//			Value x = bind.getValue("x");
//			System.out.format("%s\n", removePrefix(x));
//		}
//	}
	
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
