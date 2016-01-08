package lesson1;

import static org.junit.Assert.*;
import lesson1.HashTable.Entry;

import org.junit.Test;

public class Tester {

	@Test
	public void testAdd() {

		HashTable<Integer, String> table = new HashTable<Integer, String>(10);

		System.out.println("Test adding...");
		System.out.println("Add normal int keys");
		table.addEntry(457, "Moby Dick");
		table.addEntry(234, "Moby Dick");
		table.addEntry(457, "James Huges");
		table.addEntry(-1, "James Huges");
		table.addEntry(100000, "Liverpool");
		table.addEntry(1256 - 4, "Man United");

		Entry e = table.getEntry(457);
		assertTrue(e.getValue().equals("Moby Dick"));
		System.out.println("Test done");

		HashTable<String, String> tableStr = new HashTable<String, String>(10);
		System.out.println("Add normal String keys");
		tableStr.addEntry("457", "Moby Dick");
		tableStr.addEntry("234", "Moby Dick");
		tableStr.addEntry("457", "Wrong Side");
		tableStr.addEntry("456", "Of Heaven");
		tableStr.addEntry("456", "James Huges 123");
		tableStr.addEntry("456" + " ", "James Huges 123");

		e = tableStr.getEntry("457");
		assertTrue(e.getValue().equals("Moby Dick"));
		System.out.println("Test done");

		System.out.println("Add bad keys");
		table.addEntry(null, "Null Pointer");
		table.addEntry(Integer.getInteger("str"), "Integer from string");
		System.out.println("Test done");

		e = table.getEntry(null);
		assertNull(e);
		e = table.getEntry(Integer.getInteger("str"));
		assertNull(e);

		System.out.println("Test addding... done");
		System.out.println();
	}

	@Test
	public void testRemove() {
		HashTable<String, String> tableStr = new HashTable<String, String>(10);
		System.out.println("Test removing...");

		tableStr.addEntry("457", "Moby Dick");
		tableStr.addEntry("234", "Moby Dick");
		tableStr.addEntry("457", "Wrong Side");
		tableStr.addEntry("456", "Of Heaven");
		tableStr.addEntry("456", "James Huges 123");
		tableStr.addEntry("456" + " ", "James Huges with space");

		tableStr.removeEntry("456");
		tableStr.removeEntry("457");
		tableStr.removeEntry("456");

		Entry e = tableStr.getEntry("457");
		assertNull(e);
		System.out.println("Test done");
		e = tableStr.getEntry("456");
		assertNull(e);
		System.out.println("Test done");
		e = tableStr.getEntry("456 ");
		assertNotNull(e);
		System.out.println("Test done");

		tableStr.removeEntry(null);
		tableStr.removeEntry((new Integer(1)).toString());
		tableStr.removeEntry((new Integer(234)).toString());
		tableStr.removeEntry("str");

		System.out.println("Test removing...  done");
		System.out.println();

	}

	@Test
	public void testIsContain() {

		HashTable<String, String> tableStr = new HashTable<String, String>(10);
		System.out.println("Test IsContain...");
		tableStr.addEntry("457", "Moby Dick");
		tableStr.addEntry("234", "Moby Dick");
		tableStr.addEntry("457", "Wrong Side");
		tableStr.addEntry("456", "Of Heaven");
		tableStr.addEntry("456", "James Huges 123");
		tableStr.addEntry("456" + " ", "James Huges with space");

		tableStr.removeEntry("456");
		tableStr.removeEntry("457");
		tableStr.removeEntry("456");

		int res = tableStr.isContainsKey(null);
		assertTrue(res == -1);
		System.out.println("Test done");
		res = tableStr.isContainsKey("1");
		assertTrue(res == -1);
		System.out.println("Test done");
		res = tableStr.isContainsKey("456");
		assertTrue(res == -1);
		System.out.println("Test done");
		res = tableStr.isContainsKey("456 ");
		assertTrue(res != -1);
		System.out.println("Test done");

		System.out.println("Test IsContain... done");
		System.out.println();
	}

	@Test
	public void testGetEntry() {

		HashTable<String, String> tableStr = new HashTable<String, String>(10);
		System.out.println("Test getEntry...");
		tableStr.addEntry("457", "Moby Dick");
		tableStr.addEntry("234", "Moby Dick");
		tableStr.addEntry("457", "Wrong Side");
		tableStr.addEntry("456", "Of Heaven");
		tableStr.addEntry("456", "James Huges 123");
		tableStr.addEntry("456" + " ", "James Huges with space");

		Entry e = tableStr.getEntry("457");
		assertNotNull(e);
		System.out.println("Test done");
		assertTrue(e.getKey() == "457");
		System.out.println("Test done");
		assertTrue(e.getValue() == "Moby Dick");
		System.out.println("Test done");

		e = tableStr.getEntry("456");
		assertTrue(e.getKey() == "456");
		System.out.println("Test done");
		assertTrue(e.getValue() == "Of Heaven");
		System.out.println("Test done");

		e = tableStr.getEntry(null);
		assertNull(e);
		System.out.println("Test done");

		System.out.println("Test getEntry... done");
		System.out.println();
	}
}