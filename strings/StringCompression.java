class StringCompresion {
    
    public int compress(char[] chars) {

       int i = 1; 

       int count = 1; 

       int ptr = 0; 

       while(i <= chars.length){
               
            if(i < chars.length &&  chars[i] == chars[i - 1]){
                count++;                
            }         

            else {

                chars[ptr++] = chars[i - 1]; //copy first ocurence of character to correct pos
                
                if(count > 1){

                    String cnt = String.valueOf(count);

                    for(char c : cnt.toCharArray()){

                        chars[ptr++] = c;        
                    }

                    // reset
                    count = 1;
                }             
                 
                
            }

            i++;   
        }

        return ptr;
        
    }
}