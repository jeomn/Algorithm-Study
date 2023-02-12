import java.util.*;
class Point implements Comparable<Point>{
    int key;
    double value;
    Point(int key, double value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public int compareTo(Point p) {
        return Double.compare(p.value, this.value);
    }
}
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int users = stages.length;
        ArrayList<Point> list = new ArrayList<>();
        for(int i=1;i<=N;i++) {
            int cnt = 0;
            for(int j=0;j<stages.length;j++) {
                if(i == stages[j]) {
                    cnt++;
                }
            }
            if(cnt == 0) {
                list.add(new Point(i, 0));
                continue;
            }
            list.add(new Point(i, (double) cnt/users));
            users -= cnt;
        }

        Collections.sort(list);
        for(int i=0;i<N;i++) {
            answer[i] = list.get(i).key;
        }

        return answer;
    }
}
