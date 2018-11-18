package createdata;

import org.eclipse.rdf4j.spin.function.spif.ForEach;

import com.google.common.collect.ObjectArrays;

import connection.Setting;

/**
 * Đọc các Raw Data từ file text.Trả ra các Data đọc được dạng String[]
 * Dữ liệu tất cả các thực thể được đặt vào 1 file riêng, tương tự đối với 
 * miêu tả của chúng.
 * 
 * @author toanloi
 */
public class RawDataReader {
	private String[] entityData = new String[0];
	private String[] descriptionData = new String[6*Setting.DEFAULT_INDEX];
	private String[] relationshipData = new String[Setting.REL_INDEX];
	private String[] entityPathFile = new String[6];
	private String[] descriptionPathFile = new String[6];
	private FileReader reader;
	
	/**
	 * Đọc dữ liệu vào các mảng tương ứng.
	 */
	public void readRawData() {
		setupPathFile();
		String pathFile;
		for (int i = 0; i < 6; i++) {
			pathFile = entityPathFile[i];
			reader = new FileReader(pathFile, Setting.DEFAULT_INDEX);
			entityData = ObjectArrays.concat(entityData, reader.readFile(), String.class);
			for (String string : entityData) {
				System.out.println(string);
			}
			
			pathFile = descriptionPathFile[i];
			reader = new FileReader(pathFile, Setting.DEFAULT_INDEX);
			descriptionData = ObjectArrays.concat(descriptionData,reader.readFile(), String.class);
		}
		
		reader = new FileReader(Setting.DIR_DATA_PATH+"/relationship.txt", Setting.REL_INDEX);
		relationshipData = reader.readFile();
	}

	/**
	 * Cài đặt các path dẫn đến file dữ liệu
	 */
	private void setupPathFile() {
		String[] str = { "Person", "Organization", "Location", "Event", "Country", "Time" };
		for (int i = 0; i < 6; i++) {
			entityPathFile[i] = Setting.DIR_DATA_PATH + "/" + str[i] + ".txt";
			descriptionPathFile[i] = Setting.DIR_DATA_PATH + "/" + str[i] + "Description.txt";
		}
	}

	public String[] getEntityData() {return entityData;}
	public String[] getRelationshipData() {return relationshipData;}
	public String[] getDescriptionData() {return descriptionData;}
}
