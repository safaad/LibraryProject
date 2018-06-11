package BookStore;

import java.io.Serializable;

public class Books implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title, author;
	protected int ISBN;
	protected int targetsAge;

	Books(String title, String author, int isbn, int a) {
		this.title = title;
		this.author = author;
		this.ISBN = isbn;
		targetsAge = a;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getTarget() {
		return targetsAge;
	}

	public int getISBN() {
		return ISBN;
	}

	public String toString() {
		String s ="\t^^ " +title+" ^^";
		return s += " :\n\t  author : " + author + "\n\t  ISBN : " + ISBN + "\n\t  target age : " + targetsAge
				+ "+\n";
	}

}
