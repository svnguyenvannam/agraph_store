package query;

public class Query {

    public String Query;    //cú pháp truy vấn
    public String Description;  //mô tả truy vấn

    public Query(String des, String quer) {
        this.Description = des;
        this.Query = quer;
    }
}
