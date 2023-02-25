# num = int(input())
#
# if num%2 == 1:
#     print('SK')
# else:
#     print('CY')

num = int(input()) #돌 개수

t_count = [0 for i in range(num+1)] #초기화 최소 턴 개수

for i in range(1, num+1):
    t_count[i] = int(i/3) + int(i%3)

if t_count[num] % 2 == 1:
    print('SK')
else:
    print('CY')
