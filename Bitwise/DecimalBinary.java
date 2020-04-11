
class DecimalBinary{

   /*without bitwise and binary array in reverse*/
   public int[] decimalToBinary(int n){
   
     int[] binary = new int[32];  
      
      int i = 0;
         
      while(n > 0){
         binary[i] = n % 2;
         n = n/2;
         i++;     
      } 
     return binary;  
      
   }  
   
   /*  1 1 0 0 - 1 * 2 pow 3 + 2 * 2 pow 2 + 0 * 2 pow 1 + 0 * 2 pow 0*/
   
   public int binaryToDecimal(String binary){ // String coz int can only hold limited size of integers
      
      int base = 1; // signifies 2 ^ 0;
      int len = binary.length() - 1;
      int decimalNumber = 0;
      
      while(len >= 0){
         
         if(binary.charAt(len) == '1'){
            decimalNumber += base;            
         }
         base = 2 * base;
         len--;
         //ignore zeros            
      }
      return decimalNumber;   
   }
   
   
   public static void main(String[] args) {
      
      DecimalBinary obj = new DecimalBinary();
      
      int[] res = obj.decimalToBinary(15);
      
      //print it in reverse to get actual value
      for(int i = 0; i < res.length; i++){
          System.out.print(res[i]+"-");
      }
      
      System.out.println("");
      
      String binary = "1100";
      int dec = obj.binaryToDecimal(binary);
      System.out.println("decimal : "+dec);
   }
   

}