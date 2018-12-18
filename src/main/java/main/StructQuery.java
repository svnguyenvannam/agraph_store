package main;
public class StructQuery {
	public String Query;
	public String Description;
	
	public StructQuery(String des, String quer) {
		this.Description = des;
		this.Query = quer;
	}
	
	public void printDescriptionQuery() {
		System.out.println("_______________________________________________________________");
		System.out.format("Description : %s\nQuery : %s\n",	 this.Description, this.Query);
	}
}