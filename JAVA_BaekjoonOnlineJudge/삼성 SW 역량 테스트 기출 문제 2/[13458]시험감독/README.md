# 시험감독(2022-03-14)
### 이지순
* **연산**
* 감독관을 한명씩 배치했으므로 초기 값을 N으로 설정한다.
* 나머지 연산을 통해 부감독관의 수를 정한 후 값 더해준다.

---
### 정지민
* **수학**
* 감독관은 모든 시험장에 1명 있으므로 최소 N명 필요
* 시험장 인원수를 확인하여 부감독관 필요 인원 수 더하기
  * 인원수%부감독관감시인원이 0이면 몫
  * 0이 아니면 몫+1
* 최대 감독관의 수는 1,000,000(시험장) * 1,000,000(응시자)일 때, 1(총감독관 감시 인원), 1(부감독관 감시 인원)인 경우
  * 999,999 * 1,000,000 = 999,999,000,000 > int 범위를 벗어남
  * 감독관 수는 long