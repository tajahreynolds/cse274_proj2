/**
 * This Book class gives objects properties such as titles and authors.
 * @author TaJah Reynolds
 *
 */
public class Book {

	private String title;
	private String author;
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString() {
		return this.title + " by " + this.author;
	}
	
	public boolean equals(Object anotherBook) {
		if (anotherBook instanceof Book) {
			Book other = (Book) anotherBook;
			if (this.title.equals(other.title) && this.author.equals(other.author)) {
				return true;
			}
		}
		return false;
	}
}
