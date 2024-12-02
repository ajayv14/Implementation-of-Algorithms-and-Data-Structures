package com.app.strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC 737 :  https://leetcode.com/problems/sentence-similarity-ii/description/


class SentenceSimilarityII {
    
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        
        if(sentence1.length != sentence2.length) return false;

        UnionFind uf = new UnionFind();

        for(List<String> simPair : similarPairs){

            String p1 = simPair.get(0), p2 = simPair.get(1);

            uf.addWord(p1);
            uf.addWord(p2);

            uf.union(simPair.get(0), simPair.get(1));

        }

        System.out.println(uf.parent);

        for(int i = 0; i < sentence1.length; i++){

            String w1 = sentence1[i], w2 = sentence2[i];

            if(!w1.equals(w2)){
                
                if(!uf.parent.containsKey(w1) || !uf.parent.containsKey(w2) || (uf.find(w1) != uf.find(w2))) return false;
                
            }
        }

        return true;
    }


    // Union find based on Strings rather than integer
    class UnionFind {


        Map<String,String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();    
        

        public boolean union(String x, String y){

            String rootX = find(x), rootY = find(y);

            if(rootX == rootY) return false;

            if(rank.get(rootX) > rank.get(rootY)) parent.put(rootY,rootX);

            else if(rank.get(rootY) > rank.get(rootX)) parent.put(rootX,rootY);

            else if (rank.get(rootX) == rank.get(rootY)) {
                parent.put(rootX, rootY);
                rank.put(rootY, rank.get(rootY)+1);
            }

            return true;    
           
        } 

        public String find(String a){

            if(parent.get(a) != a) parent.put(a,find(parent.get(a)));

            return parent.get(a);
        }


        public void addWord(String a){

            parent.putIfAbsent(a,a);
            rank.putIfAbsent(a,0);
        }

    }
}
