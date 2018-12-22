package createdata;

import java.util.HashMap;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import setting.Config;

/**
 * Dùng để thực hiện riêng các truy vấn store dữ liệu vào database.
 */
public class DataStoreder {
	TreeModel model;
	ValueFactory vf;
	
	public DataStoreder(TreeModel model) {
		this.model = model;
		this.vf = model.getValueFactory();
	}

	/**
	 * Lưu trữ thực thể vào DB.
	 * @param properties : List các thuộc tính cần lưu trữ lấy từ dữ liệu thực thể .
	 * Phần tử nào trong mảng là null sẽ không được lưu
	 */
	public void storeEntity(HashMap<Object, Object> properties) {
		// Tạo thực thể định danh, tạo 2 triple để kết nối chúng 2 chiều
		// Tạo type của thực thể, kết nối thực thể với type
		IRI dinhDanh = vf.createIRI(Config.ENTITY_PREFIX, (String) properties.get("định_danh"));
		IRI type = vf.createIRI(Config.CLASS_PREFIX, (String) properties.get("thực_thể"));
		model.add(dinhDanh, RDF.TYPE, type);

		// Tạo các thuộc tính và nối với thực thể
		properties.remove("định_danh");
		properties.remove("thực_thể");
		properties.forEach((key, value) -> {
			Literal o;
			IRI p = vf.createIRI(Config.PROPERTIES_PREFIX, (String) key);
			if (value instanceof String) o = vf.createLiteral((String) value);
			else o = vf.createLiteral((Integer) value);
			model.add(dinhDanh, p, o);
		});
	}

	public void storeRelationship(String ent1, String relationship, String ent2) {
		IRI s = vf.createIRI(Config.ENTITY_PREFIX, ent1);
		IRI p = vf.createIRI(Config.RELATIONSHIP_PREFIX, relationship);
		IRI o = vf.createIRI(Config.ENTITY_PREFIX, ent2);
		model.add(s, p, o);
	}
}
