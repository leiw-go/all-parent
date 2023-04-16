package cn.leiwspider;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public List<Integer> toStr(){
        List<Integer> list = new ArrayList<>();
        ListNode node = this;
        while(node != null){
            list.add(node.val);
            node = node.next;
        }
        return list;
    }
}
