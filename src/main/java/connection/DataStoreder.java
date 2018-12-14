package connection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import Struct.Dict;

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
	 * 
	 * @param properties : List các thuộc tính cần lưu trữ lấy từ dữ liệu thực thể .
	 *                   Phần tử nào trong mảng là null sẽ không được lưu
	 */
	public void storeEntity(Dict[] properties) {
		// Tạo thực thể định danh, tạo 2 triple để kết nối chúng 2 chiều
		// Tạo type của thực thể, kết nối thực thể với type
		ValueFactory vf = model.getValueFactory();
		IRI dinhDanh = vf.createIRI(Setting.ENTITY_PREFIX, (String) properties[0].V);
		IRI type = vf.createIRI(Setting.ENTITY_PREFIX, (String) properties[2].V);
		model.add(dinhDanh, RDF.TYPE, type);

		// Tạo các thuộc tính và nối với thực thể
		int i = 1;
		while (properties[i] != null) {
			if (properties[i].V != null) {
				IRI p = vf.createIRI(Setting.PROPERTIES_PREFIX, properties[i].K.toString());
				Literal o = vf.createLiteral(properties[i].V.toString());
				model.add(dinhDanh, p, o);
			}
			i++;
		}
	}

	public void storeRelationship(String ent1, String relationship, String ent2) {
		IRI s = vf.createIRI(Setting.ENTITY_PREFIX, ent1);
		IRI p = vf.createIRI(Setting.RELATIONSHIP_PREFIX, relationship);
		IRI o = vf.createIRI(Setting.ENTITY_PREFIX, ent2);
		model.add(s, p, o);
	}
}
