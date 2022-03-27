package de.tinycodecrank.cache;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class SingleElementCache<Key, Value> implements ICache<Key, Value>
{
	private CacheKey<Key>				key		= null;
	private Value						value	= null;
	private int							size	= 0;
	private final Function<Key, Value>	function;
	private final Consumer<Key>			evicionListener;
	
	public SingleElementCache(Function<Key, Value> function)
	{
		this(function, _key ->
		{});
	}
	
	public SingleElementCache(Function<Key, Value> function, Consumer<Key> evicionListener)
	{
		this.function			= function;
		this.evicionListener	= evicionListener;
	}
	
	@Override
	public Value get(Key key)
	{
		CacheKey<Key> cKey = new CacheKey<>(key);
		if (contains(key))
		{
			return this.value;
		}
		else
		{
			if (this.size > 0)
			{
				evicionListener.accept(this.key.key);
			}
			this.value	= function.apply(key);
			this.key	= cKey;
			this.size	= 1;
			return this.value;
		}
	}
	
	@Override
	public Value peak(Key key)
	{
		return peak(new CacheKey<>(key));
	}
	
	private Value peak(CacheKey<Key> key)
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
	
	@Override
	public boolean contains(Key key)
	{
		return contains(new CacheKey<>(key));
	}
	
	private boolean contains(CacheKey<Key> key)
	{
		return Objects.deepEquals(this.key, key);
	}
	
	@Override
	public void clear()
	{
		this.size	= 0;
		this.key	= null;
		this.value	= null;
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