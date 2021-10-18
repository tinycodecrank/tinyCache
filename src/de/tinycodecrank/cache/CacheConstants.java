package de.tinycodecrank.cache;

public final class CacheConstants
{
	private CacheConstants() throws IllegalAccessException
	{
		throw new IllegalAccessException();
	}
	
	@SuppressWarnings("rawtypes")
	public static final Class<? extends ICache>	DEFAULT_CACHE		= FIFOCache.class;
	public static final int						DEFAULT_CAPACITY	= 0xFF;
}