public class WordBreak2 {



}


class WordBreak2Recursive {

    // Time 2 ^ n, space 2 ^ n
    Set<String> dict;

    public List<String> wordBreak(String s, List<String> wordDict) {

            dict = new HashSet<>(wordDict);

            List<String> results = new ArrayList<>();

            recurse(s, 0, new StringBuilder(), results);

            return results;
        
    }


    private void recurse(String s, int right, StringBuilder sb, List<String> results){

        // reached end of input string 
        if(right == s.length()){
            
            System.out.println("End of string reached, returning");
            results.add(sb.toString().trim());

            return;
        }

        for(int left = right + 1; left <= s.length(); left++){

            String sub = s.substring(right, left);

            System.out.println(sub);    

            if(dict.contains(sub)){

                System.out.println("Found in dict, calling recursion : " + sub); 
                
                // Backtracking - restore the original sentence after recursion, 
                // much like trimming list in permutaions, combi, subsets template
                
                int originalLen = sb.length();

                sb.append(sub).append(" ");
            
                recurse(s, left, sb, results);

                sb.setLength(originalLen);
            }   
        }  

         System.out.println("End of recurse method");  

    }
}






/*
Backtracking output 

c
ca
cat
Found in dict, calling recursion : cat
s
sa
san
sand
Found in dict, calling recursion : sand
d
do
dog
Found in dict, calling recursion : dog
End of string reached, returning
End of recurse method
sandd
sanddo
sanddog
End of recurse method
cats
Found in dict, calling recursion : cats
a
an
and
Found in dict, calling recursion : and
d
do
dog
Found in dict, calling recursion : dog
End of string reached, returning
End of recurse method
andd
anddo
anddog
End of recurse method
catsa
catsan
catsand
catsandd
catsanddo
catsanddog
End of recurse method
 * 
*/
