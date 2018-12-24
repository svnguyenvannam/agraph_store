package time;

import com.franz.agraph.repository.AGRepositoryConnection;

import query.QueryAction;

public class CalTime {
	private QueryAction query;
	
	public CalTime(AGRepositoryConnection conn) {
		this.query = new QueryAction(conn);
	}
	
	/**
	 * Tính thời gian chạy câu truy vấn thứ i đồng thời in kết quả ra màn hình
	 * @param number Số thứ tự câu truy ấn cần tính
	 */
	public String calTimeNormalQuery(int number) {
            String str="";
		long startTime = System.currentTimeMillis();
		query.getResultNormalQuery(number);
		long endTime = System.currentTimeMillis();
		
		long runTime = endTime - startTime;
		System.out.format("Runtime = %d\n", runTime);
                str=str+"\nTotal execution time = "+ runTime+"\n";
                return str;
	}
	
	public String calTimeAdvancedQuery(int number) {
            String str="";
		long startTime = System.currentTimeMillis();
		query.getResultAdvancedQuery(number);
		long endTime = System.currentTimeMillis();
		
		long runTime = endTime - startTime;
		System.out.format("Runtime = %d\n", runTime);
                str=str+"\nTotal execution time = "+ runTime + "\n";
                return str;
	}
}
