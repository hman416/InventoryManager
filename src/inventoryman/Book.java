package inventoryman;

public class Book extends Item{
	
	private String _publisher;
	
	
	public Book(String author, String title, String publicationYear, String publisher, String acquisitionDateStr, String owner,
			String costStr, String formatStr) {
		
		super(author, title, publicationYear, acquisitionDateStr, owner, costStr, formatStr);
		_publisher = publisher;
	}
	
	public String getPublisher() {
		return _publisher;
	}
	
	public String getPublicationYear() {//cohesive with abstraction & does not break encapsulation
		return getReleaseDate();
	}
	
	@Override
	public String toString() {//override toString to return description of book in desired format
		return getCreator() + ", '" + getTitle() + "'. (" + getPublicationYear() + ", " + getPublisher() + "). [" 
				+ getFormat().toString() + ", " + getOwner() + ", " + getAcquisitionDate() +", " + getCost() + "]"; 
	}
	

}
