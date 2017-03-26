package com.jd.www.book.algorithm.stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/5 下午2:05</li>
 * <li>function:</li>
 * </ul>
 *
 * 猫狗队列
 * 考察
 * 1 实现特殊数据结构以及对特殊功能的算法设计能力
 *
 * 常见错误
 * 1 cat队列放cat dog队列放dog 再用总队列存放所有实例
 *  错误原因 cat dog 以及总队列更新
 * 2 用hash表，key 表示一个cat实例或者一个dog实例，value表示这个队列的次序。
 *  错误原因 不能支持一个实例多次进队列的功能需求 因为hash表的key 只能对应一个value值
 *
 * 3 将用户原有cat 或 dog类重写，加一个计数项来表示一个实例队列的时间。
 * 错误原因 不能擅自
 *
 */
public class CatAndDog {

    public static class Pet{
        private String type;
        public Pet(String type){
            this.type = type;
        }

        public String getType(){
            return this.type;
        }


    }


    public static class Dog extends Pet{

        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet{

        public Cat() {
            super("cat");
        }
    }


    //实现一个新类
    public static class PetEnterQueue{
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet,long count){
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet(){
            return pet;
        }

        public long getCount(){
            return this.count;
        }

        public String getEnterPetType(){
            return this.pet.getType();
        }
    }

    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;

    public CatAndDog(){
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0 ;
    }

    public void add(Pet pet){
        if(pet.getType().equals("dog")){
            this.dogQ.add(new PetEnterQueue(pet,this.count++));
        }else if(pet.getType().equals("cat")){
            this.catQ.add(new PetEnterQueue(pet,this.count++));
        }else{
            throw new RuntimeException("err,not dog or cat");
        }
    }



    public Pet pollAll(){
        if(!this.dogQ.isEmpty()&&!this.catQ.isEmpty()){
            if(this.dogQ.peek().getCount() < this.catQ.peek().getCount()){
                return this.dogQ.poll().getPet();
            }else{
                return this.catQ.poll().getPet();
            }
        }else if(!this.catQ.isEmpty()){
            return this.catQ.poll().getPet();
        }else if(!this.dogQ.isEmpty()){
            return this.dogQ.poll().getPet();
        }else{
            throw new RuntimeException("error,queue is empty");
        }

    }


    public Cat PollCat(){
        if(!this.catQ.isEmpty()){
            return (Cat)this.catQ.poll().getPet();
        }
        throw new RuntimeException("error, cat queue is empty");
    }


    public Dog PollDog(){
        if(!this.dogQ.isEmpty()){
            return (Dog)this.dogQ.poll().getPet();
        }
        throw new RuntimeException("error, dog queue is empty");
    }






}
