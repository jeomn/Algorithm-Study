# 괄호 변환(2022-01-19)
### 이지순
* **구현/문자열/재귀**
* 균형잡힌 괄호 문자열
  * 괄호별 카운트 해서 이 값이 같으면 균형잡힌 괄호가 된다.
* 올바른 괄호 문자열
  * Stack 사용
  * 열린괄호면 Stack에 삽입, 닫힌 괄호면 제거
  * 문자열 끝났는데 Stack의 크기가 0보다 크면 false
* 반복문 사용해 괄호 방향 뒤집기
---
### 정지민
* **구현/문자열**
* 문제에서 나타난 알고리즘대로 진행하면 된다.
* solution 함수
  * 입력받은 문자열이 올바른 문자열인 지, 빈 문자열인 지 확인하여 반환
    * 올바른 괄호 문자열 판별 방법
      * Stack을 사용
      * 열린 괄호면 Stack에 삽입, 닫힌 괄호이면 Stack에서 제거
      * 닫힌 괄호인데 Stack 데이터가 없으면 false
      * 문자열이 모두 끝났는데 열린 괄호가 남아있으면 false
      * 그 외에 모두 true
  * 올바른 문자열이 아니고, 비어있지 않은 문자열이면 분리되지 않는 균형잡힌 괄호 문자열(u)과, 나머지 문자열(v)로 분리
    * 균형잡힌 괄호 문자열 판별 방법
      * 열린 괄호('(')를 빈 문자로 바꾼 후, 길이 계산
      * 닫힌 괄호 길이가 원래 문자열 길이의 1/2라면, 열린 괄호의 길이와 닫힌 괄호의 길이가 같은 것 == 균형잡힌 괄호 문자열
  * u가 올바른 괄호 문자열이라면, 문자열 v를 solution 재귀
    * return 받은 문자열을 u에 이어붙여 반환
  * u가 올바르지 않은 괄호 문자열이라면, 문제에서 표현한 알고리즘 적용
