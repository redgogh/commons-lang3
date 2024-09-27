package com.redgogh.tools.cache;

import com.redgogh.tools.BasicConverter;
import com.redgogh.tools.StreamMapper;
import com.redgogh.tools.collection.Maps;
import com.redgogh.tools.time.Date;
import com.redgogh.tools.time.TimeUnitOperator;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.redgogh.tools.BasicConverter.any;
import static com.redgogh.tools.BasicConverter.anyeq;

public class JvmLocalCache implements LocalCache {

    private static final Map<String, CacheValue> objects = Maps.of();

    private static final Map<String, CacheValue> hashs = Maps.of();

    private static final Map<String, CacheValue> sets = Maps.of();

    private static final Map<String, CacheValue> lists = Maps.of();

    private final ScheduledExecutorService scheduledExecutorService;

    @Getter
    @Setter
    static class CacheValue {
        private String key;
        private Object value;
        private Date expireTime;

        public CacheValue(String key, Object value, long expire, TimeUnitOperator timeUnitOperator) {
            this.key = key;
            this.value = value;
            this.expireTime = expire != -1 ? timeUnitOperator.add((int) expire) : null;
        }

        public boolean isExpired(Date date) {
            if (expireTime == null)
                return false;
            return expireTime.lt(date);
        }
    }

    public JvmLocalCache() {
        scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                checkExpired(date);
            }
        }, 0, 16, TimeUnit.MINUTES);
    }

    public void checkExpired(Date date) {
        List<String> expiredKeys = objects.values().stream()
                .filter(v -> v.isExpired(date))
                .map(v -> v.key)
                .collect(Collectors.toList());
        expiredKeys.forEach(objects::remove);
    }

    @Override
    public void set(String key, Object value) {
        set(key, value, -1);
    }

    @Override
    public void set(String key, Object value, long expire) {
        set(key, value, expire, TimeUnitOperator.SECONDS);
    }

    @Override
    public void set(String key, Object value, long expire, TimeUnitOperator timeUnitOperator) {
        objects.put(key, new CacheValue(key, value, expire, timeUnitOperator));
    }

    @Override
    public void del(String key) {
        objects.remove(key);
    }

    @Override
    public <R> R get(String key) {
        return get(key, BasicConverter::any);
    }

    @Override
    public <R> R get(String key, StreamMapper<Object, R> mapper) {
        CacheValue cacheValue = objects.get(key);

        /* expired */
        if (cacheValue.isExpired(new Date())) {
            objects.remove(key);
            return null;
        }

        return mapper.call(objects.get(key).value);
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
    public <R> R hget(String key, String name) {
        return null;
    }

    @Override
    public <R> R hget(String key, String name, StreamMapper<Object, R> mapper) {
        return null;
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
    public void close() {
        scheduledExecutorService.shutdown();
    }

}
