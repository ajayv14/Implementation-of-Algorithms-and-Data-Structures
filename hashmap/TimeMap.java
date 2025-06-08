

// LC 981 : https://leetcode.com/problems/time-based-key-value-store

class TimeMap {

    // <key, map <timestamp, value>>
    Map<String, TreeMap<Integer,String>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {

        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);

    }
    

    // Alternate approach
    public String getNonOptimized(String key, int timestamp) {

        if(!map.containsKey(key)) return "";

        Map<Integer, String> subMap = map.get(key);

        String finalValue = "";
        int largestTimeStamp = 0;

        for(int tStamp : subMap.keySet()){

            if(tStamp >= largestTimeStamp && tStamp <= timestamp) finalValue = subMap.get(tStamp);
        } 

        return finalValue;

    }


     public String get(String key, int timestamp) {

        if(!map.containsKey(key)) return "";

        // Note : 
        /*
            Map<Integer, String> subMap = map.get(key);
            Integer floorKey = subMap.floorKey(timestamp);

            Doesn't work - Map interface in Java doesn't have a floorKey().
            This method is specific to the NavigableMap interface, which is implemented by classes like TreeMap.
         */
         
        TreeMap<Integer, String> subMap = map.get(key);

        Integer floorKey =  subMap.floorKey(timestamp);       

        if(floorKey != null) return subMap.get(floorKey);

        return "";
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */