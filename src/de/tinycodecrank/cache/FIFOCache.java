package de.tinycodecrank.cache;

import java.util.HashMap;
import java.util.function.Function;

import de.tinycodecrank.collections.CyclicBuffer;

public class FIFOCache<Key, Value> implements ICache<Key, Value>
{
	private final HashMap<CacheKey<Key>, Value>	cache	= new HashMap<>();
	private final CyclicBuffer<CacheKey<Key>>	evicionBuffer;
	private final Function<Key, Value>			function;
	
	public FIFOCache(Function<Key, Value> function, int capacity)
	{
		this.function		= function;
		this.evicionBuffer	= new CyclicBuffer<>(capacity, this.cache::remove);
	}
	
	@Override
	public Value get(Key key)
	{
		CacheKey<Key> cKey = new CacheKey<>(key);
		if (contains(cKey))
		{
			Value value = peak(cKey);
			return value;
		}
		else
		{
			Value value = this.function.apply(key);
			this.cache.put(cKey, value);
			evicionBuffer.push(cKey);
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
		evicionBuffer.clear();
	}
	
	@Override
	public int size()
	{
		return evicionBuffer.size();
	}
	
	@Override
	public void close()
	{
		clear();
	}
}