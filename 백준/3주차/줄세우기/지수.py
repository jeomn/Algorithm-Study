p = int(input())

for i in range(p):
    Class = list(map(int, input().split()))
    count = 0

    for j in range(1, len(Class)-1):
        for k in range(j+1, len(Class)):
            if Class[j] > Class[k]: #앞이 더 크면
                Class[j], Class[k] = Class[k], Class[j] #자리바꾸기
                count += 1
    print(Class[0], count)