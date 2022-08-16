# tinyCache
A module containing several cache implementations.

Class                        | Description
:--------------------------  | :----------
@Cache                       | this annotation is used to mark functions to be modified to use the specified cache ([ByteCode #TODO set Link]() manipulator to process this annotation).
ICache                       | The interface all supplied cache Classes implement.
ExpandingCache               | A cache implementation that expands without limits.
FIFOCache                    | A cache that evicts the elements when the capacity has been reached ([FIFO](https://en.wikipedia.org/wiki/Cache_replacement_policies#First_in_first_out_(FIFO))).
LRUCache                     | A cache that evicts the elements when the capacity has been reached ([LRU](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU))).
SingleElementCache           | A cache containing at most one element.
ConcurrentExpandingCache     | A synchronized version of ExpandingCache.
ConcurrentFIFOCache          | A synchronized version of FIFOCache.
ConcurrentLRUCache           | A synchronized version of LRUCache.
ConcurrentSingleElementCache | A synchromized version of SingleElementCache.


## Download
java version | library version | Download
:----------: | :-------------: | :-------
18+          | 1.0.0           | [**tinyCache.jar**](https://github.com/tinycodecrank/tinyCache/releases/download/v1.0.0/tinyCache.jar)

## Dependencies
* [**Monads.jar**](https://github.com/tinycodecrank/tinyMonads/releases/download/v1.0.0/Monads.jar)
* [**tinyIterators.jar**](https://github.com/tinycodecrank/tinyIterators/releases/download/v1.0.0/tinyIterators.jar)
* [**BetterFunctionals.jar**](https://github.com/tinycodecrank/betterFunctionals/releases/download/v1.0.0/BetterFunctionals.jar)
* [**MathUtils.jar**](https://github.com/tinycodecrank/mathUtils/releases/download/v1.0.0/MathUtils.jar)
* [**BoundedValues.jar**](https://github.com/tinycodecrank/BoundedValues/releases/download/v1.0.0/BoundedValues.jar)
* [**tinyCollections.jar**](https://github.com/tinycodecrank/tinyCollections/releases/download/v1.0.0/tinyCollections.jar)
