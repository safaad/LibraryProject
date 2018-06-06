package BookStore;

public class Books {
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
		String s = title;
		return s += " :\n\t" + author + "\n\t" + ISBN + "\t" + targetsAge
				+ "+ .\n";
	}

}