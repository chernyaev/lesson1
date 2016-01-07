package lesson1;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import lesson1.HashTable.Entry;

import org.junit.Test;

public class Tester {

	@Test
	public void testAdd() {

		HashTable<Integer, String> table = new HashTable<Integer, String>(10);

		String[] initialValues = new String[] { "Moby Dick", "James Huges",
				"Alan Cooper", "Freddy Mercury", "yboM Dick" };

		table.add(initialValues);
		table.remove("Moby Dick");

		Entry e = table.getEntry("James Huges");
		assertTrue("Error", e.getHash() == "James Huges".hashCode());

		e = table.getEntry("Alan Cooper");
		assertTrue("Error", e.getHash() == "Alan Cooper".hashCode());

		e = table.getEntry("Freddy Mercury");
		assertTrue("Error", e.getHash() == "Freddy Mercury".hashCode());

		table.remove("James Huges");
		table.remove("Alan Cooper");
		table.remove("Freddy Mercury");
		e = table.getEntry("James Huges");
		assertNull(e);

		table.add(new String[] { "<p>This", "implementation", "provides",
				"constant-time", "performance", "for", "the",
				"basic operations", "(<tt>get</tt>", "and", "<tt>put</tt>),",
				"assuming", "the", "hash", "function disperses", "the",
				"elements", "properly", "among", "the", "buckets.", "",
				"Iteration", "over collection", "views", "requires", "time",
				"proportional", "to", "the", "capacity", "of",
				"the <tt>HashMap</tt>", "instance", "(the", "number", "of",
				"buckets)", "plus", "its", "size", "(the", "number", "of",
				"key-value", "mappings).", "", "Thus,", "it's", "very",
				"important", "not", "to", "set", "the", "initial capacity",
				"too", "high", "(or", "the", "load", "factor", "too", "low)",
				"if", "iteration", "performance", "is important." });
		
		table.remove("");
		e = table.getEntry("");
		assertTrue("Error", e.getHash() == "".hashCode());

		table.remove("");
		e = table.getEntry("");
		assertNull(e);

		HashTable<Integer, Integer> secondTable = new HashTable<Integer, Integer>(
				10);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.add(1);
		secondTable.remove(1);
		e = secondTable.getEntry(1);
		assertTrue("Error", e.getHash() == new Integer(1).hashCode());

		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		secondTable.remove(1);
		e = secondTable.getEntry(1);
		assertNull(e);

	}
}