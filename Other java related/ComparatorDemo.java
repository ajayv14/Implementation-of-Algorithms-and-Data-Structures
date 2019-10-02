import java.util.*;

   class Fruit{
         String name;
         int cost;
         int id;
      
         public Fruit(String name, int cost, int id){
            this.name = name;
            this.cost = cost;
            this.id = id;
         }
         
         public String toString(){
            return "  name : "+ name + "  cost : " + cost + " id : " + id;  
         }  
         
         public int getPrice(){
            return this.cost;
         } 
      
      }

   class ComparatorDemo {
        
      public static void main(String[] args){
         
         List<Fruit> fruits = new ArrayList<>();
            fruits.add(new Fruit("mango", 10, 001)); 
            fruits.add(new Fruit("watermelan", 5, 002));
            fruits.add(new Fruit("pomo", 12, 003));
         
         //Anonymous inner class    
         Comparator<Fruit> com = new Comparator<Fruit>(){
            public int compare(Fruit f1, Fruit f2){
               if(f1.getPrice() > f2.getPrice()){
                  return 1;                   
               }
               else return -1;
            }            
          };   
         
         Collections.sort(fruits,com );
         
         // print the list elements
         for(Fruit fr : fruits){
            System.out.println(fr);
         }  
                   
      }            
 }