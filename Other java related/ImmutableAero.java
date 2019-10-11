/*immutable class*/
  final class Aeroplane{
   
   final String name;
   final int serial;
   
   public Aeroplane(String name, int serial){
      this.name = name;
      this.serial = serial;
   }
   
   public String toString(){
      return "Name : "+ name + " Serial : " + serial;   
   }
   
  /* public void setName(String name){
      this.name = name;
   } 
   error: cannot assign a value to final variable name   
   */
   
   public static void main(String args[]){
      Aeroplane ap = new Aeroplane("B787",001);
      /* ap.name = "b737";
      error: cannot assign a value to final variable name 
      */
      ap.setName("B2"); 
      System.out.println(ap);         
   }     
}