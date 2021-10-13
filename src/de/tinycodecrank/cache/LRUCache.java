package de.tinycodecrank.cache;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import de.tinycodecrank.collections.CyclicBuffer;

public class LRUCache<Key, Value> implements Cache<Key, Value>
{
	private final HashMap<Key, Value>	cache	= new HashMap<>();
	private final CyclicBuffer<Key>		evicionBuffer;
	private final Function<Key, Value>	function;
	
	public LRUCache(Function<Key, Value> function, int capacity)
	{
		this.function		= function;
		this.evicionBuffer	= new CyclicBuffer<>(capacity, cache::remove);
	}
	
	public LRUCache(Function<Key, Value> function, int capacity, Consumer<Key> evicionListener)
	{
		this.function		= function;
		this.evicionBuffer	= new CyclicBuffer<>(capacity, key ->
							{
								cache.remove(key);
								evicionListener.accept(key);
							});
	}
	
	@Override
	public Value get(Key key)
	{
		if (contains(key))
		{
			Value value = peak(key);
			evicionBuffer.moveToFront(key);
			return value;
		}
		else
		{
			Value value = this.function.apply(key);
			this.cache.put(key, value);
			evicionBuffer.push(key);
			return value;
		}
	}
	
	@Override
	public Value peak(Key key)
	{
		return cache.get(key);
	}
	
	@Override
	public boolean contains(Key key)
	{
		return cache.containsKey(key);
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