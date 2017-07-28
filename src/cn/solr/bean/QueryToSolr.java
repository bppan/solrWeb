package cn.solr.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import net.sf.json.JSONArray;


public class QueryToSolr {

	public String queryString;
	public ResultHead resHead;
	public List<ResultObject> res;
	
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public ResultHead getResHead() {
		return resHead;
	}
	public void setResHead(ResultHead resHead) {
		this.resHead = resHead;
	}
	public List<ResultObject> getRes() {
		return res;
	}
	public void setRes(List<ResultObject> res) {
		this.res = res;
	}
	
	public static SolrClient server;
	
	public static final String SOLR_URL = "http://127.0.0.1:8983/solr/core";
	public QueryToSolr(){
		System.out.println("test");	
		this.server = new HttpSolrClient(SOLR_URL);
	}
	public void getQueryResult(String queryString, int start, int rows){
//		 SolrClient server =  new HttpSolrClient(SOLR_URL);
//		
		 this.queryString = queryString;
		 SolrQuery solrQuery = new  SolrQuery();
		 solrQuery.setQuery(this.queryString);
		 solrQuery.setHighlight(true);
		 solrQuery.addHighlightField("body");
		 solrQuery.setHighlightSimplePre("<font color='red'>");
		 solrQuery.setHighlightSimplePost("</font>");
		 solrQuery.setHighlightSnippets(1);//结果分片数，默认为1
		 solrQuery.setHighlightFragsize(200);//每个分片的最大长度，默认为100
		 solrQuery.setStart(start);
		 solrQuery.setRows(rows);
		 solrQuery.set("wt", "json");
		 QueryResponse rsp = null;
		 try {
			 rsp = server.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//设置head
		 SolrDocumentList docs = rsp.getResults(); 
		 this.resHead = new ResultHead();
		 this.resHead.numFoound = ""+ docs.getNumFound();
		 this.resHead.QTime = ""+rsp.getQTime();
		 //设置result
		this.res = new ArrayList();
		Map<String, Map<String, List<String>>> highlightresult=rsp.getHighlighting();  
        
		rsp.getHighlighting();
		for(int i = 0; i < 	docs.size(); i++){
			SolrDocument document= docs.get(i);  
			ResultObject tempres = new 	ResultObject();
			tempres.id  = docs.get(i).getFieldValue("id").toString();
			tempres.url = docs.get(i).getFieldValue("url").toString();
			tempres.url_text = docs.get(i).getFieldValue("url_text").toString();
			tempres.title = docs.get(i).getFieldValue("title").toString();
			 String highlight = null;
			if(highlightresult.get(document.get("id").toString()).size() != 0){
				highlight = highlightresult.get(document.get("id").toString()).get("body").get(0);  
			}else{
				if(docs.get(i).getFieldValue("body").toString().length() > 200){
					highlight =  docs.get(i).getFieldValue("body").toString().substring(0, 200);
				}else{
					highlight =  docs.get(i).getFieldValue("body").toString();
				}		
			}       
            tempres.body = highlight;
            System.out.println(highlight);
            this.res.add(tempres);
//			System.out.println((String) res.get(tempres.id));
//			tempres.body = (String) res.get(tempres.id);
		}
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
