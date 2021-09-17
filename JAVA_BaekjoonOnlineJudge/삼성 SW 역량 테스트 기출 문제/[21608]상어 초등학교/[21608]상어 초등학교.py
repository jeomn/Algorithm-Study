import sys
from collections import defaultdict


input_func = sys.stdin.readline
if __name__ == "__main__":
    N = int(*map(int, input_func().split()))

    seat = [[0]*N for _ in range(N)]

    dx = [-1, 0, 0, 1]
    dy = [0, -1, 1, 0]
    student_dict = defaultdict(tuple)
    for _ in range(N*N):
        student, f1, f2, f3, f4 = map(int, input_func().split())
        student_dict[student] = (f1, f2, f3, f4)
        favorite = [f1, f2, f3, f4]

        maxScore = 0
        candidate = [[0] * N for _ in range(N)]
        for r in range(N):
            for c in range(N):
                if seat[r][c] != 0: continue
                current = seat[r][c]

                for idx in range(4):
                    nr = r+dx[idx]
                    nc = c+dy[idx]

                    if 0<=nr<N and 0<=nc<N:
                        if seat[nr][nc] == 0:
                            candidate[r][c]+=1
                        elif seat[nr][nc] in favorite:
                            candidate[r][c]+=100

                if maxScore < candidate[r][c]:
                    maxScore = candidate[r][c]

        flag = False
        for r in range(N):
            for c in range(N):
                if seat[r][c] != 0: continue
                if candidate[r][c] == maxScore:
                    seat[r][c] = student
                    flag = True
                    break
            if flag: break

    sum = 0
    for r in range(N):
        for c in range(N):
            student =  seat[r][c]
            favorite = student_dict[student]

            cnt = 0
            for idx in range(4):
                nr = r+dx[idx]
                nc = c+dy[idx]

                if 0<=nr<N and 0<=nc<N:
                    if seat[nr][nc] in favorite:
                        cnt+=1

            if cnt == 0: continue
            else: sum += 10**(cnt-1)

    print(sum)
