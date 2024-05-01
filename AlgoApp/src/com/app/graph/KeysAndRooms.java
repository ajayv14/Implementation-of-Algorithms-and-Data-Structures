package com.app.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeysAndRooms {

    // 

    // Classic DFS solution
    public boolean canVisitAllRoomsDfs(List<List<Integer>> rooms) {

        Set<Integer> visited = new HashSet<>();

        dfs(rooms, visited, 0);

        return (visited.size() == rooms.size()) ? true : false;
    }

    public void dfs(List<List<Integer>> rooms, Set visited, int room) {

        visited.add(room);

        for (int roomKey : rooms.get(room)) {

            if (!visited.contains(roomKey))
                dfs(rooms, visited, roomKey); // If room not visited and we have the key
        }
    }

    public static void main(String[] args) {
        
        KeysAndRooms obj = new KeysAndRooms();

        List<List<Integer>> rooms1 = new ArrayList<List<Integer>>(Arrays.asList(Arrays.asList(1),Arrays.asList(2), Arrays.asList(3),Arrays.asList()));

        System.out.println("Input 1 : [[1],[2],[3],[]]");
        System.out.println("Expected : true");
        System.out.println("Actual : " + obj.canVisitAllRoomsDfs(rooms1));

        List<List<Integer>> rooms2 = new ArrayList<List<Integer>>(Arrays.asList(Arrays.asList(1,3),Arrays.asList(3,0,1), Arrays.asList(2),Arrays.asList(0)));
        System.out.println("Input 2 : [1,3],[3,0,1],[2],[0]]");
        System.out.println("Expected : false");
        System.out.println("Actual : " + obj.canVisitAllRoomsDfs(rooms2));
    }

    
}
