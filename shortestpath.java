package seventhass;

import java.util.Hashtable;
import java.util.LinkedList;

public class shortestpath {
LinkedList<vertex> sv;
LinkedList<vertex> uv;
Hashtable<String,String> fp;
Hashtable<String,Integer> tim;
public void find(vertex V){
	
	sv=new LinkedList<vertex>();
	uv=new LinkedList<vertex>();
	fp=new Hashtable<String,String>();
	tim=new Hashtable<String,Integer>();
	int k=0;
	for(int j=0;j<TaxiService.llv.size();j++){
		if(TaxiService.llv.get(j).v.equals(V.v)){
			k=j;
			
			break;
		}
	}
	
	tim.put(TaxiService.llv.get(k).v,0);
	uv.add(TaxiService.llv.get(k));
	
	while(!uv.isEmpty()){
		vertex v=min(uv);
		sv.add(v);
		
		uv.remove(v);
		updatedistances(v);
		
	}
	
}
public void updatedistances(vertex v1){
	
	LinkedList<vertex> l=new LinkedList<vertex>();
	l.addAll(getadjvertexes(v1));
	
	for(vertex v2: l){
		int j=0;
		
		if(getsd(v2)>getsd(v1)+gettime(v1,v2)){
			
			
			tim.put(v2.v,getsd(v1)+gettime(v1,v2));
			fp.put(v2.v,v1.v);
			
				if(uv.isEmpty())
				uv.add(v2);
				for(int i=0;i<uv.size();i++){
					if(uv.get(i).v.equals(v2.v)){
						j=1;
						}
			}
				if(j==0){
					uv.add(v2);
				}
					
			
				}
		
	}
	
	
}

	public int gettime(vertex v1,vertex v2){
		int tm=0;
		
		for(edge ed : TaxiService.lle){
			if(ed.getsource().v.equals(v1.v)){
				if(ed.getdst().v.equals(v2.v)){
					tm=ed.time;
				
				}
			}
				else if(ed.getdst().v.equals(v1.v)){
					if(ed.getsource().v.equals(v2.v)){
						tm=ed.time;
						
					}
				}
			
		}
		
	return tm;
	}
	public LinkedList<vertex> getadjvertexes(vertex v1){
		
		
		
		LinkedList<vertex> l=new LinkedList<vertex>();
		
		for(edge ed:TaxiService.lle){
			int k=0;
			int j=0;
			if(ed.getsource().v.equals(v1.v)){
			
				if(sv.isEmpty()){
					l.add(ed.getdst());
				
				}
				else {
					for(int i=0;i<sv.size();i++ ){
						if(sv.get(i).v.equals(ed.getdst().v)){
							k=1;
							
						}
					}
					if(k==0){
						if(!l.contains(ed.getdst()))
						l.add(ed.getdst());
						
					}
					
				}
				
					
				
			}
			if(ed.getdst().v.equals(v1.v)){
								if(sv.isEmpty()){
					l.add(ed.getsource());
				}
				else {
					for(int i=0;i<sv.size();i++ ){
						if(sv.get(i).v.equals(ed.getsource().v)){
							j=1;
						
					}
					if(j==0){
						
						if(!l.contains(ed.getsource()))
						l.add(ed.getsource());
					}
					
				}
				
			}
		}
		
		}
		
		return l;
		
	}
	public vertex min(LinkedList<vertex> L){
		
		vertex m=null;
		for(vertex v:L){
			if(m==null){
				m=v;
			}
			else {
				if(getsd(v)<getsd(m)){
			
					m=v;
				}
			}
		}
		
		return m;
	}
	public int getsd(vertex v1){
		int k=999999999;
	
		for(int j=0;j<TaxiService.llv.size();j++){
			if(TaxiService.llv.get(j).v.equals(v1.v))
			{	k=j;
			    break;
			}
		}
		
	Integer i=tim.get(TaxiService.llv.get(k).v);
		if(i==null){
			
			return  Integer.MAX_VALUE;
			
		}
		else{
		
		return i;
		}
		
	}
	
	
	public LinkedList<String> path(vertex v){
		
		LinkedList<String> p=new LinkedList<String>();
		LinkedList<String> p1=new LinkedList<String>();
		String v2=v.v;
		
		if(fp.get(v2)==null){
			
			return null;
			
		}
		
			p.add(v2);
			
			
			while(fp.get(v2)!=null){
				v2=fp.get(v2);
				p.add(v2);
				
			}
			while(p.size()!=0){
				p1.add(p.getLast());
				p.removeLast();
			}
			return p1;
		
		
	}
	
	
}
