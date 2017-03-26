package com.jd.www.book.concurrent.atomic;

import com.jd.www.book.concurrent.lock.BoundedQueue;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/24 上午10:34</li>
 * <li>function:</li>
 * </ul>
 */
public class AtomicReferenceTest  {

    public static AtomicReference<User> atomicReference = new AtomicReference<>();


    public static void main(String[] args) {
        User user = new User("conan",15);
        atomicReference.set(user);

        User updateUser = new User("shinichi",17);

        atomicReference.compareAndSet(user,updateUser);

        System.out.println(atomicReference.get().getName()+" ==== "+atomicReference.get().getOld());
    }


    static class User{
        private String name;
        private int old;

        public User(String name,int old){
            this.name = name;
            this.old = old;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
