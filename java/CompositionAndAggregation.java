/*Aggregation vs Composition*/ 

/*Association : Association represents the relationship between the objects. Here, one object can be associated with one object or many objects. One to One,One to Many,Many to One and Many to Many */

/*Composition : represents a "Strong" relationship where one object contains other objects as a part of its state, where containing objects do not have an independent existence. 
                 Deleting the parent object will delete all child objects automatically.*/
                 
/*Aggregation : Also called "has-a"relationship, represents a "Weak" relationship where one object contains other objects as a part of its state and the containing objects have an independent existence */                 


/*Ingredient classes that are involved in making Chocolate*/
//1
class Milk {
   
   String name;
   
   public Milk(String name){
      this.name = name;
   }
}

//2
class Sugar {
   
   String name;
   
   public Sugar(String name){
      this.name = name;
   }
}

//3
class Cocoa {
   
   String name;
   
   public Cocoa(String name){
      this.name = name;
   }
}

/*Example of Composition property*/
class Chocolate {

   private Milk milk;
   private Sugar sugar;
   private Cocoa cocoa;
   
   public Chocolate(){
      milk = new Milk("almond milk"); // creating objects by composition where deleting Chocolate object will destroy milk, sugar and cocoa object
      sugar = new Sugar("unrefined sugar");
      cocoa = new Cocoa("dark 70%");
   }
   
   @Override
   public String toString(){
      return "Chocolate : ["+milk.name +","+sugar.name+","+cocoa.name+"]";
   }   
   
}

/*Example of Aggregation property */
class Chocolate2 {

   private Milk milk;
   private Sugar sugar;
   private Cocoa cocoa;
   
   /*Here the objects - milk, sugar and cocoa are created through aggregation, i.e before the constructor and passed to constructor, hence destroying the Chocolate object will have no effect on them */
   public Chocolate2(Milk milk, Sugar sugar, Cocoa cocoa){
      this.milk = milk;
      this.sugar = sugar;
      this.cocoa = cocoa;
   }   
   
   @Override
   public String toString(){
      return "Chocolate : ["+milk.name+","+sugar.name+","+cocoa.name+"]";
   }
}

/*Test*/
class CompositionAndAggregation {

   public static void main(String[] args){
      
      /*to test Composition property*/
      Chocolate obj = new Chocolate();
      System.out.println("Chocolate obj : "+obj.toString());
      
      // now destroying obj
      obj = null;
      System.out.println("Chocolate obj : " + obj); // There is no way to preserve milk, sugar and cocoa objects, hence the Chocolate class object and others are tightly coupled
      
      /*to test Aggregation property*/
      Milk m = new Milk("whole milk");
      Sugar s = new Sugar("honey");
      Cocoa c = new Cocoa("Dark 50%");
      
      Chocolate2 obj2 = new Chocolate2(m,s,c);
      System.out.println("Chocolate2 obj : "+obj2); 
      
      // now deleting the obj2
      obj2 = null;
      System.out.println("Chocolate2 obj : "+obj2);
      System.out.println(m); // you can see here that the objects are preserved even after Chocolate2 object(obj2) has been deleted, showing weak coupling
      System.out.println(s);
      System.out.println(c);
      
   }
}
