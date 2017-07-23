package seventhass;

import java.util.LinkedList;

public class vertex {
	String v;
	static LinkedList<String> lv=new LinkedList<String>();
	
LinkedList<vertex> adj;
	public vertex(String name){
		v=name;
		if(!lv.contains(name)){
		lv.add(name);
		adj=new LinkedList<vertex>();
		}
	}
	
	

}
