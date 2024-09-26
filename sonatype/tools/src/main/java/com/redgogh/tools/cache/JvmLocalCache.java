package com.redgogh.tools.cache;

import com.redgogh.tools.StreamMapper;
import com.redgogh.tools.collection.Maps;
import com.redgogh.tools.time.Date;
import com.redgogh.tools.time.TimeUnitOperator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JvmLocalCache implements LocalCache {

    private static final Map<String, CacheValue> objects = Maps.of();

    private static final Map<String, Map<String, CacheValue>> hashs = Maps.of();

    private static final Map<String, Set<CacheValue>> sets = Maps.of();

    private static final Map<String, List<CacheValue>> lists = Maps.of();

    static class CacheValue implements Comparable<CacheValue> {
        String key;
        Object value;
        Date expireTime;

        @Override
        public int compareTo(@NotNull CacheValue o) {
            return 0;
        }

    }

    @Override
    public void set(String key, Object value) {
        set(key, value, 7, TimeUnitOperator.DAYS);
    }

    @Override
    public void set(String key, Object value, long expire) {
        set(key, value, expire, TimeUnitOperator.SECONDS);
    }

    @Override
    public void set(String key, Object value, long expire, TimeUnitOperator timeUnitOperator) {

    }

    @Override
    public void del(String key) {
        objects.remove(key);
    }

    @Override
    public void hset(String key, String name, Object value) {

    }

    @Override
    public void hset(String key, String name, Object value, long expire) {

    }

    @Override
    public void hset(String key, String name, Object value, long expire, TimeUnitOperator timeUnitOperator) {

    }

    @Override
    public void hdel(String key, String name) {

    }

    @Override
    public void pushstart(String key, Object value) {

    }

    @Override
    public void pushstart(String key, Object value, long expire) {

    }

    @Override
    public void pushstart(String key, Object value, long expire, TimeUnitOperator timeUnitOperator) {

    }

    @Override
    public void pushback(String key, Object value) {

    }

    @Override
    public void pushback(String key, Object value, long expire) {

    }

    @Override
    public void pushback(String key, Object value, long expire, TimeUnitOperator timeUnitOperator) {

    }

    @Override
    public void sadd(String key, Object value) {

    }

    @Override
    public void sadd(String key, Object value, long expire) {

    }

    @Override
    public void sadd(String key, Object value, long expire, TimeUnitOperator timeUnitOperator) {

    }

    @Override
    public void sdel(String key, Object value) {

    }

    @Override
    public <R> R sget(String key) {
        return null;
    }

    @Override
    public <R> R sget(String key, StreamMapper<Object, R> mapper) {
        return null;
    }

    @Override
    public <R> R popstart(String key) {
        return null;
    }

    @Override
    public <R> R popback(String key) {
        return null;
    }

    @Override
    public <R> R lrange(String key, int index) {
        return null;
    }

    @Override
    public <R> List<R> lrange(String key, int begin, int end) {
        return null;
    }

    @Override
    public <R> R get(String key) {
        return null;
    }

    @Override
    public <R> R get(String key, StreamMapper<Object, R> mapper) {
        return null;
    }

    @Override
    public <R> R hget(String key, String name) {
        return null;
    }

    @Override
    public <R> R hget(String key, String name, StreamMapper<Object, R> mapper) {
        return null;
    }

}
