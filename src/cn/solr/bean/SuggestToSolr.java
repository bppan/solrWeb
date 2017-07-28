package cn.solr.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;

public class SuggestToSolr {
	public static SolrClient server;
	public static final String SOLR_URL = "http://127.0.0.1:8983/solr/core";
	public SuggestToSolr(){
		this.server = new HttpSolrClient(SOLR_URL);
	}
	public List<SuggestValue>  doSuggest(String keyWord){	
		SolrQuery params = new SolrQuery();
		params.set("qt", "/suggest");
		params.setQuery(keyWord);
		QueryResponse response = null;  
	    try {  
            response = this.server.query(params);  
            System.out.println("查询耗时：" + response.getQTime());  
        } catch (SolrServerException e) {  
            System.err.println(e.getMessage());  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.err.println(e.getMessage());  
            e.printStackTrace();  
        } finally {  
        	this.server.shutdown();  
        }  
	    List<SuggestValue>  best_suggest = new ArrayList<SuggestValue>();
		SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
        if (spellCheckResponse != null) {  
            List<Suggestion> suggestionList = spellCheckResponse.getSuggestions();
            if(suggestionList.size() == 0){
            	return best_suggest;
            }
            Suggestion suggestion = suggestionList.get(suggestionList.size() - 1);
//            for (Suggestion suggestion : suggestionList) {  
                System.out.println("Suggestions NumFound: " + suggestion.getNumFound());  
                System.out.println("Token: " + suggestion.getToken());  
                System.out.print("Suggested: ");  
                List<String> suggestedWordList = suggestion.getAlternatives();  
                for (String word : suggestedWordList) {  
                	SuggestValue suggest = new SuggestValue();
                	String suggest_string = getSuggestString(keyWord, word);
                	suggest.setValue(suggest_string);
                	best_suggest.add(suggest);
                    System.out.println(word + ", ");  
                }  
                System.out.println();  
//            }
        }

        return  best_suggest;
	}
	public String getSuggestString(String keyWord, String sugggest){
		String[] stringArray = keyWord.split("\\s+");
		String result = "";
		for(int i = 0; i < stringArray.length - 1; i++){
			if(i == 0){
				result += stringArray[i];
			}else{
				result += " " + stringArray[i];
			}
		}
		result += " " + sugggest;
		return result;
	}
	public String getCollaction(String keyWord){
		String result = "";
		SolrQuery params = new SolrQuery();
		params.set("qt", "/suggest");
		params.setQuery(keyWord);
		QueryResponse response = null;  
	    try {  
            response = this.server.query(params);  
            System.out.println("查询耗时：" + response.getQTime());  
        } catch (SolrServerException  e) {  
            System.err.println(e.getMessage());  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.err.println(e.getMessage());  
            e.printStackTrace();  
        } finally {  
        	this.server.shutdown();  
        }  
		SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
        List<Collation> collatedList = spellCheckResponse.getCollatedResults();  
        if (collatedList != null) {  
            for (Collation collation : collatedList) {          
                System.out.println("collated query String: " + collation.getCollationQueryString());  
                System.out.println();  
              	return  collation.getCollationQueryString();
            }  
        }  
		return result;
	}
}
