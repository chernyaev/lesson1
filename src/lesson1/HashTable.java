package lesson1;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Set;

public class HashTable<K, V> extends AbstractMap<K, V> {

	static final int DEFAULT_INITIAL_SIZE = 16;

	/*
	 * ������ ������ ����� ��������� ������� ���� Entry. ������ ����� ������
	 * �������� � ���� �������� � ���-��� ������� ��������.
	 */
	private Entry<V> hashTable[];

	HashTable() {
		this(DEFAULT_INITIAL_SIZE);
	}

	HashTable(int initialSize) {
		if (initialSize < 0)
			throw new IllegalArgumentException("Illegal initial size: "
					+ initialSize);

		hashTable = new Entry[initialSize];
	}

	/*
	 * ������ ���� Entry �������� ��� �������� ���� int � V: - int hash
	 * ������������� �� ��������� value, ������� ���� ������������ - V value -
	 * ��� �������� ����������� ��� �������� HashTable
	 */
	static class Entry<V> {
		V value;
		int hash;

		Entry(int h, V v) {
			value = v;
			hash = h;
		}

		public final V getValue() {
			return value;
		}

		public final int getHash() {
			return hash;
		}

		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		public final String toString() {
			return getHash() + "=" + getValue();
		}

	}

	public void add(V[] values) {
		for (V value : values) {
			this.add(value);
		}
	}

	/*
	 * ������ ������� ������������ �� ������ ���-���� � ������� �������, �
	 * ������� �������� ������� ���� Entry
	 */
	private int hashIndex(int hashCode) {
		return (hashCode & (this.size() - 1));
	}

	/*
	 * ���������� ����� ���������� �� ������ �������� ����������. ��� ��������
	 * ������������ �������� ���������� � ����� 1
	 */
	public void add(V value) {
		if (null != value) {
			int index = getIndexByHash(value);

			if (!put(index, getHashByValue(value), value)) {
				int position = index + 1;
				while (true) {
					if (put(position, getHashByValue(value), value)) {
						break;
					}
					position++;
				}
			}
		}
	}

	/*
	 * ���� ��� ���������� �������� � ������ �� ��������� ���������� �����,
	 * ������ ������� ������������� �� 1.
	 */
	private boolean put(int index, int hashCode, V value) {
		if (index >= size()) {
			resize();
		}
		if (null == hashTable[index]) {
			hashTable[index] = new Entry(hashCode, value);
			return true;
		}
		return false;
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

	public boolean remove(V value) {
		int index = -1;
		if ((index = isContains(value)) != -1) {
			hashTable[index] = null;
			return true;
		} else {
			return false;
		}

	}

	public int isContains(V value) {
		int index = getIndexByHash(value);
		while (true) {
			if (index < size()) {
				if (isEntryExist(getHashByValue(value), index, value)) {
					return index;
				}
			}
			index++;
			if (index >= size()) {
				return -1;
			}
		}
	}

	public Entry<V> getEntry(V value) {
		int index = -1;
		if ((index = isContains(value)) != -1) {
			return hashTable[index];
		} else {
			return null;
		}

	}

	private int getIndexByHash(V value) {
		return hashIndex(getHashByValue(value));
	}

	private int getHashByValue(V value) {
		return value.hashCode();
	}

	private boolean isEntryExist(int hashCode, int index, V value) {
		return (null != hashTable[index] && (hashTable[index].getHash() == hashCode && hashTable[index]
				.getValue().equals(value)));
	}

	public Entry<V>[] getTable() {
		return hashTable;
	}

	public void printf() {
		for (int i = 0; i < size(); i++) {
			if (null != hashTable[i]) {
				System.out.println(" hash = " + hashTable[i].getHash()
						+ " value = " + hashTable[i].getValue());
			}
		}
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
