
while 1: #무한루프 0 0 0 들어오면 나가기
    a, b, c = sorted(list(map(int, input().split())))
    #먼저 정렬해서 가장 큰 수가 뒤로가게

    if a == 0 and b == 0 and c == 0: #0 0 0 탈출
        break

    elif a+b <= c:
        print("Invalid")

    elif a == b == c:
        print("Equilateral")

    elif (a == b) or (a == c) or (b == c):
        print("Isosceles")

    else:
        print("Scalene")