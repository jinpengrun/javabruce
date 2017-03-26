package com.jd.www.book.algorithm.link;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/6 下午4:31</li>
 * <li>function:</li>
 * </ul>
 *
 * 给定链表的头 找出两者 相同值
 *
 */
public class LinkCommonTest {
    public static void printCommon(Node head1,Node head2){
        System.out.print("common part: ");
        while(head1 != null && head2 != null){
            if(head1.value<head2.value){
                head1 = head1.next;
            }else if(head1.value>head2.value){
                head2 = head2.next;
            }else{
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }

        }

        System.out.println();
    }

    public static class Node{
        public int value;
        public Node next;
        private Node(int value,Node next){
            this.value = value;
            this.next = next;
        }

        public static Node newNode(int value,Node next){
            return new Node(value,next);
        }
    }

    public static void main(String[] args) {
        Node node2 = Node.newNode(22,null);
        Node node1 = Node.newNode(1,node2);

        Node node22 = Node.newNode(23,null);
        Node node11 = Node.newNode(1,node22);

        printCommon(node1,node11);

    }


}
