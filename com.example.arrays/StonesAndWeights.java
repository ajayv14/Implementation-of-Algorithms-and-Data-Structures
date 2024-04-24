
//credits: https://leetcode.com/problems/last-stone-weight/
import java.util.PriorityQueue;

class StonesAndWeights {

    /*
     * logic : Basically at each attempt, we need to pick 2 heaviest stones and
     * smash them, store the remaining weight. So insert all the values in max heap.
     * pick two at each time till only one is left
     */

    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {

            int stone1 = pq.poll();
            int stone2 = pq.poll();

            if (stone1 != stone2)
                pq.offer(stone1 - stone2);
        }

        return (!pq.isEmpty()) ? pq.poll() : 0; // check if PQ is not empty and them pull out the last item

    }

    public static void main(String[] args) {

        int[] stones = { 2, 7, 4, 1, 8, 1 }; // op 1
        // int[] stones = {2,2}; //op 0

        StonesAndWeights obj = new StonesAndWeights();
        int res = obj.lastStoneWeight(stones);
        System.out.println(res);

    }

}