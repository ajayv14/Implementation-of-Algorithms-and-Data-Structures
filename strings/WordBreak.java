
// LC : 139 : https://leetcode.com/problems/word-break/

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

    /**
        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: false

        Here, no combination of words exist in wordDict that can perfectly recreate the input string s.
        "cats" + "dog" will require "an" in wordDict in order to recreate s
        "cats" + "and" will require "og" in wordDict in order to recreate s
        "cat" + "sand" will require "og" in wordDict in order to recreate s
     */

class WordBreak {
    
    
    //Classic BFS - Non-optimized soln Time Limit Exceeded


/*
    c
    ca
    cat
    Present in dict : cat
    Added to q : 3
    cats
    Present in dict : cats
    Added to q : 4
    catsa
    catsan
    catsand
    catsando
    catsandog
    s
    sa
    san
    sand
    Present in dict : sand
    Added to q : 7
    sando
    sandog
    a
    an
    and
    Present in dict : and
    Added to q : 7
    ando
    andog
    o
    og
    o
    og */

    public boolean wordBreak(String s, List<String> wordDict) {

        int right = 0;
        int left = 0;
        // Contains left pointer 
        Queue<Integer> q = new LinkedList<>();
        
        q.add(left); // 0           

        while(!q.isEmpty()){

            left = q.remove();            
            
            // <= s.length() as substring(left, right) doesn't include char at right
            for(right = left + 1; right <= s.length(); right++){

                String sub = s.substring(left, right);

                System.out.println(sub);

                if(wordDict.contains(sub)){

                    if(right == s.length()) return true;

                    q.add(right);
                } 
            }           
        
        }

        return false;
        
    }
    
    
    
    
    
// Can further optimize by limiting j to a size of max len of word in dict.    
public boolean wordBreakDp(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1]; // Tracks if the word-substring between j and i is present in dict 
        
        dp[0] = true; // empty string
       
             
        // Each character in string input
        for(int i = 1; i <= s.length(); i++){
            
            for(int j = i - 1; j >= 0; j--){

                String sub = s.substring(j, i);

                System.out.println(sub);

                    if(dp[j] && wordDict.contains(sub)){
                        dp[i] = true;
                        break;
                    }    
            }
        }

        return dp[dp.length - 1];
        
    }

    /* c
a
ca
t
at
cat
s
ts
ats
cats
a
sa
tsa
atsa
catsa
n
an
san
tsan
atsan
catsan
d
nd
and
o
do
ndo
ando
sando
tsando
atsando
catsando
g
og
dog
ndog
andog
sandog
tsandog
atsandog
catsandog */
}