import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_낚시왕_17143 {
	static int R, C;
	static ArrayList<Shark>[][] map;
	static ArrayList<Shark> list;
	static int result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	private static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.r, o.r);
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new ArrayList[R][C];

		list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = new ArrayList<Shark>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			list.add(new Shark(r, c, s, d, z));
		}
		result = 0;
		
		for (int i = 0; i < C; i++) {
			Collections.sort(list);
			fishing(i);
			move();
		}

		System.out.println(result);
	}

	private static void move() {
		ArrayList<Shark> temp = new ArrayList<>();
		for (Shark s : list) {
			int nx = s.r;
			int ny = s.c;
			int move = s.s;
			if (s.d == 0 || s.d == 1) {
				move %= (R - 1) * 2;
				while (move > 0) {
					if (nx == 0) {
						s.d = 1;
					}
					if (nx == R - 1) {
						s.d = 0;
					}
					nx += dx[s.d];
					move--;
				}
			} else if (s.d == 2 || s.d == 3) { 
				move %= (C - 1) * 2;
				while (move > 0) {
					if (ny == 0) {
						s.d = 2;
					}
					if (ny == C - 1) {
						s.d = 3;
					}
					ny += dy[s.d];
					move--;
				}
			}
			s.r = nx;
			s.c = ny;

			if(map[s.r][s.c].isEmpty()) {
				map[s.r][s.c].add(s);
			}
			else {
				if(map[s.r][s.c].get(0).z < s.z) {
					temp.add(map[s.r][s.c].remove(0));
					map[s.r][s.c].add(s);
				} else {
					temp.add(s);
				}
			}
		}
		for(Shark s: temp) {
			list.remove(s);
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j].clear();
			}
		}
	}

	private static void fishing(int location) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).c == location) {
				result += list.get(i).z;
				list.remove(i);
				return;
			}
		}
	}
}
