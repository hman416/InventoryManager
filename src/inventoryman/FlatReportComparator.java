package inventoryman;

import java.util.Comparator;

public class FlatReportComparator implements Comparator<Item>{

	@Override
	public int compare(Item item1, Item item2) {
		//if current comparison is equal, order by next "order priority"
		int result = item1.getOwner().compareTo(item2.getOwner());
		
		if (result == 0) {
			result = compareFormats(item1.getFormat().toString(), item2.getFormat().toString());
		}
		
		if (result == 0) {
			result = item1.getCreator().compareTo(item2.getCreator());	
			}
		if (result == 0) {
			result = item1.getTitle().compareTo(item2.getCreator());	
			}
					
		return result;
	}
	//method for comparing formats to prevent convoluting compare method
	private int compareFormats(String format1, String format2) {
		int result;
		if((format1.equals("Hardcover") || format1.equals("Paperback")) && (format2.equals("CD") || format2.equals("LP"))) {
			result = -1;
		}else if(((format1.equals("CD") || format1.equals("LP")) && (format2.equals("Hardcover") || format2.equals("Paperback")))){
			result = 1;
		}else {
			result = 0;
		}		
		return result;
	}

}
