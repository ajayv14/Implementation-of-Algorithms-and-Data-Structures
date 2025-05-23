import java.util.LinkedList;
import java.util.List;

// Split path by '/'. Use stack to track directories. Skip empty and '.' entries. For '..' pop from stack if not empty. 
//Finally, build path from stack elements with '/' separator.

// LC - 71. Simplify Path
// https://leetcode.com/problems/simplify-path

public class SimplifyPath {

    public String simplifyPath(String path) {

        String[] str = path.split("/");

        List<String> components = new LinkedList<>();

        StringBuilder sb = new StringBuilder();    

        for(int i = 0; i < str.length; i++){

            String s = str[i];

            if(s.equals("") || s.equals(".") ){
                continue;
            }
            
            else if(s.equals("..")){

                if(components.size() > 0)  components.removeLast();                 

            }
    

            else {                  
                  components.add(s);
            }

                     
        }

        for(String component : components){
            sb.append("/");
            sb.append(component);
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }

}
