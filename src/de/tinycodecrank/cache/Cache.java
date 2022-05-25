package de.tinycodecrank.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Cache
{
	/**
	 * @return The type of the cache implementation to be applied to the annotated
	 *         method.
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends ICache> cache() default FIFOCache.class;
	
	/**
	 * @return The caches capacity to be used if it can be specified
	 */
	int capacity() default CacheConstants.DEFAULT_CAPACITY;
}