package lesson1;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Set;

public class HashTable<K, V> extends AbstractMap<K, V> {

	static final int DEFAULT_INITIAL_SIZE = 16;

	/*
	 * This array will contain Entry's objects. Each this object contain three
	 * atributes: key, value and hash-code.
	 */
	private Entry<K, V> hashTable[];

	public HashTable() {
		this(DEFAULT_INITIAL_SIZE);
	}

	public HashTable(int initialSize) {
		if (initialSize < 0)
			throw new IllegalArgumentException("Illegal initial size: "
					+ initialSize);

		hashTable = new Entry[initialSize];
	}

	/*
	 * Each object contain three atributes: K key - unique attribute, int hash -
	 * this attributes based on key, V value,
	 */
	public class Entry<K, V> {
		K key;
		V value;
		int hash;

		Entry(int h, K k, V v) {
			key = k;
			hash = h;
			value = v;
		}

		public final K getKey() {
			return key;
		}

		private final int getHash() {
			return hash;
		}

		public final V getValue() {
			return value;
		}

		public final String toString() {
			return getHash() + "=" + getKey();
		}

	}

	/*
	 * Index calculation based on hash-code and array's length, in which Entry's
	 * objects is storage
	 */
	private int hashIndex(int hashCode) {
		return (hashCode & (this.size() - 1));
	}

	/*
	 * Adding key is based on the open addressing. When a collision is used
	 * linear penetration whit step 1
	 */

	public boolean addEntry(K key, V value) {
		if (null != key) {
			int index = getIndexByHash(key);

			for (int i = index; i < size(); i++) {
				if (null != hashTable[i]
						&& hashTable[i].getKey().toString()
								.equals(key.toString())) {
					return false;
				}

			}

			while (true) {
				if (null == hashTable[index]) {
					putEntry(index, getHashByKey(key), key, value);
					return true;
				} else if (hashTable[index].getKey() == key) {
					return false;
				}
				index++;
				if (index >= size()) {
					putEntry(index, getHashByKey(key), key, value);
					return true;
				}
			}
		}
		return false;
	}

	private void putEntry(int index, int hashCode, K key, V value) {
		if (index >= size()) {
			resize();
		}
		hashTable[index] = new Entry(hashCode, key, value);
	}

	public int size() {
		return hashTable.length;
	}

	private void resize() {
		try {
			hashTable = Arrays.copyOf(hashTable, size() + 1);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean removeEntry(K Key) {
		int index = -1;
		if ((index = isContainsKey(Key)) != -1) {
			hashTable[index] = null;
			return true;
		} else {
			return false;
		}

	}

	public Entry<K, V> getEntry(K key) {
		if (null != key) {
			int index = -1;
			if ((index = isContainsKey(key)) != -1) {
				return hashTable[index];
			} else {
				return null;
			}
		}
		return null;
	}

	public int isContainsKey(K key) {
		if (null != key) {
			int index = getIndexByHash(key);
			while (true) {
				if (index < size()) {
					if (isEntryExist(getHashByKey(key), index, key)) {
						return index;
					}
				}
				index++;
				if (index >= size()) {
					return -1;
				}
			}
		}
		return -1;
	}

	private int getIndexByHash(K Key) {
		return hashIndex(getHashByKey(Key));
	}

	private int getHashByKey(K Key) {
		return Key.hashCode();
	}

	private boolean isEntryExist(int hashCode, int index, K key) {
		return (null != hashTable[index] && (hashTable[index].getHash() == hashCode && hashTable[index]
				.getKey().equals(key)));
	}

	public Entry<K, V>[] getTable() {
		return hashTable;
	}

	public void printf() {
		for (int i = 0; i < size(); i++) {
			if (null != hashTable[i]) {
				System.out.println(i + " hash = " + hashTable[i].getHash()
						+ " Key = " + hashTable[i].getKey() + " Value= "
						+ hashTable[i].getValue());
			}
		}
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
