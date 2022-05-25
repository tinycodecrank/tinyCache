package de.tinycodecrank.cache;

import java.util.HashMap;
import java.util.function.Function;

/**
 * This cache has no size limit and expands without limitations.
 * 
 * @author tinycodecrank
 *
 * @param <Key>
 * @param <Value>
 */
public final class ExpandingCache<Key, Value> implements ICache<Key, Value>, AutoCloseable
{
	private final HashMap<CacheKey<Key>, Value>	cache	= new HashMap<>();
	private final Function<Key, Value>			function;
	
	public ExpandingCache(Function<Key, Value> function)
	{
		this.function = function;
	}
	
	@Override
	public Value get(Key key)
	{
		CacheKey<Key> cKey = new CacheKey<>(key);
		if (contains(cKey))
		{
			return peak(cKey);
		}
		else
		{
			Value value = this.function.apply(key);
			this.cache.put(cKey, value);
			return value;
		}
	}
	
	@Override
	public Value peak(Key key)
	{
		return peak(new CacheKey<>(key));
	}
	
	private Value peak(CacheKey<Key> key)
	{
		return this.cache.get(key);
	}
	
	@Override
	public boolean contains(Key key)
	{
		return contains(new CacheKey<>(key));
	}
	
	private boolean contains(CacheKey<Key> key)
	{
		return this.cache.containsKey(key);
	}
	
	@Override
	public void clear()
	{
		cache.clear();
	}
	
	@Override
	public int size()
	{
		return this.cache.size();
	}
	
	/**
	 * clears the cache
	 */
	@Override
	public void close()
	{
		clear();
	}
}