package Struct;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;

import com.franz.agraph.repository.AGRepositoryConnection;

public class BasicStmt {
	public static void printRows(String query, AGRepositoryConnection conn) {
		TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		TupleQueryResult result = tupleQuery.evaluate();
		while (result.hasNext()) {
			BindingSet bind = result.next();
			Value s = bind.getValue("x");
			Value p = bind.getValue("p");
			Value o = bind.getValue("o");
			System.out.format("%s	%s %s\n", s, p ,o);
		}
	}

	public static String removePrefix(String uri) {
		int pos = uri.indexOf('^');
		uri = uri.substring(1, pos - 1);
		return uri;
	}

	public static void println(Object obj) {
		System.out.println(obj);
	}
}
