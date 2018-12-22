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
	public void calTimeNormalQuery(int number) {
		long startTime = System.currentTimeMillis();
		query.getResultNormalQuery(number);
		long endTime = System.currentTimeMillis();
		
		long runTime = endTime - startTime;
		System.out.format("Runtime = %d\n", runTime);
	}
	
	public void calTimeAdvancedQuery(int number) {
		long startTime = System.currentTimeMillis();
		query.getResultAdvancedQuery(number);
		long endTime = System.currentTimeMillis();
		
		long runtime = endTime - startTime;
		System.out.format("Runtime = %d\n", runtime);
	}
}
