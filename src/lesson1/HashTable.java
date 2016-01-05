package lesson1;

public class HashTable {

	private String ar[][] = new String[16][2];

	HashTable() {
		this(16);
	}

	HashTable(int initialSize) {
		if (initialSize < 0)
			throw new IllegalArgumentException("Illegal initial capacity: "
					+ initialSize);
		
		ar = new String[initialSize][2];
		for (int i = 0; i < initialSize; i++) {
			ar[i][0] = "-1";
		}
	}

	public void add(String[] initialValues) {
		for (String initValue : initialValues) {
			byte hash_code = hash(initValue);
			byte index = (byte) (hash_code & (ar.length - 1));
			if (ar[index][0].equals("-1")) {
				ar = set(index, hash_code, initValue, ar);
			} else {
				System.out.println("Collision. Position " + index
						+ " is not empty. Try to find new place");
				byte colPosition = (byte) (index + 1);
				while (true) {
					if (colPosition < ar.length) {
						if (ar[colPosition][0].equals("-1")) {
							ar = set(colPosition, hash_code, initValue, ar);
							break;
						}
					}
					colPosition++;
					if (colPosition > ar.length) {
						System.out.println("Not empty cell for this value");
						break;
					}
				}
			}
		}
	}

	public void remove(String value) {
		byte hash_code = hash(value);
		byte index = (byte) (hash_code & (ar.length - 1));
		while (true) {
			if (index < ar.length) {
				if (ar[index][0].equals(hash_code + "")) {
					System.out.println("Value " + value
							+ " removed from position " + index);
					ar[index][0] = "-1";
					ar[index][1] = "";
					break;
				}
			}
			index++;
			if (index > ar.length) {
				System.out.println("Value " + value
						+ " not fouded in hash-table. Can't remove value.");
				break;
			}
		}
	}

	public void find(String value) {
		byte hash_code = hash("Moby Dick");
		byte index = (byte) (hash_code & (ar.length - 1));
		while (true) {
			if (index < ar.length) {
				if (ar[index][0].equals(hash_code + "")) {
					System.out.println("Value " + value
							+ " founded in position " + index);
					break;
				} else {
					System.out.println("Value " + value
							+ " not founded in position " + index);
				}
			}
			index++;
			if (index > ar.length) {
				System.out.println("Value " + value
						+ " not fouded in hash-table. Searching stopped.");
				break;
			}
		}

	}

	private byte hash(String str) {
		byte bb[] = str.getBytes();
		byte hash_code = 1;
		for (byte b : bb) {
			hash_code *= b;
			if (hash_code == 0)
				hash_code++;
		}

		if (hash_code < 0)
			hash_code += 128;

		return hash_code;

	}

	private String[][] set(byte index, byte hash_code, String initValue,
			String ar[][]) {
		System.out.println("Position " + index + " is empty. Added "
				+ initValue + " to position " + index);
		ar[index][0] = hash_code + "";
		ar[index][1] = initValue;
		return ar;

	}

}
