package inventoryman;

abstract class Item { 
	private String _creator;
	private String _title;
	private String _releaseDateStr;
	private String _acquisitionDateStr;
	private String _owner;
	private String _costStr;
	private Format _format;
	
	public Item(String creator, String title, String releaseDateStr, String acquisitionDateStr, String owner,
			String costStr, String formatStr) {
		_creator = creator;
		_title = title;
		_releaseDateStr = releaseDateStr;
		_acquisitionDateStr = acquisitionDateStr;
		_owner = owner;
		_costStr = costStr;
		for(Format format: Format.values()) {//check for Format object corresponding to string
			if (format.toString().equals(formatStr)) {
				_format = format;
			}
		}
	}
	
	public String getCreator(){
		return _creator;
	}
	
	public String getTitle(){
		return _title;
	}
	
	public String getAcquisitionDate(){
		return _acquisitionDateStr;	
	}
	
	public String getOwner(){
		return _owner;
	}
	public String getCost(){
		return _costStr;
	}
	public Format getFormat() {
		return _format;
	}
	
	public String getReleaseDate() {
		return _releaseDateStr;
	}
		
	public enum Format {
		HARDCOVER("Hardcover"), 
		PAPERBACK("Paperback"), 
		CD("CD"), 
		LP("LP");
		
		private final String formatString;
		
		Format(String fStr) {
			formatString = fStr;
		}   
				
		public String toString(){
			return formatString;
		}
	}
}
