# 이진 검색 트리(2022-06-29)
### 이지순
* **트리**
* Node 클래스 생성해 전체 트리 정보 저장
* 객체 안 add 함수 사용해 노드 저장
  * 루트보다 크면 오른쪽
  * 루트보다 작으면 왼쪽
* 루트 노드 기준 왼, 오 후위 순회
---
### 정지민
* **트리**
* 전위 순회 결과를 순서대로 배열에 저장
* 전위 순회에서 항상 맨 처음 노드가 루트노드
* 이진 트리는 항상 노드 기준 왼쪽은 노드보다 작은 값, 오른쪽은 노드보다 큰 값
* 위의 특징들을 이용하여 루트 노드를 기준으로 왼쪽, 오른쪽을 후위 순회
