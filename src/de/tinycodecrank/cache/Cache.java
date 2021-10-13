package de.tinycodecrank.cache;

public interface Cache<Key, Value> extends AutoCloseable
{
	/**
	 * @param key
	 * @return the associated value to the key from the cache or calculate it
	 *         otherwise
	 */
	public Value get(Key key);
	
	/**
	 * @param key
	 * @return the associated value to the key from the cache or null if not present
	 */
	public Value peak(Key key);
	
	/**
	 * @param key
	 * @return true if key is contained in this cache otherwise false
	 */
	public boolean contains(Key key);
	
	/**
	 * empties the cache
	 */
	public void clear();
	
	/**
	 * @return the amount of entries contained in this cache
	 */
	public int size();
	
	@Override
	public void close();
}