def solution(n, arr1, arr2):
    answer = []
    total = []
    for i in range(n):
        total.append(bin(arr1[i] | arr2[i])[2:].zfill(n))

        total[i] = total[i].replace("1", "#")
        total[i] = total[i].replace("0", " ")

    answer = total

    return answer