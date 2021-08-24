package QNumberOfIsland;

import java.util.*;

class Solution {
    int xSize = 0;
    int ySize = 0;
    public int numIslands(char[][] grid) {
        ySize = grid.length;
        xSize = grid[0].length;
        
        int answer=0;
        //모든 셀을 순회하면서 땅을 찾으면 체크한다.
        for(int y=0; y<ySize ; y++){
            for(int x=0; x<xSize ; x++){
                if(grid[y][x]=='1'){
                    expandIsland(grid, x,y);
                    answer ++;
                }
                
            }
        }
        
        return answer;
    }
    
    public int expandIsland(char[][] grid, int x, int y){
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; //UDLR  
        //Visited 체크
        grid[y][x] = '2';
        
        //한번 땅을 찾아서 들어왔으면 주변을 재귀적으로 탐색하면서 연결된 땅을 모두 visited 처리한다.
        for(int[] move : directions){
            int nextX = x + move[0];
            int nextY = y + move[1]; 
            
            //주변 좌표가 주어진 지도 범위 이내 이고 땅('1')이면 Visit
            if(0<=nextX && nextX<xSize
              && 0<=nextY && nextY<ySize
              && grid[nextY][nextX]=='1'){
                expandIsland(grid, nextX,nextY);
            }
        }
        return 1;        
    }
        
}

/*
https://leetcode.com/problems/number-of-islands

Runtime: 2 ms, faster than 46.47% of Java online submissions for Number of Islands.
Memory Usage: 41.4 MB, less than 68.20% of Java online submissions for Number of Islands.
*/