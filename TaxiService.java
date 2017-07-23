package seventhass;

import java.util.Hashtable;
import java.util.LinkedList;

public class TaxiService{
static LinkedList<vertex>  llv=new LinkedList<vertex>();
static LinkedList<edge>  lle=new LinkedList<edge>();
static LinkedList<taxi> llt=new LinkedList<taxi>();
	
	public void  customer(vertex v1,vertex v2,int t){
	Hashtable<Integer,taxi> h=new Hashtable<Integer,taxi>();
	LinkedList<Integer> l=new LinkedList<Integer>();
	int min=Integer.MAX_VALUE;
		for(int i=0;i<llt.size();i++){
		
			
			
			if(llt.get(i).t<=t){
				
			shortestpath s=new shortestpath();
			s.find(llt.get(i).vt);
			if(s.path(v1)!=null){
				
			h.put(timetaken(s.path(v1)),llt.get(i));
			l.add(timetaken(s.path(v1)));
			
			System.out.println("Path of taxi "+llt.get(i).id+":"+s.path(v1)+".time taken is  "+timetaken(s.path(v1))+" units");
			}
			else if(llt.get(i).vt.v.equals(v1.v))
			{
				h.put(0,llt.get(i));
				l.add(0);
				
				System.out.println(llt.get(i).id+" is at "+v1.v );
				}
			else if(!llt.get(i).vt.v.equals(v1.v)){
				System.out.println("no path exists between "+llt.get(i).vt.v+" and " +v1.v);
			}
				
			}
		}
		if(!l.isEmpty()){
	for(int i=0;i<l.size();i++){
		if(min==Integer.MAX_VALUE){
			
			min=l.get(i);
		}
		else if(min>l.get(i)){
			min=l.get(i);
		}
		
	}
	shortestpath s2=new shortestpath();
	
	s2.find(h.get(min).vt);
	h.get(min).t=t;
	if(s2.path(v1)!=null){
	System.out.println("choose taxi "+h.get(min).id);//+" which takes " +timetaken(s2.path(v1))+" to travel "+s2.path(v1));
	h.get(min).vt=v2;
	h.get(min).t=h.get(min).t+timetaken(s2.path(v1));
	}
	else{
		System.out.println("choose taxi "+h.get(min).id);//+" is at "+ v1.v);
		h.get(min).vt=v2;
		
	}
		shortestpath s1=new shortestpath();
		s1.find(v1);
		
		System.out.println("path of customer:"+s1.path(v2)+".timetaken is "+timetaken(s1.path(v2))+" units");
		h.get(min).t=h.get(min).t+timetaken(s1.path(v2));
		}	
	}
	public int timetaken(LinkedList<String> L){
		int i=0;
		int t=0;

		while(i<L.size()-1){
			int k1=0;
			
			for(int j=0;j<lle.size();j++){
				if(lle.get(j).getsource().v.equals(L.get(i))){
					if(lle.get(j).getdst().v.equals(L.get(i+1))){
						k1=j;
				}
				
				}
				if(lle.get(j).getdst().v.equals(L.get(i))){
					if(lle.get(j).getsource().v.equals(L.get(i+1))){
						k1=j;
				}
				
				}
		
			}
			t=t+lle.get(k1).time;
			
			i=i+1;
			
		}
		return t;
	}
	
	public void performAction(String actionMessage) {
		System.out.println("action to be performed: " + actionMessage);
		
		String A[]=actionMessage.split("\\W+");
		if(A[0].equals("edge")){
			int j=Integer.MAX_VALUE;
			int k=Integer.MAX_VALUE;
			vertex v1=new vertex(A[1]);
			vertex v2=new vertex(A[2]);
			edge e1=new edge(v1,v2,Integer.parseInt(A[3]));
			
			lle.add(e1);
			
			if(llv.isEmpty()){
				v1.adj.add(v2);
				v2.adj.add(v2);
				llv.add(v1);
				llv.add(v2);
			}
			else
			{
				for(int i=0;i<llv.size();i++){
					if(llv.get(i).v.equals(v1.v)){
						j=i;
					}
					if(llv.get(i).v.equals(v2.v)){
						k=i;
					}
				}
			if(j==Integer.MAX_VALUE){
				llv.add(v1);
				j=llv.indexOf(v1);
				
			}
			if(k==Integer.MAX_VALUE){
				llv.add(v2);
				k=llv.indexOf(v2);
				
			}
			
		
		
			llv.get(j).adj.add(llv.get(k));
			llv.get(k).adj.add(llv.get(j));
				}
		
			
		}
	
		if(A[0].equals("taxi")){
			int k=Integer.MAX_VALUE;
			for(int i=0;i<llv.size();i++){
				if(llv.get(i).v.equals(A[2])){
					k=i;
					break;
				}
					
			}
			if(k!=Integer.MAX_VALUE){
			taxi t=new taxi(A[1],llv.get(k),0);
			llt.add(t);
			}
			else
				System.out.println(A[2]+" location where taxi is to be added is not there in graph");
			
		}
	
		if(A[0].equals("customer")){
			shortestpath s=new shortestpath();
			vertex v1=new vertex(A[1]);
			vertex v2=new vertex(A[2]);
			int t1=Integer.parseInt(A[3]);
			int j=Integer.MAX_VALUE;
			int k=Integer.MAX_VALUE;
			for(int i=0;i<llv.size();i++){
				
				if(llv.get(i).v.equals(v1.v)){
					j=i;
				}
				if(llv.get(i).v.equals(v2.v)){
					k=i;
				}
			}
			if(j!=Integer.MAX_VALUE && k!=Integer.MAX_VALUE && j!=k)
		customer(llv.get(j),llv.get(k),t1);
			if(j!=Integer.MAX_VALUE && k!=Integer.MAX_VALUE && j==k){
				System.out.println("customer  is at destination");
			}
			if(j==Integer.MAX_VALUE){
				System.out.println(v1.v+" is not there in graph");
			}
			if(k==Integer.MAX_VALUE){
				System.out.println(v2.v+" is not there in graph");
			}
			
		}
		if(A[0].equals("printTaxiPosition")){
			int j=0;
			for(int i=0;i<llt.size();i++){
				if(llt.get(i).t<=Integer.parseInt(A[1])){
				 j=1;
					System.out.println(llt.get(i).id + " : " +llt.get(i).vt.v);
				}
			}
			if(j==0){
				System.out.println("no taxi available");
			}
			
		}
	}
}