package com.jd.www.book.algorithm.stackandqueue;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/6 下午3:35</li>
 * <li>function:</li>
 * </ul>
 * 构造数组的MaxTree
 *
 * 一个数组的maxtree 定义如下
 * 数组必须没有重复元素
 * maxtree 是一颗二叉树，数组的每一个值对应一个二叉树节点
 * 包括maxtree树 在内的且在其中的一颗子树上，值最大的节点都是树的头
 *
 * 数组长度为N 时间复杂度为on  额外空间复杂度为on
 */
public class MaxTreeTest {
    //TODO
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }


}
