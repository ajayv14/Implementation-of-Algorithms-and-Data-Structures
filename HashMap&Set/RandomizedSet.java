/**  Logic :   
   * We use an hash map to keep track of <value, indexOfArray>. We also update the value in an array.
   * The array is needed to return a value based on random generated number.    
   * credits: https://www.youtube.com/watch?v=WtkwD7ikxfg
   *          https://leetcode.com/problems/insert-delete-getrandom-o1/     
**/

class RandomizedSet {

    Map<Integer,Integer> map;
    int[] arr;
    int index;
    Random random; 
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();  
        arr = new int[10000];
        index = -1;
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        
        if(!map.containsKey(val)){
          
            arr[++index] = val;
            map.put(val,index);
            return true;
        } else {
            return false;
        }
        
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        
        if(map.containsKey(val)){
            int indexOfVal = map.get(val);
            map.remove(val);
           
           /* remove the entry from the map, but to remove from the array,
            * Simply copy the last element of the array into current index and decrement the index to shrink the array.
            * Also update the new value,index mapping.                 
           */
 
           if(indexOfVal < index){   // if indexOfVal == index, it wud copy the same value, and update same entries on map
             arr[indexOfVal] = arr[index];
             map.put(arr[indexOfVal],indexOfVal);    
           }            
           
            index--;  // shrink the array
            return true;           
           
        } else return false;          
        
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        
        int result = -1;
         return arr[random.nextInt(map.size())];       
    }
}
