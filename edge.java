package seventhass;

import java.util.LinkedList;

public class edge {
	static LinkedList<vertex> le=new LinkedList<vertex>();
	vertex s;
	vertex d;
	int time;
	public edge(vertex v1,vertex v2,int t){
		int j=19999;
		int k=19999;
		
		time=t;
		s =new vertex(v1.v);
		d =new vertex(v2.v);
	
		}
	public vertex getsource(){
		return s;
	}
	public vertex getdst(){
		return d;
	}
	
}
