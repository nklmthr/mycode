package org.nklmthr.practivce.trees;

import java.util.HashMap;
import java.util.Map;

/*
 * http://collabedit.com/q62rc
 * 
 */
public class RetainBestCache<K, T extends Rankable> {

	/* Constructor with a data source (assumed to be slow) and a cache size */

	Map<K, T> cache = new HashMap<K, T>();
	int entriesToRetain = 0;
	DataSource<K, T> ds = null;

	public RetainBestCache(DataSource<K, T> ds, int entriesToRetain) {
		this.entriesToRetain = entriesToRetain;
		this.ds = ds;
	}

	/*
	 * Gets some data. If possible, retrieves it from cache to be fast. If the
	 * data is not cached, retrieves it from the data source. If the cache is
	 * full, attempts to cache the returned data, evicting the T with lowest
	 * rank among the ones that it has available If there is a tie, the cache
	 * may choose any T with lowest rank to evict.
	 */
	public T get(K key) throws Exception {
		if (key == null)
			throw new Exception();
		if (!cache.containsKey(key)) {
			T value = ds.get(key);
			if (cache.size() < entriesToRetain) {
				cache.put(key, value);
				addKeyToMinHeap(key);
				return cache.get(key);
			} else {
				K keyToRemove = findKeyWithMinimumRank();
				if (key.equals(keyToRemove)) {
					return cache.get(key);
				} else {
					cache.remove(keyToRemove);
					cache.put(key, value);
					addKeyToMinHeap(key);
					return cache.get(key);
				}
			}

		} else {
			return cache.get(key);
		}
	}

	private K findKeyWithMinimumRank() {
		// TODO Auto-generated method stub
		return null;
	}

	private void addKeyToMinHeap(K key) {
		// TODO Auto-generated method stub
		
	}
}

/*
 * For reference, here are the Rankable and DataSource interfaces. You do not
 * need to implement them, and should not make assumptions about their
 * implementations.
 */

interface Rankable {
	/**
	 * Returns the Rank of this object, using some algorithm and potentially the
	 * internal state of the Rankable.
	 */
	long getRank();
}

interface DataSource<K, T extends Rankable> {
	T get(K key);
}