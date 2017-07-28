package cn.solr.bean;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String query = "solr";
		QueryToSolr tese = new QueryToSolr();
		tese.getQueryResult(query, 0, 5);
		
	}

}
