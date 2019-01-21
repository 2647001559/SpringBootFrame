package com.wt.wata.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 内存缓存工具类
 *  @author 添柴灬少年
 *  @Date    2018/12/25
 *  @version 1.0
 */
public class CacheUtil {
    private final static Map<String,Entity> map = new HashMap<>();
    //定时器线程池，用于清除过期缓存
    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    //缓存实体内部类
    private static class Entity {
        private Object value;   //键值对的value
        private Future future;  //定时器
        public Entity(Object value,Future future){
            this.value = value;
            this.future = future;
        }
        //获取值
        public Object getValue() {
            return value;
        }
        //获取Future 对象
        public Future getFuture() {
            return future;
        }
    }

    /**
     * 添加缓存
     * @param key       键
     * @param data      值
     */
    public synchronized static void put(String key,Object data){
        CacheUtil.put(key, data, 0);
    }

    /**
     * 添加缓存
     * @param key       键
     * @param data       值
     * @param expire    过期时间，单位：毫秒，0表示无限长
     */
    public synchronized static void put(String key,Object data,long expire){
        //清除缓存
        CacheUtil.remove(key);
        //设置过期时间
        if (expire > 0 ){
            Future future = executor.schedule(new Runnable() {
                @Override
                public void run() {
                    //过期后清除该键值对
                    synchronized (CacheUtil.class) {
                        map.remove(key);
                    }
                }
            },expire, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(data, future));
        } else {
            //不设置过期时间
            map.put(key, new Entity(data, null));
        }
    }

    /**
     * 读取缓存
     * @param key   键
     * @return
     */
    public synchronized static Object get(String key){
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    /**
     * 读取缓存
     * @param key   键
     * @return
     */
    public synchronized static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(CacheUtil.get(key));
    }

    /**
     * 清除缓存
     * @param key
     * @return
     */
    public synchronized static Object remove(String key) {
        //清除原缓存数据
        Entity entity = map.remove(key);
        if (entity == null) return null;
        //清除原键值对定时器
        Future future = entity.getFuture();
        if (future != null) future.cancel(true);
        return entity.getValue();
    }

    /**
     * 查询当前缓存的键值对数量
     * @return
     */
    public synchronized static int size() {
        return map.size();
    }
}
