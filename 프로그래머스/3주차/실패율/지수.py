def solution(N, stages):
    answer = []
    length = len(stages)

    for i in range(N):
        if length != 0:
            fail = stages.count(i + 1) / length  # 실패율
        else:
            fail = 0
        length = length - stages.count(i + 1)
        answer.append((i, fail))

    answer.sort(key=lambda x: x[1], reverse=True)

    answer = [i[0] + 1 for i in answer]

    return answer