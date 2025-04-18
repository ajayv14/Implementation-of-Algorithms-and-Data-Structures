package com.app.strings;

public class ValidWordAbbreviation {

    //408 Valid Word Abreviation 
    // https://leetcode.com/problems/valid-word-abbreviation/description/

    // Time O(m + n) Space O(1)

    // Remember a word can contain a-z only.
    public boolean validWordAbbreviation(String word, String abbr) {

        if(abbr.length() > word.length()) return false;

        int wIndex = 0, abbrIndex = 0;

        while(abbrIndex < abbr.length() && wIndex < word.length()){

            char wC = word.charAt(wIndex);
            char wAbbr = abbr.charAt(abbrIndex);

            if(wC == wAbbr) {
                wIndex++;
                abbrIndex++;
            }

            else if(Character.isDigit(wAbbr)){


                if(wAbbr == '0') return false;

                String num = "";

                while(abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))){
                    num += abbr.charAt(abbrIndex);
                    abbrIndex++;
                }              

                wIndex += Integer.parseInt(num);
                                               
            }

            

            else return false;

        }

        return wIndex == word.length() && abbrIndex == abbr.length();      
       
        
    }

}
