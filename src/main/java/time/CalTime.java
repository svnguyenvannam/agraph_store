package time;

import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

import query.QueryAction;

public class CalTime {
	private QueryAction queryAction;

	public CalTime(AGRepositoryConnection conn) {
		this.queryAction = new QueryAction(conn);
	}

	/**
	 * Tính thời gian chạy câu truy vấn thứ i đồng thời in kết quả ra màn hình
	 * 
	 * @param number Số thứ tự câu truy ấn cần tính
	 */

	public String calTime(String Query) {
		String str = "";

		long startTime = System.currentTimeMillis();
		TupleQueryResult result = queryAction.getResult(Query);
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;

		str += queryAction.printRows(result) + "\n\n------------------------------------" + "\nTotal execution time = "
				+ runTime + "ms" + "\n";
		return (str);
	}
}
