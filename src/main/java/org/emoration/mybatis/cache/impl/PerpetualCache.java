package org.emoration.mybatis.cache.impl;

import org.emoration.mybatis.cache.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author czh
 * @Description 简单的Cache
 * @Date 2023/8/19
 */
public class PerpetualCache implements Cache {

    private final Map<Object, Object> cache = new HashMap<>();

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        Object o = cache.get(key);
        cache.remove(key);
        return o;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }
}
