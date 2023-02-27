def solution(s):
    answer = 0
    word_dic = {'zero': '0', 'one': '1', 'two': '2', 'three': '3', 'four': '4', 'five': '5', 'six': '6', 'seven': '7',
                'eight': '8', 'nine': '9'}

    for k in word_dic:
        s = s.replace(k, word_dic[k])

    answer = int(s)

    return answer