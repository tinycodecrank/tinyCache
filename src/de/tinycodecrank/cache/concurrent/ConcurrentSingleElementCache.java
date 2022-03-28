package de.tinycodecrank.cache.concurrent;

import java.util.function.Consumer;
import java.util.function.Function;

import de.tinycodecrank.cache.CacheKey;
import de.tinycodecrank.cache.ICache;

public class ConcurrentSingleElementCache<Key, Value> implements ICache<Key, Value>
{
	private final Object				lock	= new Object();
	private CacheKey<Key>				key		= null;
	private Value						value	= null;
	private int							size	= 0;
	private final Function<Key, Value>	function;
	private final Consumer<Key>			evictionListener;
	
	public ConcurrentSingleElementCache(Function<Key, Value> function)
	{
		this(function, _key ->
		{});
	}
	
	public ConcurrentSingleElementCache(Function<Key, Value> function, Consumer<Key> evictionListener)
	{
		this.function			= function;
		this.evictionListener	= evictionListener;
	}
	
	@Override
	public Value get(Key key)
	{
		CacheKey<Key> cKey = new CacheKey<>(key);
		synchronized (lock)
		{
			if (contains(key))
			{
				return this.value;
			}
			else
			{
				if (this.size > 0)
				{
					evictionListener.accept(this.key.key);
				}
				this.value	= function.apply(key);
				this.key	= cKey;
				this.size	= 1;
				return this.value;
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
		synchronized (lock)
		{
			if (contains(key))
			{
				return this.value;
			}
			else
			{
				return null;
			}
		}
	}
	
	@Override
	public boolean contains(Key key)
	{
		return contains(new CacheKey<>(key));
	}
	
	private boolean contains(CacheKey<Key> key)
	{
		synchronized (lock)
		{
			return this.key.equals(key);
		}
	}
	
	@Override
	public void clear()
	{
		synchronized (lock)
		{
			this.size	= 0;
			this.key	= null;
			this.value	= null;
		}
	}
	
	@Override
	public int size()
	{
		return this.size;
	}
	
	@Override
	public void close()
	{
		clear();
	}
}