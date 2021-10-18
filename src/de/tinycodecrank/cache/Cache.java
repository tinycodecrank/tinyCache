package de.tinycodecrank.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Cache
{
	@SuppressWarnings("rawtypes")
	Class<? extends ICache> cache() default FIFOCache.class;
	
	int capacity() default CacheConstants.DEFAULT_CAPACITY;
}