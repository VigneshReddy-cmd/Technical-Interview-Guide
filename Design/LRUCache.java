package Design;

import java.io.*;
import java.util.*;
class Node
{
   Node prev;
   int val;
   Node next;
  public Node(int val)
  {
     this.val=val;
     this.next=null;
     this.prev=null;
  }
}
class DLL
{
   private Node head;
   private Node tail;
   private Node dummy=new Node(-1);
   public DLL()
   {
     this.head=dummy;
     this.tail=dummy;
   }
   public void miss(Node node)
   {
      // add last
      node.prev=tail;
      tail.next=node;
      tail=node;
   }
   public void hit(Node node)
   {
     // remove node
      Node p=node.prev;
      Node n=node.next;
      if(p!=null)
      {
         p.next=n;
      }
      if(n!=null)
      {
         n.prev=p;
      }
     if(tail==node)
     {
        tail=p;
     }
      node.next=null;
      node.prev=null;
     // add last
      miss(node);
   }
   public int missAndFull(Node node)
   {
       Node currhead=head.next;  // head=dummy
       if(currhead!=null) // if exist the element i,e capacity is 0 case
       {
            head.next=currhead.next; // updating dummy head next
            if(currhead.next!=null)
            {
               currhead.next.prev=head;   // connecting currhead prev to dummy head
            }
            else
            {
               tail=head;   // if numm then it is tail then tail=head;
            }
       }
     miss(node);
     return currhead.val;
   }
  public void display()
  {
      Node tptr=head.next;
      while(tptr!=null)
      {
        System.out.print(tptr.val+" ");
        tptr=tptr.next;
      }
     System.out.println();
  }
}
class LRUCache
{
   private HashMap<Integer,Node> map;
   private DLL dll;
   private int capacity; 
   public LRUCache(int capacity)
   {
     this.capacity=capacity;
     this.map=new HashMap<>();
     this.dll=new DLL();
   }
   public void insert(int val)
   {
      // if hit
      if(map.containsKey(val))
      {
         Node hitNode=map.get(val);
         dll.hit(hitNode);
      }
      else if(map.size()==capacity)
      {
         // remove node from map and dll
        Node nn=new Node(val);
        int removedVal=dll.missAndFull(nn);
        map.remove(removedVal);
        map.put(val,nn);
      }
     else
     {
        // only miss 
        Node nn=new Node(val);
        dll.miss(nn);
        map.put(val,nn);
     }
   }
   public void display()
   {
     dll.display();
   }
}
class Solution {

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       int t=sc.nextInt();
       while(t-->0)
       {
          int n=sc.nextInt();
          int capacity=sc.nextInt();
          int[] arr=new int[n];
          for(int i=0;i<n;i++)
          {
             arr[i]=sc.nextInt();
          }
          LRUCache lru=new LRUCache(capacity);
          for(int i=0;i<n;i++)
          {
             lru.insert(arr[i]);
          }
          lru.display();
       }
      sc.close();
    }
}