import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.util.Iterator;


class QueueDemo{

   public static void main(String[] args){
      
      Queue<Integer> q = new LinkedList<>();
      
      // op1: Enqueue - adding elements to the queue
      q.add(5);
      q.add(6);
      
      System.out.println(q);
      
      //op 2 : dequeue - remove head of queue
      int n = q.remove();
      
      System.out.println("removed : " + n +", current queue : " + q);
      
      //op 3 : peek - to view the head element of the queue
      int p = q.peek();
      
      System.out.println("Head : " + p +", current queue : " + q);
      
      // op 4 : poll vs peek
            
      q.remove();
      System.out.println("current queue : " + q);
      System.out.println(q.peek());
      System.out.println(q.poll());
      
      // Generic methods in collection interface
      q.add(20);
      
      // size
      int size = q.size();
      System.out.println("size : " + q.size());
      
      //contains
      System.out.println("contains : " + q.contains(25));
      
      //clear
      q.clear();
      System.out.println("current queue : " + q);
      
      q.add(10);
      q.add(20);
      q.add(30);
      System.out.println("current queue : " + q);
      
      // Collections.addAll()
      
      boolean b = Collections.addAll(q, 40,50,60);
      System.out.println("current queue : " + q);
      
      //Iterator - hasNext(), next() remove()
      Iterator iterator = q.iterator();
         
         while(iterator.hasNext()){
            System.out.println("item : " + iterator.next() );
         }
         
      // toArray()         
       Object[] arr = q.toArray();  // cannot convert Object to int[]
       
      // stream      
      q.stream().filter(s -> s % 10 == 0).forEach(System.out::println); 
      
      System.out.println("---------------------------------------------");
      // parallel stream - use with care, is efficient under certain conditions only
      q.parallelStream().filter(s -> s > 40).forEach(System.out::println);     
      
      //hashcode
      System.out.println(q.hashCode());
                   
   }   

}