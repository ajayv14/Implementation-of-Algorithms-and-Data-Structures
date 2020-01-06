/*please create a cleaner version of this code please !!*/

class MyHashMap {

    Node[] map;    
    
    /** Initialize your data structure here. */
    public MyHashMap() {
         map = new Node[10000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
    
        int k = (key % map.length);
        
        // check if already exists
        Node node = map[k];
        
        if(node != null){ // append            
            
            Node n = search(key,node);
            
            if(n != null) n.val = value; // if key exists
            
            else {
                
                Node newNode = new Node(key, value);
                 // add to front of node
                while(node.next != null){
                    node = node.next;                    
                }              
                node.next = newNode;             
            }                            
        }
        
        else {  // insert  
                               
            map[k] = new Node(key,value);        
        }       
        
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        
        int k = (key % map.length);
        
        int result = -1;  
         
        Node node = map[k];   
        
        if(node != null){
           
          Node n = search(key, node);
          
            if(n != null) result = n.val; 
            
           
        }         
        return result;        
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int k = (key % map.length);
        
        if(map[k] != null){
            
            Node n = search(key, map[k]);
            
            if(n != null){
                n.key = -1;
                n.val = -1;
            }
            
            
        }
    }       
   
    private Node search(int key, Node head){
                
        while(head != null){
            
            if(head.key == key) return head;
            
            head = head.next;            
        }        
        return null;        
    }  
}

class Node {
    
    int val;
    int key;
    Node next;
    
    public Node(int key, int val){
        this.val = val;         
        this.key = key;
    }    
    
}


/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */