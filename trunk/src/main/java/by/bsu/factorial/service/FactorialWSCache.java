package by.bsu.factorial.service;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.collections.map.MultiKeyMap;

import java.math.BigInteger;

/**
 * @author: Sidorovich Vladislav
 * Date: 27.10.11
 */
public final class FactorialWSCache {
    private static FactorialWSCache instance = new FactorialWSCache();

    public static FactorialWSCache getInstance() {
        return instance;
    }

    private final MultiKeyMap cache = MultiKeyMap.decorate(new LRUMap());

    private FactorialWSCache() {};

    public BigInteger get(Integer n, String ip) {
        return (BigInteger) cache.get(n, ip);
    }

    public boolean isCached(Integer n, String ip) {
        return cache.containsKey(n, ip);
    }

    public void storedInCache(Integer n, String ip, BigInteger factorial) {
        cache.put(n, ip, factorial);
    }

    public void removeCacheFor(Integer n, String ip) {
        if (cache.containsKey(n, ip)) {
            cache.remove(n, ip);
        }
    }

    public void dropCache() {
        cache.clear();
    }
}
