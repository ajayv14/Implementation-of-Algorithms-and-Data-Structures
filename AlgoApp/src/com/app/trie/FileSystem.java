package com.app.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//https://leetcode.com/problems/design-in-memory-file-system/
//588. Design In-Memory File System


// partially working soln without trie. Need to re-implement

public class FileSystem {

    Map<String, List<File>> map;

    public FileSystem() {
        map = new HashMap<>();
        map.put("/", new ArrayList<>());
    }
    
    public List<String> ls(String path) {       

        List<File> files = map.get(path);

        if(files != null)  return files.stream().map(f -> f.getName()).sorted().collect(Collectors.toList());    

        else return null;
    }
    
    /*private void printMap(){

        for(String s : map.keySet()){

            List<File> l = map.get(s);

            for(File f : l  ){

                System.out.println(f.getName());
            }

            
        }
    }*/

    public void mkdir(String path) {

        if(path == null) return;

        String[] dirs = path.split("/");
        
        String root = "/";     
        
        for (int i = 1; i < dirs.length; i++) {
        
            String cur = root.equals("/") ? root + dirs[i] : root + "/" + dirs[i];

            if (!map.containsKey(cur)) {
                map.put(cur, new ArrayList<>());
                map.get(root).add(new File(dirs[i], false));
            }

            root = cur; // Update root without adding an extra "/"
        }
        
    }
    
    public void addContentToFile(String filePath, String content) {     

        if(filePath == null || content == null) return;      

        if(!map.containsKey(filePath)){
            mkdir(filePath);
            List<File> files = map.get(filePath);
             files.add(new File(content, true));   
        }
        else {
            List<File> files = map.get(filePath);

        for(File f : files){
            if(f.isFile()){
                f.name += content;
            }
        }   

        }
        

       
         
        //printMap();
        
    }
    
    public String readContentFromFile(String filePath) {
       
        if(map.containsKey(filePath)){

            List<File> files = map.get(filePath);
            
            for(File f : files){
                if(f.isFile()) return f.getName();
            }
        }

        return null;        
    }



    class File {
        
        String name;
        boolean isFile;

        public File(String name, boolean isFile){

            this.name = name;
            this.isFile = isFile;
        }

        public String getName(){
            return this.name;
        }

        public boolean isFile() {
            return this.isFile;
        }
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */