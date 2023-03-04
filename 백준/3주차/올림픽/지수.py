N, K = map(int, input().split())
country = []

for i in range(N):
    country.append(list(map(int, input().split())))

country.sort(key=lambda x : (x[1], x[2], x[3]), reverse=True)
#금, 은, 동 순으로 내림차순으로 정렬 금이 같으면 은, 은이 같으면 동으로 순서대로 정렬

for i in range(N):
    if country[i][0] == K: #국가명이 K와 같으면
        index = i

for i in range(N): #K와 메달수가 일치하는 나라가 존재하면 +1해주기
    if country[index][1:] == country[i][1:]:
        print(i+1)
        break

