package setting;

public class Query {

    public String querySyntax;    //cú pháp truy vấn
    public String queryDescription;  //mô tả truy vấn

    public Query(String queryDescription, String querySyntax) {
        this.queryDescription = queryDescription;
        this.querySyntax = querySyntax;
    }
}
