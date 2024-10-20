package com.app.doublylinkedlist;

public class CircularDequeue {


    int size;
    DLLNode head;
    DLLNode tail;
    int maxSize;

    public CircularDequeue(int k) {
        this.size = 0;
        this.maxSize = k;
    }
    
    public boolean insertFront(int value) {

        if(isFull()) return false;

        DLLNode node = new DLLNode(value);

        if(head == null) head = tail = node;

        else {

            node.next = head;
            head.prev = node;
            head = node;                    
        }

        size++;

        return true;
        
    }
    
    public boolean insertLast(int value) {

        if(isFull()) return false;

        DLLNode node = new DLLNode(value);

        if(head == null) head = tail = node;

        // Redundant condition head == tail

        else {
            tail.next = node;
            node.prev = tail;
            tail = node;            
        }
         size++;    

         return true;    
    }
    
    public boolean deleteFront() {

        if(isEmpty()) return false;

        if(head == tail) head = tail = null;

        else {

            head = head.next;
            head.prev = null;
        }

        size--; 

        return true;

        
    }
    
    public boolean deleteLast() {

        if(isEmpty()) return false;

        if(head == tail) head = tail = null;

        else {
            tail = tail.prev;
            tail.next = null;
        }

        size--; 

        return true;
        
    }
    
    public int getFront() {
        
        if(isEmpty()) return -1;

        else return head.value;
    }
    
    public int getRear() {

        if(isEmpty()) return -1;

        else return tail.value;    
        
    }
    
    public boolean isEmpty() {
        return size == 0;             
    }
    
    public boolean isFull() {
        return size == maxSize;        
    }


    class DLLNode {

        int value;
        DLLNode next;
        DLLNode prev;

        public DLLNode(int value){            
            this.value = value;
        }
    }

}
