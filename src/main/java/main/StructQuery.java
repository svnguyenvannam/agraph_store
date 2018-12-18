package main;
public class StructQuery {
	String Query;
	String Description;
	
	public StructQuery(String des, String quer) {
		this.Description = des;
		this.Query = quer;
	}
	
	public void printQuery() {
		System.out.format("Description : %s\nQuery : %s\n",	 this.Description, this.Query);
	}
}