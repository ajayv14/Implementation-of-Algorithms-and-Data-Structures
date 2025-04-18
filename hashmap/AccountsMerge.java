package com.app.hashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC : https://leetcode.com/problems/accounts-merge/
// 721. Accounts Merge

public class AccountsMerge {

     public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // credits : ems/accounts-merge/solutions/1601980/java-solution-using-unionfind-beats-99-87-of-submissions/

        /*    
        ["John","johnsmith@mail.com","john_newyork@mail.com"],
        ["John","johnsmith@mail.com","john00@mail.com"],
        ["Mary","mary@mail.com"]
        ["John","johnnybravo@mail.com"]]



        Map root - "johnsmith@mail.com" - 0
                    "john_newyork@mail.com" - 1
                    "john00@mail.com" - 2
                    "mary@mail.com" : 3,
                    "johnnybravo@mail.com": 4
               uf parent [0,0,2,3,5]
        Map : parent index - list of emails 
        0 - "" "" ""
        2 - "" "" ""
        3 - "" "" ""
        */


        
        List<List<String>> merged = new ArrayList<>();

        UnionFind uf = new UnionFind(accounts.size());

        // Build emailId, index map to utilize union find to merge accounts
        // email,email id index
        Map<String,Integer> emailMap = new HashMap<>(); 

        for(int i = 0; i < accounts.size(); i++){

            List<String> emailIds = accounts.get(i);

            for(int j = 1 ; j < emailIds.size(); j++){

                    String email = emailIds.get(j);

                    if(emailMap.containsKey(email)){

                        uf.union(i, emailMap.get(email)); // Merge accounts
                    }
                    else emailMap.put(email, i);
            }
        }    


        Map<Integer, List<String>> idxToEmails = new HashMap<>();

        for(String key : emailMap.keySet()){

            int idxValue = emailMap.get(key); 

            int parentRootIndex = uf.find(idxValue); // Get parent idx of each unique email from union find


            // Populate map
            if(!idxToEmails.containsKey(parentRootIndex)){

                List<String> emailList = new ArrayList<>();
               // emailList.add(key);
                idxToEmails.put(parentRootIndex, emailList);
            }    

            idxToEmails.get(parentRootIndex).add(key);
        }

        
        // Prepare final results
        for(Integer idx : idxToEmails.keySet()){

            List<String> mergedEmails = idxToEmails.get(idx);

            Collections.sort(mergedEmails);

            mergedEmails.add(0, accounts.get(idx).get(0));

            merged.add(mergedEmails);   
                
        }


        return merged;
    }


    class UnionFind {

        int parent[];
        int rank[];    

        public UnionFind(int n){

            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public boolean union(int x, int y){

            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return false;

            else if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }

            else if(rank[rootY] > rank[rootX]){
                parent[rootX] = rootY;
            }

            else {
                parent[rootX] = rootY;                
                rank[rootY]++;
            }

            return true;
        }

        public int find(int x){
            
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }   

    }

}
