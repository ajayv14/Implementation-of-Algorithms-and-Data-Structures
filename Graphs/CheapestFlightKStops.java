//credits : https://leetcode.com/submissions/detail/332981543/
// https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/605725/Fast-JAVA-Bellman-Ford-based-solution-with-explanation

import java.util.Arrays;

class CheapestFlightKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        /* bellman ford */

        int[] dist = new int[n]; // do not use flights.length
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i <= K; i++) {

            // for each run,
            int[] tempDist = Arrays.copyOf(dist, dist.length);

            for (int[] edge : flights) {

                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] == Integer.MAX_VALUE)
                    continue; // if not will end up comparing Math.min(Integer.MAX_VALUE, smaller value)

                tempDist[v] = Math.min(tempDist[v], dist[u] + w); // here dist[u] - previous round's relaxed value

            }
            dist = tempDist;
        }

        return (dist[dst] == Integer.MAX_VALUE) ? -1 : dist[dst];
    }
}