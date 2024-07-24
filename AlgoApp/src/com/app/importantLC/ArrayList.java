import java.util.Arrays;
class ArrayList {   
   
  
   Object[] arr;
   int size;
   int indexPointer = 0; // points to the last element always
   
   public ArrayList(int size){ 
      this.size = size;     
      arr = new Object[size];      
   }
   
   
   public void insert(int n){
      // check & update size of array if full      
      if(indexPointer == arr.length){
         expandArray();
      }  
      arr[indexPointer++] = n;              
   }
   
   
   public void delete(int index){
      if(index < arr.length){
         
         arr[index] = null;
         
         int tempPtr = index;
         // re arrange the array
         for(int i = index; i < arr.length; i++ ){
               arr[tempPtr] = arr[tempPtr+1];  
         }
         indexPointer--;
              
      }
   }   
   
   
   public void expandArray(){
     /**/
     // Object[] tempArr = new Object[arr.length * 2]; 
      arr = Arrays.copyOf(arr, arr.length * 2);     
   }
   
   
   public Object get(int index){
       
       if(index < indexPointer){
         return arr[index];
       }
       
       else throw new ArrayIndexOutOfBoundsException();  
         
   }
   
   public static void main(String[] args){
      ArrayList obj = new ArrayList(2);
      
      obj.insert(new Integer(5));
      obj.insert(new Integer(6));
      obj.insert(new Integer(6));
      obj.insert(new Integer(8));
      obj.insert(new Integer(12));
      
      obj.delete(4);
      obj.delete(3);
      obj.insert(new Integer(14));

         
      System.out.println(obj.arr.length);
      System.out.println(obj.get(3));
           
   }

}