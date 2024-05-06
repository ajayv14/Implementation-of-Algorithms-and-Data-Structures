//credits: https://leetcode.com/problems/valid-palindrome/

class ValidPalindrome {
   
   
   public boolean isPalindrome(String s){
   
      int start = 0;
      int end = s.length() - 1;
      
      while(start < end){
         
         if(!Character.isLetterOrDigit(s.charAt(start))){
            start++;
         }
         
         if(!Character.isLetterOrDigit(s.charAt(end))){
            end--;
         }
         
         if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
            return false;
         }
      
         else{
            start++;
            end--;
         }
      }  
       return true;   
   }

   public static void main(String[] args){
	   
      String s = "Kayak";
      //String s = "Cloud";
      
      ValidPalindrome obj = new ValidPalindrome();
      boolean res = obj.isPalindrome(s);
		System.out.println(res);

   }
   
}