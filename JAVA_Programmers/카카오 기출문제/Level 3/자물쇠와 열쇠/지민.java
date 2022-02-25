//20220225
//2020 KAKAO BLIND RECRUITMENT


class Solution {
    
    int N;
    int M;
    int[][] keyShape;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        N = lock.length;
        M = key.length;
        keyShape = key;
        
        int matchCnt = 0;
        for(int r=0; r<N+M-1; r++){
            for(int c=0; c<N+M-1; c++){
                next: for(int idx=0; idx<4; idx++){
                    rotateKey();
                    
                    matchCnt = 0;
                    for(int br=0; br<N; br++){
                        for(int bc=0; bc<N; bc++){
                            int kr = br+M-1-r;
                            int kc = bc+M-1-c;
                            
                            if(kr<0 || kr>=M || kc<0 || kc>=M){
                                matchCnt += lock[br][bc];
                                continue;
                            }
                            if(lock[br][bc] == 0 && keyShape[kr][kc] == 0) continue next;
                            else if(lock[br][bc] == 1 && keyShape[kr][kc] == 1) continue next;
                            else matchCnt++;
                        }
                    }
                    if(matchCnt == N*N) return true;
                }
            }
        }
        
        return false;
    }
    
    public void rotateKey(){
        int[][] newKey = new int[M][M];
        for(int r=0; r<M; r++){
            for(int c=0; c<M; c++){
                newKey[c][M-1-r] = keyShape[r][c];
            }
        }
        keyShape = newKey;
    }
}