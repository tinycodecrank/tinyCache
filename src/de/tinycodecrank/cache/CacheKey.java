package de.tinycodecrank.cache;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author tinycodecrank
 *
 * @param <Key>
 *            The type of the key to be used in the associated cache. This class
 *            is used to wrap key values, in order to allow for primitive array
 *            types as keys.
 */
public final class CacheKey<Key>
{
	public final Key	key;
	private final int	hashCode;
	
	/**
	 * The {@linkplain Object#hashCode()} of this element will be precalculated
	 * during creation.
	 * 
	 * @param key
	 *            The key to wrap.
	 */
	public CacheKey(Key key)
	{
		this.key		= key;
		this.hashCode	= calcHashCode(key);
	}
	
	/**
	 * @param <Key>
	 * @param key
	 *            The key to be used to calculate the hashCode for this object
	 * @return the hashCode for the supplied key
	 */
	private static <Key> int calcHashCode(Key key)
	{
		Class<?> keyClass = key.getClass();
		if (keyClass.isArray())
		{
			String name = keyClass.getComponentType().getName();
			return switch (name)
			{
				case "boolean" -> Arrays.hashCode((boolean[]) key);
				case "byte" -> Arrays.hashCode((byte[]) key);
				case "char" -> Arrays.hashCode((char[]) key);
				case "double" -> Arrays.hashCode((double[]) key);
				case "float" -> Arrays.hashCode((float[]) key);
				case "int" -> Arrays.hashCode((int[]) key);
				case "long" -> Arrays.hashCode((long[]) key);
				case "short" -> Arrays.hashCode((short[]) key);
				default -> Arrays.deepHashCode((Object[]) key);
			};
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