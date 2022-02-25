class Solution {
    int[][] map;
    int[][] copyMap;
    int keylength;
    int locklength;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        int len = lock.length + (key.length-1)*2;
        keylength = key.length;
        locklength = lock.length;
        map = new int[len][len];
        
        for(int i=0;i<lock.length;i++) {
            for(int j=0;j<lock.length;j++) {
                map[key.length-1+i][key.length-1+j] = lock[i][j];
            }
        }
        
        int[][] keyCopy = new int[keylength][keylength];
        for(int i=0;i<keylength;i++) {
            keyCopy[i] = key[i].clone();
        }
        
        for(int i=0;i<4;i++) {
            int[][] temp = new int[keylength][keylength];
            for(int r = 0;r<keylength;r++) {
                for(int c = 0;c<keylength;c++) {
                    temp[c][keylength-1-r] = keyCopy[r][c];
                }
            }
            
            for(int r=0;r<len-key.length+1;r++) {
                for(int c=0;c<len-key.length+1;c++) {
                    copyMap = new int[len][len];
                    copy();
                    
                    for(int m=0;m<key.length;m++) {
                        for(int n=0;n<key.length;n++) {
                            copyMap[r+m][c+n] += temp[m][n];
                        }
                    } 
                    
                    if(check())
                        return true;
                }
            }
            
            for(int j=0;j<keylength;j++)
                keyCopy[j] = temp[j].clone();
            
        }
        
        return false;
    }
    
    private boolean check() {
        boolean keycheck = true;
        for(int i=0;i<locklength;i++) {
            for(int j=0;j<locklength;j++) {
                if(copyMap[i+keylength-1][j+keylength-1] != 1)
                    keycheck = false;
            }
        }
        return keycheck;
    }
    
    public void copy() {
        for(int i=0;i<map.length;i++) {
            copyMap[i] = map[i].clone();
        }
    }
}
