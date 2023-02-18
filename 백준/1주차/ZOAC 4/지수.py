import math

classroom = [list(map(int, input().split()))]

H = classroom[0][0]
W = classroom[0][1]
N = classroom[0][2]
M = classroom[0][3]

row = math.ceil(W/(M+1)) #올림
col = math.ceil(H/(N+1))

result = row*col
print(result)