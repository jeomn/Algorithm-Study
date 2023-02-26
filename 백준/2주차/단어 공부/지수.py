word = str(input()).upper()
#알파벳 받아오면서 대문자로 바꾸기 어차피 대소문자 신경쓰지 않고 갯수를 세고,
#나중에 출력도 대문자
word_dic = {}

for alpha in word: #딕셔너리에 각 알파벳 갯수 넣기
    if alpha in word_dic:
        word_dic[alpha] += 1
    else:
        word_dic[alpha] = 1

#max = max(word_dic, key = word_dic.get) #value값이 최대인 key값
#중복은 안됨. 최대값중 앞에 있는것 출력

maxs = [k for k, v in word_dic.items() if max(word_dic.values()) == v]
#중복을 모두 출력하려고 리스트 컴프리헨션 사용

if(len(maxs) == 1):
    print(*maxs) #*사용하면 대괄호없이 출력
else:
    print("?")
