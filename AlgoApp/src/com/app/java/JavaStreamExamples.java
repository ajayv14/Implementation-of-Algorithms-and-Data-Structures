import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class JavaStreamExamples {
   
   static boolean s;
   
   
   public void pp(){
   
         System.out.println(s);
   
   }
   
   
   public static void main(String[] args){
      
      JavaStreamExamples o = new JavaStreamExamples();
      o.pp(); 
      
      
      System.out.println(s);
      
      
      List<String> list1 = new ArrayList<>();
      list1.add("AMD");
      list1.add("Intel");
      list1.add("Nvidia");
      list1.add("Qualcomm");
      
      // forEach using Stream      
      list1.stream().forEach(e -> System.out.println(e));
      System.out.println(""); 
      
      //example2:
      list1.stream().forEach(e ->{
               System.out.println(e);
               
               if(e.equals("AMD")){
                  System.out.println("cheers");
               }      
      
      });
            
      System.out.println(""); 
      
      //example 3     
      list1.stream()
            .filter(e->e.contains("N"))
            .sorted()
            .forEach(System.out::println);
      System.out.println("");
      
      
      
      Map<Integer,String> map = new HashMap<>();
      map.put(1,"Nvidia");
      map.put(2,"AMD");
      map.put(2,"Qualcomm");
      map.put(3,"Intel");
      
            
      //forEach on Map
      map.forEach((k,v) -> System.out.println("key : " + k + " value : " + v));
         
      
   
   }






}