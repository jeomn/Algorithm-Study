# 신고 결과 받기(2022-01-30)
### 이지순
* **구현/문자열**
* Set 사용해 중복 신고 처리
* Map 사용해 Key에는 경고 받을 사람, value에는 신고한 사람 저장
---
### 정지민
* **구현/문자열**
* 신고받은 내역을 토대로, Key: 경고받을 사람, Value(Set): 신고한 사람 저장
* 중복 신고는 1회로 카운트 되기 때문에 Set 사용
* 신고 맵을 토대로, K번 이상 신고 받았는 지 확인
  * 신고 받았다면 해당 인덱스 +1
