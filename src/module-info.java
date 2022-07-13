module de.tinycodecrank.Cache
{
	exports de.tinycodecrank.cache;
	exports de.tinycodecrank.cache.concurrent;
	
	requires transitive de.tinycodecrank.Collections;
	requires de.tinycodecrank.Monads;
	requires transitive de.tinycodecrank.bounded;
}