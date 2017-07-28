package cn.solr.bean;

public class SuggestValue {
	
	public SuggestValue(String value){
		this.value = value;
	}
	public String value;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public SuggestValue(){
		this.value = "";
	}
}
