/**
 * Class that describes the operations of Sets with resizable arrays.
 * @author TaJah Reynolds
 *
 */
public class ResizableArraySet implements SetInterface {

	// array of book objects
	private Book[] array;
	// counter for the number of entries in the set
	private int size = 0;
	
	public ResizableArraySet() {
		this.array = new Book[10];
	}
	
	public ResizableArraySet(int size) {
		this.array = new Book[size];
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.getSize() == 0;
	}

	@Override
	public boolean add(Book newBook) {
		// No Duplicates
		if (this.contains(newBook))
			return false;
		
		if (size >= array.length) {
			array = this.copyUp();
		}
		
		array[size++] = newBook;
		return true;
	}
	
	private Book[] copyUp() {
		Book[] copy = new Book[this.array.length*2];
		for (int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}
		return copy;
	}

	@Override
	public boolean remove(Book aBook) {
		if (this.contains(aBook)) {
			array[index(aBook)] = array[--size];
			if (size <= array.length / 3)
				array = sizeDown();
			return true;
		}
		return false;
	}
	
	private Book[] sizeDown() {
		Book[] copy = new Book[this.array.length/2];
		for (int i = 0; i < size; i++) {
			copy[i] = this.array[i];
		}
		return copy;
	}
	
	private int index(Book aBook) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(aBook)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Book remove() {
		if (!this.isEmpty()) {
			Book rm = array[--size];
			array[size] = null;
			if (size <= array.length / 3) {
				array = sizeDown();
			}
			return rm;
		}
		return null;
	}

	@Override
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}

	@Override
	public boolean contains(Book aBook) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(aBook))
				return true;
		}
		return false;
	}

	@Override
	public SetInterface union(SetInterface anotherSet) {
		SetInterface ret = new ResizableArraySet();
		Book[] set = anotherSet.toArray();
		for (int i = 0; i < size; i++) {
			ret.add(array[i]);
		}
		for (int i = 0; i < set.length; i++) {
			ret.add(set[i]);
		}
		return ret;
	}

	@Override
	public SetInterface intersection(SetInterface anotherSet) {
		SetInterface ret = new ResizableArraySet();
		for (int i = 0; i < size; i++) {
			if (anotherSet.contains(array[i])) {
				ret.add(array[i]);
			}
		}
		return ret;
	}

	@Override
	public SetInterface difference(SetInterface anotherSet) {
		SetInterface ret = new ResizableArraySet();
		for (int i = 0; i < size; i++) {
			if (!anotherSet.contains(array[i])) {
				ret.add(array[i]);
			}
		}
		return ret;
	}

	@Override
	public Book[] toArray() {
		Book[] ret = new Book[size];
		for (int i = 0; i < size; i++) {
			ret[i] = array[i];
		}
		return ret;
	}

}
