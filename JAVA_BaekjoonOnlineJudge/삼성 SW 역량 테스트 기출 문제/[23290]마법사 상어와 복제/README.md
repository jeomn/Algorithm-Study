# [23290]마법사 상어와 복제(2021-12-09)

### 이지순

---
### 정지민
* **구현 / 브루트포스**
* ArrayList 이차원 배열로 격자판, Deque 이차원 배열로 냄새 표현
* (물고기 복사 > 물고기 이동 > 상어 이동 > 냄새 없애기 > 복사 완료) 반복
* 물고기 복사
  * 전체 격자(4*4)돌면서 격자에 있는 물고기 리스트 fishList에 복사
* 물고기 이동
  * 전체 격자(4*4)돌면서 8가지 방향을 탐색, 탐색 중 이동가능한 칸을 찾으면 이동
* 상어 이동
  * 연속해서 3칸 이동 > 전체 경우의 수는 4방향*3칸 > 3중 for문
  * 이동 좌표 계산 후, 범위 체크
  * 첫번째 이동의 경우, 3번째 이동이 동일한 칸이 될 수 있으므로 물고기 카운팅을 위해 visited방문 처리
  * 마지막 이동에서 없앤 물고기 개수 비교
  * 가장 큰 경우로 상어 이동, 냄새 추가
* 냄새 없애기
  * 전체 격자(4*4) 돌면서 현재 time과 냄새 칸의 냄새 비교
  * deque에 add로 붙여줬기 때문에, 자동으로 정렬되어 있음. 만약 사라지지 않는 냄새 확인 시 addFirst로 다시 맨 앞칸에 넣고 stop
* 복사 완료
  * 복사했던 fishList에 있는 Fish를 격자에 추가
* 전체 격자(4*4)돌면서 물고기 개수 카운팅, 출력
