import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())
map = [list(sys.stdin.readline()) for _ in range(n)]
check = [[[[False] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

rx, ry, bx,by = 0, 0, 0, 0
for i in range(n):
    for j in range(m):
        if map[i][j] == 'R':
            rx = i
            ry = j
        elif map[i][j] == 'B':
            bx = i
            by = j
q = deque()
q.append([rx, ry, bx, by, 0])
check[rx][ry][bx][by] = True

def bfs():
    while q:
        rx, ry, bx, by, cnt = q.popleft()
        if(cnt >= 10): break

        for i in range(4):
            nrx = rx
            nry = ry
            while True:
                nrx += dx[i]
                nry += dy[i]
                if map[nrx][nry] == '#':
                    nrx -= dx[i]
                    nry -= dy[i]
                    break
                if map[nrx][nry] == 'O':
                    break

            nbx = bx
            nby = by
            while True:
                nbx += dx[i]
                nby += dy[i]
                if map[nbx][nby] == '#':
                    nbx -= dx[i]
                    nby -= dy[i]
                    break
                if map[nbx][nby] == 'O':
                    break
            # print(nrx, nry, nbx, nby)
            if map[nbx][nby] == 'O': continue
            if map[nrx][nry] == 'O':
                print(cnt+1)
                return

            if nbx == nrx and nby == nry:
                if i==0:
                    if(rx>bx): nrx += 1
                    else: nbx +=1
                elif i==1:
                    if(ry>by): nby -= 1
                    else: nry -=1
                elif i==2:
                    if(rx>bx): nbx -= 1
                    else: nrx -= 1
                else:
                    if(ry>by): nry += 1
                    else: nby += 1

            if check[nrx][nry][nbx][nby] == False:
                q.append([nrx, nry, nbx, nby, cnt+1])
    print(-1)
bfs()
