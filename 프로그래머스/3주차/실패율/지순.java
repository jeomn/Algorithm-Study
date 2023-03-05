import java.util.*;

class Solution {
    class Point implements Comparable<Point> {
        int stage;
        double failure;
        Point(int stage, double failure) {
            this.stage = stage;
            this.failure = failure;
        }

        @Override
        public int compareTo(Point p) {
            if (Double.compare(p.failure, this.failure) == 0) {
                return this.stage - p.stage;
            }
            return Double.compare(p.failure, this.failure);
        }
    }
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int cnt = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int users = stages.length;

        for(int i=1;i<=N;i++) {
            for(int s : stages) {
                if(s == i)
                    cnt++;
            }
            pq.add(new Point(i, cnt == 0 ? 0 : (double) cnt/users));
            users -= cnt;
            cnt = 0;
        }

        int idx = 0;
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            answer[idx++] = p.stage;
        }

        return answer;
    }
}