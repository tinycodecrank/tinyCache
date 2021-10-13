package de.tinycodecrank.cache;

import java.util.HashMap;
import java.util.function.Function;

public final class ExpandingCache<Key, Value> implements Cache<Key, Value>, AutoCloseable
{
	private final HashMap<Key, Value>	cache	= new HashMap<>();
	private final Function<Key, Value>	function;
	
	public ExpandingCache(Function<Key, Value> function)
	{
		this.function = function;
	}
	
	@Override
	public Value get(Key key)
	{
		if (contains(key))
		{
			return peak(key);
		}
		else
		{
			Value value = this.function.apply(key);
			this.cache.put(key, value);
			return value;
		}
	}
	
	@Override
	public Value peak(Key key)
	{
		return this.cache.get(key);
	}
	
	@Override
	public boolean contains(Key key)
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