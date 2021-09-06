class Solution {
    public void gameOfLife(int[][] board) {
        //복제
        int[][] prevBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        int m = board.length;
        int n = board[0].length;
        
        for(int r=0;r<m;r++)            
            for(int c=0;c<n;c++)
                //주위를 둘러보고 다음 결과를 정한다.
                board[r][c] = lookAroundAndMakeDecision(prevBoard, r, c);
        
    }
    
    private int lookAroundAndMakeDecision(int[][] board, int r, int c){
        int[][] directions = { 
            {  0, -1 },   // 0  ↑
            {  1, -1 },   // 1  ↗
            {  1,  0 },   // 2  →
            {  1,  1 },   // 3  ↘
            {  0,  1 },   // 4  ↓
            { -1,  1 },   // 5  ↙
            { -1,  0 },   // 6  ←
            { -1, -1 },   // 7  ↖
         };
        
        int m = board.length;
        int n = board[0].length;
        
        int aliveCount = 0;
        //주변을 둘러보고 카운팅
        for(int i=0;i<8;i++){
            int nextR = r+directions[i][0];
            int nextC = c+directions[i][1];
            
            if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n){
                if(board[nextR][nextC] > 0) aliveCount++;
            }
        }
        
        //살아남거나 되살아나는 조건 체크
        if(aliveCount==2 || aliveCount==3){
            if(board[r][c]==1)
                return 1;
            if(board[r][c]==0 && aliveCount == 3) 
                return 1;
        }
        return 0;        
    }
}