package com.djh.demo.lru;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双向链表实现
 */
public class LRUDemo1 {

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;
        AtomicInteger count;

        public Node(K key, V value, Node<K, V> prev) {
            this.count = new AtomicInteger();
            this.key = key;
            this.value = value;
            this.prev = prev;
        }

    }

    static class LRUPool<K, V> {
        Map<K, Node<K, V>> nodeMap;
        Node<K, V> head;
        Node<K, V> tail;
        int maxSize;


        public LRUPool(int maxSize) {
            this.nodeMap = new HashMap<K, Node<K, V>>();
            this.maxSize = maxSize;
        }

        public V put(K key, V value) {
            Node<K, V> node = nodeMap.get(key);
            if (node == null) {
                //新数据
                //判断是否大于maxSize
                int size = nodeMap.size();
                if (size >= maxSize) {
                    //--当前size大于maxSize，队尾淘汰一条数据
                    K keyToRemove = this.tail.key;
                    Node<K, V> removeNode = this.nodeMap.remove(keyToRemove);
                    this.tail = removeNode.prev;
                    this.tail.next = null;
                }
                //插入数据到队尾
                Node<K, V> newNode = new Node<K, V>(key, value, this.tail);
                this.tail.next = newNode;
                this.tail = newNode;//指向新队尾
                nodeMap.put(key, newNode);
            } else {
                node.value = value;
            }
            return value;
        }


        public V get(K key) {
            Node<K, V> node = nodeMap.get(key);
            if (node == null) {
                return null;
            }
            node.count.incrementAndGet();
            //调整元素位置到队头
            Node<K, V> oldHead = this.head;//旧队头节点
            this.head = node;//指向本次获取的节点
            if (node.next != null) {
                //本元素不是队尾的话 修改下一节点的指向
                node.next.prev = node.prev;
            }
            if (node.prev != null) {
                //本元素不是队头的话 修改上一节点指向
                node.prev.next = node.next;
            }

            //放置本元素到队头
            node.next = oldHead;
            node.prev = null;
            return node.value;
        }

    }


}
