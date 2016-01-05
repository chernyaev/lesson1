package lesson1;

public class Main{
	
	public static void main(String[] args) {
		HashTable table = new HashTable(20);
		
		String[] initialValues = new String[] { "Moby Dick", "James Huges",
				"Alan Cooper", "Freddy Mercury", "yboM Dick" };
		
		table.add(initialValues);
		table.remove("Moby Dick");
		table.find("yboM Dick");
		
	}

}