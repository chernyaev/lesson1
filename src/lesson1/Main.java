package lesson1;

import lesson1.HashTable.Entry;

public class Main {

	public static void main(String[] args) {
		HashTable<Integer, String> table = new HashTable<Integer, String>(10);

		String[] initialValues = new String[] { "Moby Dick", "James Huges",
				"Alan Cooper", "Freddy Mercury", "yboM Dick" };

		table.add(initialValues);

		table.printf();

		table.remove("Moby Dick");

		Entry e = table.getEntry("James Huges");
		System.out.println(e.getValue());
		System.out.println(e.getHash());


		e = table.getEntry("Alan Cooper");
		System.out.println(e.getValue());
		System.out.println(e.getHash());
		
		table.printf();

	}

}