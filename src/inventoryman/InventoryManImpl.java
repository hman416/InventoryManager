package inventoryman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashSet;

public class InventoryManImpl implements InventoryMan{
	private List<Item> _inventory; 
	private String _flat;
	
	public InventoryManImpl(String flatName) {
		_inventory = new ArrayList<>();
		_flat = flatName;
	}

	@Override  
	public String addBook(String author, String title, String publicationYear, String publisher,
			String acquisitionDateStr, String owner, String costStr, String formatStr) {			
		try {//try to add book if strings are formatted correctly
			if (checkDateFormat(acquisitionDateStr) && checkCostFormat(costStr)) {
				Book book = new Book(author, title, publicationYear, publisher,	acquisitionDateStr, 
						owner, costStr, formatStr); 
				_inventory.add(book);
				return "Success";			
			}else{
				throw new IncorrectFormatException("ERROR Incorrect formatting of date or cost");
			}				
		}
		catch (IncorrectFormatException exp){//returns error message
			return ("ERROR Incorrect formatting of date or cost");
		}	
	}

	@Override
	public String addMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
			String costStr, String formatStr){
		try {//try to add music if strings are formatted correctly
			if (checkDateFormat(releaseDateStr) && checkDateFormat(acquisitionDateStr) && checkCostFormat(costStr)) {
				
				Music music = new Music(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr); 
				_inventory.add(music);
	
				return "Success";
			}else{
				throw new IncorrectFormatException("ERROR Incorrect formatting of date or cost");
			}				
		}
		catch (IncorrectFormatException exp){//returns error message
			return ("ERROR Incorrect formatting of date or cost");
		}

	}

	@Override
	public String getItemToDisplay(String creator, String title, String formatStr) {
		for (Item item: _inventory) {
			//finds item with matching specifications
			if ((item.getCreator().equals(creator))&&(item.getTitle().equals(title))&&
			item.getFormat().toString().equals(formatStr)){
				
			return item.toString();		
			}
		}//returns error message
		return "ERROR item not in list";
	}

	@Override
	public List<String> getAll(String order) { 
		List<String> sortedItems = new ArrayList<>();
		Comparator<Item> com;
		
		try {
			//create comparator object specific to request
			switch(order) { 
			case "Creator":
				com = new CreatorComparator();
				break;
						
			case "Title": 
				com = new TitleComparator();
				break;
				
			case "Acquisition":
				com = new AcquisitionDateComparator(); 
				break;
		
			default:
				throw new InvalidOrderException("ERROR invalid method of ordering has been entered");				
			}
			Collections.sort(_inventory, com);
			
		}catch(InvalidOrderException exp) {
			//no sorting as the sort order request is invalid 
		}finally {
			for (Item item: _inventory) { //return _inventory as a list of strings
				sortedItems.add(item.toString());
			}			
		}
		return sortedItems;
	}
	@Override
	public List<String> getItemsAcquiredInYear(String year) {//need to sort order
		List<String> itemList = new ArrayList<>();
				
		getAll("Acquisition"); //use getAll method to sort inventory
		
		for(Item item: _inventory) {
			if(item.getAcquisitionDate().contains(year)) {
				itemList.add(item.toString());
			}
		}
		return itemList;
	}

	@Override
	public List<String> getCreators() {
		HashSet<String> unsortedCreators = new HashSet<String>();
		
		for(Item item: _inventory) {//HashSet is used to prevent duplicate creators in collection
			unsortedCreators.add(item.getCreator());
		}
		//convert HashSet of creators to arraylist
		List<String> list = new ArrayList<>(unsortedCreators);
		
		Collections.sort(list); //sort strings by alphabetical order
		return list;
	}

	@Override
	public List<String> getFlatReport() {
		List<String> report = new ArrayList<>();
		report.add(_flat);
		String format;
		Comparator<Item> com = new FlatReportComparator();
		
		Collections.sort(_inventory, com);
		
		for (Item item: _inventory) {//creates appropriate string for each item according to format
			if (item.getFormat().toString().equals("CD") || item.getFormat().toString().equals("LP")) {
				format = item.getOwner() + ": '" + item.getTitle() + "' by " + item.getCreator() + " (" + item.getFormat() + ")";
			}else {
				format = item.getOwner() + ": " + item.getCreator() + ", '" + item.getTitle() + "'. (" + item.getFormat().toString() + ")";
			}
			report.add(format);
		}
		return report;
	}
	
	//checks if the formatting of Date (String) is valid
	private boolean checkDateFormat(String Date){
		boolean	result = false;
		//confirm correct format by position of '-' and string length in string
		if ((Date.charAt(4) == '-') && (Date.charAt(7) == '-') && (Date.length() == 10)) {
			result = true;
		}
		return result;
	}
	
	//checks if the formatting of cost (String) is valid
	private boolean checkCostFormat(String Cost){
		boolean result = false;
		//confirm correct format by position of '.' and '$' and string length in string
		if((Cost.charAt(0) == '$') && (Cost.charAt(Cost.length() - 3) == '.') && (Cost.length() >= 5)){
			result = true;
		}
		return result;
	}
	
}
