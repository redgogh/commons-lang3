package com.redgogh.tools.cache;

import com.redgogh.tools.StreamMapper;
import com.redgogh.tools.time.TimeUnitOperator;

import java.util.List;

/**
 * `LocalCache` 接口定义了多种本地缓存操作，包括基本的设置、获取、删除及列表操作。
 *
 * <p>本接口提供了丰富的缓存操作功能，支持单值、哈希、集合及列表结构的缓存，以满足
 * 不同场景下的需求。用户可以轻松地管理缓存项，包括设置过期时间和按需获取数据。
 *
 * <h2>注意事项</h2>
 * <ul>
 *     <li>确保键的唯一性，以避免意外覆盖。</li>
 *     <li>对于带过期时间的操作，需谨慎选择时间单位。</li>
 *     <li>获取方法支持通过映射器转换返回值，以增强灵活性。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 设置简单缓存
 *     cache.set("key1", "value1");
 *
 *     // 设置带过期时间的哈希缓存
 *     cache.hset("key2", "name1", "value2", 10, TimeUnitOperator.SECONDS);
 *
 *     // 从列表前端推入元素
 *     cache.pushstart("listKey", "element1");
 * </pre>
 *
 * @author RedGogh
 * @since 1.0
 */
public interface LocalCache {

    /**
     * #brief: 设置缓存值
     *
     * <p>将指定键的值存入缓存。如果已存在该键，则会覆盖原有值。
     *
     * @param key 缓存键
     * @param value 缓存值
     */
    void set(String key, Object value);

    /**
     * #brief: 设置带过期时间的缓存值
     *
     * <p>将指定键的值存入缓存，并设置过期时间。过期后，缓存项将被自动移除。
     *
     * @param key 缓存键
     * @param value 缓存值
     * @param expire 过期时间
     */
    void set(String key, Object value, long expire);

    /**
     * #brief: 设置带过期时间和时间单位的缓存值
     *
     * <p>将指定键的值存入缓存，并设置过期时间及其单位。过期后，缓存项将被自动移除。
     *
     * @param key 缓存键
     * @param value 缓存值
     * @param expire 过期时间
     * @param timeUnitOperator 过期时间单位
     */
    void set(String key, Object value, long expire, TimeUnitOperator timeUnitOperator);

    /**
     * #brief: 删除指定键的缓存项
     *
     * <p>根据键删除缓存项。如果该键不存在，则不做任何操作。
     *
     * @param key 缓存键
     */
    void del(String key);

    /**
     * #brief: 获取缓存值
     *
     * <p>根据键获取对应的缓存值。如果不存在该键，返回 `null`。
     *
     * @param key 缓存键
     * @param <R> 返回值类型
     * @return 对应的缓存值
     */
    <R> R get(String key);

    /**
     * #brief: 获取缓存值并应用映射器
     *
     * <p>根据键获取对应的缓存值，并通过映射器转换返回值。如果不存在该键，返回 `null`。
     *
     * @param key 缓存键
     * @param mapper 映射器
     * @param <R> 返回值类型
     * @return 映射后的缓存值
     */
    <R> R get(String key, StreamMapper<Object, R> mapper);

    /**
     * #brief: 设置哈希缓存值
     *
     * <p>将指定名称的值存入指定键的哈希缓存。如果哈希中已存在该名称的值，则会覆盖原有值。
     *
     * @param key 哈希缓存键
     * @param name 哈希中值的名称
     * @param value 哈希中值
     */
    void hset(String key, String name, Object value);

    /**
     * #brief: 设置带过期时间的哈希缓存值
     *
     * <p>将指定名称的值存入指定键的哈希缓存，并设置过期时间。过期后，哈希项将被自动移除。
     *
     * @param key 哈希缓存键
     * @param name 哈希中值的名称
     * @param value 哈希中值
     * @param expire 过期时间
     */
    void hset(String key, String name, Object value, long expire);

    /**
     * #brief: 设置带过期时间和时间单位的哈希缓存值
     *
     * <p>将指定名称的值存入指定键的哈希缓存，并设置过期时间及其单位。过期后，哈希项将被自动移除。
     *
     * @param key 哈希缓存键
     * @param name 哈希中值的名称
     * @param value 哈希中值
     * @param expire 过期时间
     * @param timeUnitOperator 过期时间单位
     */
    void hset(String key, String name, Object value, long expire, TimeUnitOperator timeUnitOperator);

    /**
     * #brief: 删除哈希缓存值
     *
     * <p>根据键和名称删除对应的哈希缓存项。如果该键或名称不存在，则不做任何操作。
     *
     * @param key 哈希缓存键
     * @param name 哈希中值的名称
     */
    void hdel(String key, String name);

    /**
     * #brief: 获取哈希缓存值
     *
     * <p>根据键和名称获取对应的哈希缓存值。如果不存在该键或名称，返回 `null`。
     *
     * @param key 哈希缓存键
     * @param name 哈希中值的名称
     * @param <R> 返回值类型
     * @return 对应的哈希缓存值
     */
    <R> R hget(String key, String name);

    /**
     * #brief: 获取哈希缓存值并应用映射器
     *
     * <p>根据键和名称获取对应的哈希缓存值，并通过映射器转换返回值。如果不存在该键或名称，返回 `null`。
     *
     * @param key 哈希缓存键
     * @param name 哈希中值的名称
     * @param mapper 映射器
     * @param <R> 返回值类型
     * @return 映射后的哈希缓存值
     */
    <R> R hget(String key, String name, StreamMapper<Object, R> mapper);

    /**
     * #brief: 从列表前端推入元素
     *
     * <p>将元素推入指定键的列表的前端。列表长度会自动增长。
     *
     * @param key 列表缓存键
     * @param value 列表元素
     */
    void pushstart(String key, Object value);

    /**
     * #brief: 从列表前端推入带过期时间的元素
     *
     * <p>将元素推入指定键的列表的前端，并设置过期时间。过期后，列表项将被自动移除。
     *
     * @param key 列表缓存键
     * @param value 列表元素
     * @param expire 过期时间
     */
    void pushstart(String key, Object value, long expire);

    /**
     * #brief: 从列表前端推入带过期时间和时间单位的元素
     *
     * <p>将元素推入指定键的列表的前端，并设置过期时间及其单位。过期后，列表项将被自动移除。
     *
     * @param key 列表缓存键
     * @param value 列表元素
     * @param expire 过期时间
     * @param timeUnitOperator 过期时间单位
     */
    void pushstart(String key, Object value, long expire, TimeUnitOperator timeUnitOperator);

    /**
     * #brief: 从列表后端推入元素
     *
     * <p>将元素推入指定键的列表的后端。列表长度会自动增长。
     *
     * @param key 列表缓存键
     * @param value 列表元素
     */
    void pushback(String key, Object value);

    /**
     * #brief: 从列表后端推入带过期时间的元素
     *
     * <p>将元素推入指定键的列表的后端，并设置过期时间。过期后，列表项将被自动移除。
     *
     * @param key 列表缓存键
     * @param value 列表元素
     * @param expire 过期时间
     */
    void pushback(String key, Object value, long expire);

    /**
     * #brief: 从列表后端推入带过期时间和时间单位的元素
     *
     * <p>将元素推入指定键的列表的后端，并设置过期时间及其单位。过期后，列表项将被自动移除。
     *
     * @param key 列表缓存键
     * @param value 列表元素
     * @param expire 过期时间
     * @param timeUnitOperator 过期时间单位
     */
    void pushback(String key, Object value, long expire, TimeUnitOperator timeUnitOperator);

    /**
     * #brief: 从列表前端弹出元素
     *
     * <p>从指定键的列表前端弹出元素。如果列表为空，返回 `null`。
     *
     * @param key 列表缓存键
     * @param <R> 返回值类型
     * @return 被弹出的元素
     */
    <R> R popstart(String key);

    /**
     * #brief: 从列表后端弹出元素
     *
     * <p>从指定键的列表后端弹出元素。如果列表为空，返回 `null`。
     *
     * @param key 列表缓存键
     * @param <R> 返回值类型
     * @return 被弹出的元素
     */
    <R> R popback(String key);

    /**
     * #brief: 获取列表指定索引的元素
     *
     * <p>根据键获取指定索引的列表元素。如果索引超出范围，返回 `null`。
     *
     * @param key 列表缓存键
     * @param index 元素索引
     * @param <R> 返回值类型
     * @return 指定索引的元素
     */
    <R> R lrange(String key, int index);

    /**
     * #brief: 获取列表指定范围的元素
     *
     * <p>根据键获取指定范围内的列表元素。如果范围超出，则返回实际存在的元素。
     *
     * @param key 列表缓存键
     * @param begin 开始索引
     * @param end 结束索引
     * @param <R> 返回值类型
     * @return 指定范围内的元素列表
     */
    <R> List<R> lrange(String key, int begin, int end);


    /**
     * #brief: 将元素添加到集合
     *
     * <p>将指定元素添加到集合中。如果集合中已存在该元素，则不做任何操作。
     *
     * @param key 集合缓存键
     * @param value 集合元素
     */
    void sadd(String key, Object value);

    /**
     * #brief: 将元素添加到带过期时间的集合
     *
     * <p>将指定元素添加到集合中，并设置过期时间。过期后，集合项将被自动移除。
     *
     * @param key 集合缓存键
     * @param value 集合元素
     * @param expire 过期时间
     */
    void sadd(String key, Object value, long expire);

    /**
     * #brief: 将元素添加到带过期时间和时间单位的集合
     *
     * <p>将指定元素添加到集合中，并设置过期时间及其单位。过期后，集合项将被自动移除。
     *
     * @param key 集合缓存键
     * @param value 集合元素
     * @param expire 过期时间
     * @param timeUnitOperator 过期时间单位
     */
    void sadd(String key, Object value, long expire, TimeUnitOperator timeUnitOperator);

    /**
     * #brief: 删除集合中的元素
     *
     * <p>根据键和元素值删除集合项。如果该键或元素不存在，则不做任何操作。
     *
     * @param key 集合缓存键
     * @param value 集合元素
     */
    void sdel(String key, Object value);

    /**
     * #brief: 获取集合中的元素
     *
     * <p>根据键获取对应的集合值。如果不存在该键，返回 `null`。
     *
     * @param key 集合缓存键
     * @param <R> 返回值类型
     * @return 对应的集合值
     */
    <R> R sget(String key);

    /**
     * #brief: 获取集合中的元素并应用映射器
     *
     * <p>根据键获取对应的集合值，并通过映射器转换返回值。如果不存在该键，返回 `null`。
     *
     * @param key 集合缓存键
     * @param mapper 映射器
     * @param <R> 返回值类型
     * @return 映射后的集合值
     */
    <R> R sget(String key, StreamMapper<Object, R> mapper);

    void close();

}

