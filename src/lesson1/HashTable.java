package lesson1;

public class HashTable {

	private String ar[][] = new String[16][2];

	HashTable() {
		this(16);
	}

	HashTable(int initialSize) {
		if (initialSize < 0)
			throw new IllegalArgumentException("Illegal initial size: "
					+ initialSize);

		ar = new String[initialSize][2];
		for (int i = 0; i < initialSize; i++) {
			ar[i][0] = "-1";
		}
	}

	public void add(String[] values) {
		for (String value : values) {
			this.add(value);
		}
	}

	private int hashIndex(int hashCode) {
		return (hashCode & (this.size() - 1));
	}

	public void add(String value) {
		int hashCode = value.hashCode();
		int index = hashIndex(hashCode);

		if (!put(index, hashCode, value)) {
			System.out.println("Collision. Position " + index
					+ " is not empty. Try to find new place");
			int colPosition = index + 1;
			for(int pos = index + 1; pos < this.size(); pos++){
				if (put(colPosition, hashCode, value)) {
					break;
				}
			}
		}
	}

	private boolean put(int index, int hashCode, String value) {
		if (index >= ar.length) {
			System.out.println("Not empty cell for this value");
			return false;
		}
		if (ar[index][0].equals("-1")) {
			System.out.println("Position " + index + " is empty. Added "
					+ value + " to position " + index);
			ar[index][0] = hashCode + "";
			ar[index][1] = value;
			return true;
		}
		return false;
	}

	public int size() {
		return ar.length;
	}

	public void remove(String value) {
		int hashCode = value.hashCode();
		byte index = (byte) (hashCode & (ar.length - 1));
		while (true) {
			if (index < ar.length) {
				if (ar[index][0].equals(hashCode + "")) {
					System.out.println("Value " + value
							+ " removed from position " + index);
					ar[index][0] = "-1";
					ar[index][1] = "";
					break;
				}
			}
			index++;
			if (index >= ar.length) {
				System.out.println("Value " + value
						+ " not fouded in hash-table. Can't remove value.");
				break;
			}
		}
	}

	/*
	 * public void find(String value) { byte hashCode = hash("Moby Dick"); byte
	 * index = (byte) (hashCode & (ar.length - 1)); while (true) { if (index <
	 * ar.length) { if (ar[index][0].equals(hashCode + "")) {
	 * System.out.println("Value " + value + " founded in position " + index);
	 * break; } else { System.out.println("Value " + value +
	 * " not founded in position " + index); } } index++; if (index > ar.length)
	 * { System.out.println("Value " + value +
	 * " not fouded in hash-table. Searching stopped."); break; } }
	 * 
	 * }
	 */

}
