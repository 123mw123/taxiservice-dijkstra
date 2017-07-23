package seventhass;

import java.util.LinkedList;

public class graph {
static LinkedList<vertex> vg=TaxiService.llv;
static LinkedList<edge> eg=TaxiService.lle;
public LinkedList getvertexes(){
	return vg;
}
public LinkedList edges(){
	return eg;
}
	
}
