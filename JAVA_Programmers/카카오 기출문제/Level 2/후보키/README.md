# 후보키(2022-01-11)
### 이지순
* **부분집합/set**
* 튜플을 기준으로 부분집합 생성
* 최소성 확인을 위해 후보키를 만족하는 리스트와 비교
* 부분집합을 만족하는 튜플의 각 행을 문자열로 붙이고 다른 행과 비교
---
### 정지민
* **구현**
* 부분집합으로 전체 튜플 조합 생성, 길이 순 정렬
* 전체 튜플 조합들을 대상으로 조건 확인
  * 유일성 확인
    * column값을 모두 더해 키 생성
    * set에 더해 row개수와 비교, 수가 같을 경우 유일성 만족
  * 최소성 확인
    * 현재까지 만들어진 후보키와 비교
    * 구성요소 중 일치 개수가 후보키 길이와 같을 경우, 최소성 불만족
