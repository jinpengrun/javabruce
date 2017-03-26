package com.jd.www.book.algorithm.stackandqueue;


import java.util.Stack;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/5 上午10:49</li>
 * <li>function:</li>
 * </ul>
 */
public class GetMinStack<T extends Comparable<T>> {
    private Stack<T> stackData;
    private Stack<T> stackMin;

    public GetMinStack(){
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    //第一种省空间
    public void push(T t){
        if(this.stackMin.isEmpty()){
            stackMin.push(t);
        }else if(t.compareTo(this.getMin())<=0){
            this.stackMin.push(t);
        }
        stackData.push(t);
    }

    public T getMin(){
       if(this.stackData.isEmpty()){
           throw new RuntimeException("your stack is empty");
       }
        //弹出对象 但不从栈里删除
       return this.stackMin.peek();
    }

    public T pop(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("your stack is empty");
        }

        T t = stackData.pop();
        if(t.equals(getMin())){
            this.stackMin.pop();
        }

        return t;
    }


    public static class GetMinStacktwo<T extends Comparable<T>>{
        private  Stack<T> stackData;
        private  Stack<T> stackMin;

        public GetMinStacktwo(){
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }


        public void push(T t){
            stackData.push(t);

            if(stackMin.isEmpty()){
                stackMin.push(t);
            }else if(t.compareTo(getMin())<=0){
                stackMin.push(t);
            }else{
                stackMin.push(getMin());
            }


        }
        //弹出快 第二种 弹出快
        public T pop(){
            if(stackData.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }

            stackMin.pop();
            return stackData.pop();
        }


        public T getMin(){
            if(stackMin.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }

            return stackMin.peek();

        }
    }
}
