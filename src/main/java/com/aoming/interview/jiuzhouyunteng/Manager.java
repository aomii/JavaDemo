package com.aoming.interview.jiuzhouyunteng;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Manager {
        //定义一个Map，用于存储用户和商品的关系
        private final Map<Long, Set<Long>> userProdectSet=new HashMap();
        //定义一个Map，用于存储商品和订阅数的关系
        private final Map<Long,Integer> productCounts=new HashMap();
        //定义一个锁，用于保证线程安全
        private final Lock lock=new ReentrantLock();

        //添加用户商品
        public void addUserProduct(Long userId,Long productId){
            //加锁
            lock.lock();
            try {
                //获取用户对应的商品集合
                Set<Long> productIds=userProdectSet.get(userId);
                //如果用户没有对应的商品集合，则新建一个
                if(productIds==null){
                    productIds=new HashSet();
                    userProdectSet.put(userId,productIds);
                }
                //将商品添加到用户对应的商品集合中
                productIds.add(productId);

                //获取商品对应的订阅数
                Integer count=productCounts.get(productId);
                //如果商品没有对应的订阅数，则初始化为0
                if(count==null){
                    count=0;
                }
                //将订阅数加1
                productCounts.put(productId,++count);
            }finally {
                //解锁
                lock.unlock();
            }
        }



        //根据商品id返回订阅数
        public int getProductCount(Long productId){
            //加锁
            lock.lock();
            try {
                //获取商品对应的订阅数
                Integer count=productCounts.get(productId);
                //如果商品没有对应的订阅数，则初始化为0
                if(count==null){
                    count=0;
                }
                //返回订阅数
                return count;
            }finally {
                //解锁
                lock.unlock();
            }

        }


        /**返回所有商品的订阅总数 */
        public int getAllProductSubCount(){
            //加锁
            lock.lock();
            try {
                //将所有商品的订阅数相加
                return productCounts.values().stream().mapToInt(Integer::intValue).sum();
            }finally {
                //解锁
                lock.unlock();
            }
        }

    }