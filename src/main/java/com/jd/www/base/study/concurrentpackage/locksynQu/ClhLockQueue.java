package com.jd.www.base.study.concurrentpackage.locksynQu;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhujinpeng on 16/2/24.
 * clh 就是一个fifo 队列，
 */
public class ClhLockQueue {

    static class ClhSpinLock{
        private final ThreadLocal<Node> prev;
        private final ThreadLocal<Node> node;
        private final AtomicReference<Node> tail = new AtomicReference<Node>(new Node());

        public ClhSpinLock() {
            this.node = new ThreadLocal<Node>() {
                protected Node initialValue() {
                    return new Node();
                }
            };

            this.prev = new ThreadLocal<Node>() {
                protected Node initialValue() {
                    return null;
                }
            };
        }


        public void lock() {
            final Node node = this.node.get();
            node.locked = true;
            // 一个CAS操作即可将当前线程对应的节点加入到队列中，
            // 并且同时获得了前继节点的引用，然后就是等待前继释放锁
            Node pred = this.tail.getAndSet(node);
            this.prev.set(pred);
            while (pred.locked) {// 进入自旋
            }
        }


        public void unlock() {
            final Node node = this.node.get();
            node.locked = false;
            this.node.set(this.prev.get());
        }





    }



    static class Node {
        private volatile boolean locked;
    }
}
