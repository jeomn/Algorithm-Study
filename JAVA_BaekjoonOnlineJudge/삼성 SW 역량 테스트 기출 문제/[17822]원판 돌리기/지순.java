import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_원판돌리기_17822 {
	private static ArrayList<Integer>[] list;
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    static class Point {
    	int r, c;
    	Point(int r, int c) {
    		this.r = r;
    		this.c = c;
    	}
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
        for (int i = 0; i < T; i++) {
        	st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
            rotate(x, d, k);
            if (!check()) change();
        }
        int ans=0;
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				ans+=list[i].get(j);
			}
		}
		System.out.println(ans);
    }

    private static void change() {
        int sum = 0, cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (list[i].get(j) != 0) {
                    cnt++;
                    sum += list[i].get(j);
                }
            }
        }
        if (cnt == 0) return;
        double avg = sum / (double) cnt;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (list[i].get(j) != 0) {
                    if (list[i].get(j) > avg) // 평균보다 큰수
                    	list[i].set(j, list[i].get(j)-1);
                    else if (list[i].get(j) < avg)
                    	list[i].set(j, list[i].get(j)+1);
                }
            }
        }
    }

    private static boolean check() {
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (list[i].get(j) != 0) {
                    if (bfs(i, j, list[i].get(j))) {
                    	list[i].set(j, 0);
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    private static boolean bfs(int i, int j, int k) {
        Queue<Point> q = new LinkedList<>();
        boolean flag = false;
        q.offer(new Point(i, j));

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int r = cur.r + dx[dir];
                int c = cur.c + dy[dir];
                if (c == -1)
                    c = M - 1;
                else if (c == M)
                    c = 0;
                if (1<=r && r<=N) {
                    if (list[r].get(c) == k) {
                        q.offer(new Point(r, c));
                        list[r].set(c, 0);
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }
    
    private static void rotate(int x, int d, int k) {
    	for(int i=x;i<=N;i+=x) {
			if(d == 0) {
				for(int j=0;j<k;j++) {
					list[i].add(0, list[i].remove(list[i].size()-1));
				}
			} else {
				for(int j=0;j<k;j++) {
					list[i].add(list[i].remove(0));
				}
			}
		}
    }

}
