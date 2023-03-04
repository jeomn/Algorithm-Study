N = int(input())

person = []

for i in range(N):
    x, y = map(int, input().split())
    person.append([x, y])

for i in range(N):
    count = 1
    for j in range(N):
        if (person[i][0] < person[j][0]) and (person[i][1] < person[j][1]):
            count += 1
    print(count, end = " ")

