package gamevaluate.utilities;

import java.util.Comparator;

import gamevaluate.bean.Gioco;

public class ValutationSorter implements Comparator<Gioco> 
{
	boolean asc;
	
	public ValutationSorter(boolean asc) {
		this.asc = asc;
	}
	
    @Override
    public int compare(Gioco gioco1, Gioco gioco2) {
    	if(asc)
    		return gioco1.getValutazione() - gioco2.getValutazione();
    	else
    		return gioco2.getValutazione() - gioco1.getValutazione();
    }

}