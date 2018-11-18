package connection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import com.franz.agraph.repository.AGRepositoryConnection;

import Struct.Dict;

/**
 * Dùng để thực hiện riêng các truy vấn store dữ liệu vào database.
 */
public class DataStoreder {
	DatabaseConnecter databaseConnecter;
	AGRepositoryConnection conn;
	ValueFactory vf;
	
	public DataStoreder(DatabaseConnecter databaseConnecter) {
		this.databaseConnecter = DatabaseConnecter.getDatabaseConnecter();
		this.conn = databaseConnecter.getConnection();
		this.vf = conn.getValueFactory();
	}

	/**
	 * Lưu trữ thực thể vào DB.
	 * 
	 * @param properties : List các thuộc tính cần lưu trữ lấy từ dữ liệu thực thể .
	 *                   Phần tử nào trong mảng là null sẽ không được lưu
	 */
	public void storeEntity(Dict[] properties) {
		// Tạo thực thể định danh, tạo 2 triple để kết nối chúng 2 chiều
		// Tạo type của thực thể, kết nối thực thể với type
		ValueFactory vf = conn.getValueFactory();
		IRI dinhDanh = vf.createIRI(Setting.ENTITY_PREFIX, (String) properties[0].V);
		IRI type = vf.createIRI(Setting.ENTITY_PREFIX, (String) properties[2].V);
		conn.add(dinhDanh, RDF.TYPE, type);

		// Tạo các thuộc tính và nối với thực thể
		int i = 1;
		while (properties[i] != null) {
			if (properties[i].V != null) {
				IRI p = vf.createIRI(Setting.PROPERTIES_PREFIX, (String) properties[i].K);
				Literal o = vf.createLiteral((String) properties[i].V);
				conn.add(dinhDanh, p, o);
			}
			i++;
		}
	}

	public void storeRelationship(String ent1, String relationship, String ent2) {
		IRI s = vf.createIRI(Setting.ENTITY_PREFIX, ent1);
		IRI p = vf.createIRI(Setting.RELATIONSHIP_PREFIX, relationship);
		IRI o = vf.createIRI(Setting.ENTITY_PREFIX, ent2);
		conn.add(s, p, o);
	}
}
