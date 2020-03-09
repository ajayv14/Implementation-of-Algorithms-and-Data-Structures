// credits:  Kevin Naughton Jr. -  https://www.youtube.com/watch?v=tRpusgdZxrE

class AddBinary {
    public String addBinary(String a, String b) {
        
        //to return result
        StringBuilder sb = new StringBuilder();
        
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        
        while(i >= 0 || j >= 0){
            
            int sum = carry; // each loop, begin with adding carry
            
            if(i >= 0){
                sum += a.charAt(i) - '0';  // convert char to int
                i--;         
            }
            
            if(j >= 0){
                sum += b.charAt(j) - '0';
                j--;
            }
            
            // append at beginning
            sb.insert(0,sum % 2);  // if 1 + 1 or 0 + 0, then sum = 0. if 1 + 0, sum = 1
            carry = sum / 2; // if sum is 1 + 0 or 0 + 0 carry is 0, if sum is 1 + 1, then carry is 1;            
        }      
        
        if(carry > 0) sb.insert(0,carry); // add the remaining carry, case when loop is complete
        
        return sb.toString();        
    }
    
    
    /*Test*/
    public static void main(String[] args){
      
      String a = "1111";
      String b = "1100"; // expected 11011
      AddBinary obj = new AddBinary();
      String res = obj.addBinary(a,b);
      System.out.println(res);
    }
    
}