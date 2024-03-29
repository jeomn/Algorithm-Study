# 순위 검색(2022-01-27)
### 이지순
* **조합/이진탐색**
* 주어진 info 문자열 배열을 공백을 기준으로 split
* "-"이 포함되는 모든 경우의 수 찾기 위해 조합 생성
* 조합으로 문자열을 만들어 HashMap에 문자열, 숫자 리스트 조합으로 저장
  * 같은 문자열 생성 시 숫자 리스트를 받아와 추가
  * 모든 조합 생성 후 숫자 리스트 정렬 -> 이진 탐색을 위함
* 이진탐색 진행
  * query 반복문 돌려가면서 진행
  * 공백을 기준으로 split
  * 결과 찾고 싶은 문자열을 해시맵에 검색 후 있으면 이분탐색 진행, 없으면 0 삽입
  * 위치 찾고 리스트 - 위치를 결과에 넣어준다
    * 이상이 되는 값이므로 리스트 - 위치 하면 큰 값 갯수 구할 수 있음 
---
### 정지민
* **구현**
* 쿼리에 맞는 지원자의 점수를 list로 저장
* 지원자 정보로 만들 수 있는 쿼리를 만들어 점수를 저장
* 쿼리에 맞는 지원자 점수 list를 이분탐색으로 탐색하여 해당하는 사람 수 반환
