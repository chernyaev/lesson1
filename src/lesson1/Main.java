package lesson1;

import lesson1.HashTable.Entry;

public class Main {

	public static void main(String[] args) {
		HashTable<Integer, String> table = new HashTable<Integer, String>(10);

		table.addEntry(457,  "Moby Dick");
		table.addEntry(234,  "Moby Dick");
		table.addEntry(457,  "James Huges");
		table.printf();

	}

}