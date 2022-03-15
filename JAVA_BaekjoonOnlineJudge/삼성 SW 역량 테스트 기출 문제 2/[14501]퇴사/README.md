# 퇴사(2022-03-14)
### 이지순
* **DP**
* 점화식
  ```
  if (i+period[i][0]<=N) 
    answer[i+period[i][0]] = Math.max(answer[i+period[i][0]], answer[i]+period[i][1])
  ```
* 상담이 지속되므로 값을 계속 갱신해줘야한다.(25줄)

---
### 정지민
