package com.epam.shop.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Cache that deletes the least recently used element
 */
public class LRUCache extends LinkedHashMap<String, Integer> {
    /**
     * Initial size
     */
    private int size;

    /**
     * Initial constructor
     *
     * @param size initial size
     */
    public LRUCache(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    /**
     * Puts new value to the container
     *
     * @param key   serial number of an element
     * @param value quantity of the gadget
     * @return value of the original put method
     */
    @Override
    public Integer put(String key, Integer value) {
        remove(key);
        return super.put(key, value);
    }

    /**
     * Method for removing eldest element
     *
     * @param eldest eldest entry
     * @return <tt>true</tt> if the eldest element was removed
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return size() > size;
    }

    /**
     * Returns string value of LRUCache
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        entrySet().stream().forEach((s) -> stringJoiner.add(s.getKey() + " " + s.getValue()));
        return stringJoiner.toString();
    }
}
