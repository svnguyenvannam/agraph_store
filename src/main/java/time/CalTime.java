package time;

import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

import query.QueryAction;

public class CalTime {
	private QueryAction queryAction = new QueryAction();

	public CalTime(AGRepositoryConnection conn) {
		
	}

	/**
	 * Tính thời gian chạy câu truy vấn thứ i đồng thời in kết quả ra màn hình
	 * 
	 * @param number Số thứ tự câu truy ấn cần tính
	 */

	public String calTime(String Query, AGRepositoryConnection conn) {
		String str = "";

		long startTime = System.currentTimeMillis();
		TupleQueryResult result = queryAction.getResult(Query, conn);
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;

		str += queryAction.printRows(result) + "\n\n------------------------------------" + "\nTotal execution time = "
				+ runTime + "ms" + "\n";
		return (str);
	}
}
