package inventoryman;

import java.util.Comparator;

public class CreatorComparator implements Comparator<Item>{
	
	@Override
	public int compare(Item item1, Item item2) {
		int result;
		//if current comparison is equal, order by next "order priority"
		result = item1.getCreator().compareToIgnoreCase(item2.getCreator());
		if (result == 0) {
			result = item1.getTitle().compareToIgnoreCase(item2.getTitle()); 
		}
		if (result == 0) {
			result = item1.getAcquisitionDate().compareToIgnoreCase(item2.getAcquisitionDate());
		}
		return result;
	}

}
