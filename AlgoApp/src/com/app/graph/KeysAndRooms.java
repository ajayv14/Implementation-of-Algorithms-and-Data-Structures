package com.app.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// LC 75
// Checkout both BFS and DFS solution
public class KeysAndRooms {

    // Classic DFS solution
    // Time : O(N + E) N -> Num of rooms (Nodes)  E -> total num of keys (Edges).
    // O(N) Space for N level stack in worst case.    
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

    // Classic BFS solution
    // Time : O(N + E) N -> Num of rooms (Nodes)  E -> total num of keys (Edges)
    // O(N) Space for Queue    
    public boolean canVisitAllRoomsBFS(List<List<Integer>> rooms) {

        Set<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited.add(0);

        while (!queue.isEmpty()) {

            int room = queue.remove();

            for (Integer key : rooms.get(room)) { // for all keys in room

                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.add(key);
                }
            }
        }
        return (visited.size() == rooms.size()) ? true : false;
    }




    public static void main(String[] args) {

        KeysAndRooms obj = new KeysAndRooms();

        List<List<Integer>> rooms1 = new ArrayList<List<Integer>>(
                Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList()));

        System.out.println("Input 1 : [[1],[2],[3],[]]");
        System.out.println("Expected : true");
        System.out.println("Actual : " + obj.canVisitAllRoomsDfs(rooms1));

        List<List<Integer>> rooms2 = new ArrayList<List<Integer>>(
                Arrays.asList(Arrays.asList(1, 3), Arrays.asList(3, 0, 1), Arrays.asList(2), Arrays.asList(0)));
        System.out.println("Input 2 : [1,3],[3,0,1],[2],[0]]");
        System.out.println("Expected : false");
        System.out.println("Actual : " + obj.canVisitAllRoomsDfs(rooms2));
    }

}
