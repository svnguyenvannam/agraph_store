package connection;

import java.util.ArrayList;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

import filereader.QueryReader;
import main.StructQuery;

/**
 * Cài đặt các query cơ bản và nâng cao theo yêu cầu 
 * @author toanloi
 */
public class Query {
	private DatabaseConnecter databaseConnecter;
	private AGRepositoryConnection conn;
	private ArrayList<StructQuery> listNormalQuery;
	private ArrayList<StructQuery> listAdvancedQuery;
	
	public Query(DatabaseConnecter databaseConnecter) {
		this.databaseConnecter = databaseConnecter;
		this.conn = databaseConnecter.getConnection();
		this.getListQuery();
	}
	
	/**
	 * Đọc query từ file text bằng đối tượng QueryReader
	 */
	public void getListQuery() {
		QueryReader reader = new QueryReader();
		this.listNormalQuery = reader.getListNormalQuery();
		this.listAdvancedQuery = reader.getListAdvancedQuery();
	}
	
	/**
	 * Nhận vào số i và in ra kết quả câu truy vấn thứ i
	 * @param number Số thứ tự câu truy vấn
	 */
	public void getResultQuery(int number) {
		
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
