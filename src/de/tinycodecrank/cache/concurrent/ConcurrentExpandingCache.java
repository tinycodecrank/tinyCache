package de.tinycodecrank.cache.concurrent;

import java.util.HashMap;
import java.util.function.Function;

import de.tinycodecrank.cache.CacheKey;
import de.tinycodecrank.cache.ICache;

public class ConcurrentExpandingCache<Key, Value> implements ICache<Key, Value>, AutoCloseable
{
	private final HashMap<CacheKey<Key>, Value>	cache	= new HashMap<>();
	private final Function<Key, Value>			function;
	
	public ConcurrentExpandingCache(Function<Key, Value> function)
	{
		this.function = function;
	}
	
	@Override
	public Value get(Key key)
	{
		CacheKey<Key> cKey = new CacheKey<>(key);
		synchronized (cache)
		{
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
	}
	
	@Override
	public Value peak(Key key)
	{
		return peak(new CacheKey<>(key));
	}
	
	private Value peak(CacheKey<Key> key)
	{
		synchronized (cache)
		{
			return this.cache.get(key);
		}
	}
	
	@Override
	public boolean contains(Key key)
	{
		return contains(new CacheKey<>(key));
	}
	
	private boolean contains(CacheKey<Key> key)
	{
		synchronized (cache)
		{
			return this.cache.containsKey(key);
		}
	}
	
	@Override
	public void clear()
	{
		synchronized (cache)
		{
			cache.clear();
		}
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