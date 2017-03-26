package com.jd.www.book.algorithm.binarytree;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/7 下午4:06</li>
 * <li>function:</li>
 * </ul>
 * 用递归和非递归方式实现二叉树的先序中序和后序遍历
 */
public class printBinaryTree {
    public static class Node{
        private int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    //先序
    public static void preOrderRecur(Node head){
        if(head==null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 打印中序
     * @param head
     */
    public static void inOrderRecur(Node head){
        if(head == null){
            return;
        }

        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }


    /**
     * 打印后序
     * @param args
     */

    public static void posOrderRecur(Node head){
        if(head == null){
            return;
        }

        inOrderRecur(head.left);
        inOrderRecur(head.right);
        System.out.print(head.value + " ");
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left=new Node(2);
        head.right=new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        System.out.println("-----------打印先序-------------");
        preOrderRecur(head);
        System.out.println("");
        System.out.println("--------------打印中序------------");
        inOrderRecur(head);
        System.out.println("");
        System.out.println("--------------打印后序------------");
        posOrderRecur(head);
    }

}
