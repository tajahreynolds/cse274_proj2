import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SetTester {

	@Test
	void testGetSize() {								// =====================================================
		SetInterface set = new ResizableArraySet(4);	// Creating empty set of size 4
		assertEquals(set.getSize(), 0); 				// Empty set size should return 0
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		for (int i = 0; i < 5; i++) {					// Adding 5 books to set
			set.add(books[i]);
			assertEquals(set.getSize(), i+1);			// Size should increase as each item is added
		}
		
		set.add(e);
		assertEquals(set.getSize(), 5);					// Size should stay the same if we add a duplicate

		for (int i = 4; i >= 0; i--) {					// Removing 5 books from set
			set.remove();
			assertEquals(set.getSize(), i);				// Size should decrease as each item is removed
		}

		set.remove();
		assertEquals(set.getSize(), 0);					// Size should stay at 0 if we try to remove with no items
	}

	@Test
	void testIsEmpty() {								// =====================================================
		SetInterface set = new ResizableArraySet(4);	// Creating empty set of size 4
		assertTrue(set.isEmpty());						// An empty set should return true
		
		// Creating book
		Book a = new Book("a", "author");
		
		set.add(a);										// Add one item to set
		assertFalse(set.isEmpty());						// Set is no longer empty, return false
		
		set.remove();									// Remove one item from set
		assertTrue(set.isEmpty());						// Set should be empty, return true
	}

	@Test
	void testAdd() {									// =====================================================
		SetInterface set = new ResizableArraySet(4);	// Creating empty set of size 4
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		for (int i = 0; i < 4; i++) {					// Adding 4 books to set (capacity 4)
			assertTrue(set.add(books[i]));				// Successful add 4 times
		}
		
		assertTrue(set.add(e));							// Adding 5th book should be true (increasing capacity)
	}

	@Test
	void testRemoveBook() {								// =====================================================
		SetInterface set = new ResizableArraySet(4);	// Creating empty set of size 4
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		assertFalse(set.remove(a));						// Removing book from empty set should return false
		
		for (int i = 0; i < 4; i++) {					// Adding 4 books to set
			set.add(books[i]);
		}
		
		for (int i = 0; i < 4; i++) {					// Remove each book specifically
			assertTrue(set.remove(books[i]));
		}
	}

	@Test
	void testRemove() {									// =====================================================
		SetInterface set = new ResizableArraySet(4);	// Creating empty set of size 4
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		assertEquals(set.remove(), null);				// Removing book from empty set should return null
		
		for (int i = 0; i < 5; i++) {					// Add 5 books to set
			set.add(books[i]);
		}

		for (int i = 0; i < 5; i++) {					// Remove 5 unspecified books from set
			assertEquals(set.remove(), books[4-i]);		// Unspecified book removed should be the last one
			assertEquals(set.getSize(), 4-i);			// Size should decrease as each book is removed
		}
	}

	@Test
	void testClear() {									// =====================================================
		SetInterface set = new ResizableArraySet();		// Creating empty set
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};		
		
		set.clear();
		assertEquals(set.getSize(), 0);					// Set should be empty after clearing
		
		set.add(a);										// Add 3 items to the set
		set.add(c);
		set.add(e);
		
		assertFalse(set.getSize() == 0);				// Set should not be empty
		set.clear();									// Clear the set
		assertTrue(set.getSize() == 0);					// Set should be empty
	}

	@Test
	void testContains() {								// =====================================================
		SetInterface set = new ResizableArraySet();		// Creating empty set
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		assertFalse(set.contains(a));					// Set should not contain any items
		set.add(a);										// Add a book to the set
		assertTrue(set.contains(a));					// Set should contain the added book
		set.remove();									// Remove the book from the set
		assertFalse(set.contains(a));					// Set should not contain any items
	}

	@Test
	void testUnion() {									// =====================================================
		SetInterface set = new ResizableArraySet();		// Creating empty set
		SetInterface other = new ResizableArraySet();	// Creating empty set
		assertEquals(set.union(other).getSize(), 0);	// Union of two empty sets should have 0 items
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		for (int i = 0; i < 5; i++) {
			if (i <= 2)
				set.add(books[i]);						// Add books a, b, and c to the set
			if (i >= 2)
				other.add(books[i]);					// Add books c, d, and e to the other set
		}
		
		SetInterface union = set.union(other);
		for (int i = 0; i < union.getSize(); i++) {
			assertTrue(union.contains(books[i]));		// All 5 books should be in the union of the two sets
		}
	}

	@Test
	void testIntersection() {							// =====================================================
		SetInterface set = new ResizableArraySet();		// Creating empty set
		SetInterface other = new ResizableArraySet();	// Creating empty set
		assertEquals(set.intersection(other).getSize(), 0);	// Intersection of two empty sets should have 0 items

		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		for (int i = 0; i < 5; i++) {
			set.add(books[i]);							// Add all 5 books to the set
			if(i == 1 || i == 3)
				other.add(books[i]);					// Add books b and d to the other set
		}
		
		assertTrue(set.intersection(other).toArray()[0].equals(b));	// Only books b and d should be in the set intersection
		assertTrue(set.intersection(other).toArray()[1].equals(d));
	}

	@Test
	void testDifference() {								// =====================================================
		SetInterface set = new ResizableArraySet();		// Creating empty set
		SetInterface other = new ResizableArraySet();	// Creating empty set
		assertEquals(set.union(other).getSize(), 0);	// Difference of two empty sets should have 0 items

		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		for (int i = 0; i < 5; i++) {
			set.add(books[i]);							// Add all 5 books to the set
			if(i == 1 || i == 3)
				other.add(books[i]);					// Add books b and d to the other set
		}
		
		assertTrue(set.difference(other).toArray()[0].equals(a));	// Only books a, c, and e should be in the set difference
		assertTrue(set.difference(other).toArray()[1].equals(c));
		assertTrue(set.difference(other).toArray()[2].equals(e));
	}

	@Test
	void testToArray() {								// =====================================================
		SetInterface set = new ResizableArraySet();		// Creating empty set
		
		// Creating array of 5 books
		Book a = new Book("a", "author");
		Book b = new Book("b", "author");
		Book c = new Book("c", "author");
		Book d = new Book("d", "author");
		Book e = new Book("e", "author");
		Book[] books = new Book[] {a, b, c, d, e};
		
		for (int i = 0; i < 5; i++) {
			set.add(books[i]);							// Add 5 books to the set
		}
		
		for (int i = 0; i < 5; i++) {
			assertEquals(set.toArray()[i], books[i]);	// Each item in the set should match each item in the array of books
		}
		
	}

}
