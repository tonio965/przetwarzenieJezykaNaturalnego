package zadanie5;

import java.util.Comparator;


public class ItemComparator implements Comparator<FrequentItemToZad5>{

	@Override
	public int compare(FrequentItemToZad5 arg0, FrequentItemToZad5 arg1) {
		
		if(arg0.getOccurences() > arg1.getOccurences()) {
			return 1;
		}
		
		else if(arg0.getOccurences() < arg1.getOccurences()) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
}
