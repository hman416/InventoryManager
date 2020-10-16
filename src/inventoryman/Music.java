package inventoryman;

public class Music extends Item{
	
	public Music(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
			String costStr, String formatStr) {
		super(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr);
	}
	
	@Override
	public String toString() {//override toString to return description of music in desired format
		return "'" + getTitle() + "'" + " by " + getCreator() + ", " + getReleaseDate() + ". (" + getFormat().toString() + 
				", " + getOwner() + ", " + getAcquisitionDate() +", " + getCost() + ")"; 
	}
	 

}
