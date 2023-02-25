import sys

num = int(input())
S = set()

for i in range(num):
    temp = sys.stdin.readline().strip().split()

    if len(temp) == 1: #all or empty 일때
        if temp[0] == 'all':
            S = set([i for i in range(1, 21)]) #1~20까지
        else:
            S = set() #공집합
        continue

    command, x = temp[0], temp[1]
    x = int(x)

    if command == 'add':
        S.add(x)
    elif command == 'check':
        if x in S:
            print(1)
        else:
            print(0)
    elif command == 'remove':
        S.discard(x)
    elif command == 'toggle':
        if x in S: #S에 x가 있으면 삭제
            S.discard(x)
        else: #없으면 추가
            S.add(x)
