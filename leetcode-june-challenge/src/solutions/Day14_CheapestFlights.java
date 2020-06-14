package solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day14_CheapestFlights {

	https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/115541/JavaPython-Priority-Queue-Solution
	public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
		for (int[] f : flights) {
			if (!prices.containsKey(f[0]))
				prices.put(f[0], new HashMap<>());
			prices.get(f[0]).put(f[1], f[2]);
		}
		Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
		pq.add(new int[] { 0, src, k + 1 });
		while (!pq.isEmpty()) {
			int[] top = pq.remove();
			int price = top[0];
			int city = top[1];
			int stops = top[2];
			if (city == dst)
				return price;
			if (stops > 0) {
				Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
				for (int a : adj.keySet()) {
					pq.add(new int[] { price + adj.get(a), a, stops - 1 });
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int n = 3;
		int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 } };
		int src = 0, dst = 2, k = 1;
		System.out.println(findCheapestPrice(n, flights, src, dst, k));
	}

}
