package de.tinycodecrank.cache;

import java.util.Arrays;
import java.util.Objects;

public final class CacheKey<Key>
{
	public final Key	key;
	private final int	hashCode;
	
	public CacheKey(Key key)
	{
		this.key		= key;
		this.hashCode	= calcHashCode(key);
	}
	
	private static <Key> int calcHashCode(Key key)
	{
		Class<?> keyClass = key.getClass();
		if (keyClass.isArray())
		{
			String name = keyClass.getComponentType().getName();
			switch (name)
			{
				case "boolean":
					return Arrays.hashCode((boolean[]) key);
				case "byte":
					return Arrays.hashCode((byte[]) key);
				case "char":
					return Arrays.hashCode((char[]) key);
				case "double":
					return Arrays.hashCode((double[]) key);
				case "float":
					return Arrays.hashCode((float[]) key);
				case "int":
					return Arrays.hashCode((int[]) key);
				case "long":
					return Arrays.hashCode((long[]) key);
				case "short":
					return Arrays.hashCode((short[]) key);
				default:
					return Arrays.deepHashCode((Object[]) key);
			}
		}
		else
		{
			return Objects.hashCode(key);
		}
	}
	
	@Override
	public int hashCode()
	{
		return hashCode;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof CacheKey))
		{
			return false;
		}
		CacheKey<?>	other	= (CacheKey<?>) obj;
		boolean		equals	= Objects.deepEquals(key, other.key);
		return equals;
	}
}