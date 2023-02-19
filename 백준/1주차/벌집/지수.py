
num = int(input())

room_last_num = 1 # 현재 마지막 방 번호 1, 7, 19, 37...
plus_num = 6 #각 원의 마지막 방의 차가 6의 배수이기 때문에 6씩 더해주려고
count = 1 #지나가는 방의 개수 처음시작은 1이어서 무조건 1개의 방은 지난다

while num > room_last_num: #입력값보다 마지막 방 번호가 더 크면 그 원에 속해있다는 것
    count = count+1
    room_last_num = room_last_num + plus_num #다음 원으로 바꿔주기
    plus_num = plus_num + 6 #6,12, 18, 24 .. 이렇게 늘어나기 때문에 6씩 더해주어야 함

print(count)

