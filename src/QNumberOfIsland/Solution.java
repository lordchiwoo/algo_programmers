package QNumberOfIsland;

import java.util.*;

class Solution {
    int xSize = 0;
    int ySize = 0;
    public int numIslands(char[][] grid) {
        ySize = grid.length;
        xSize = grid[0].length;
        
        int answer=0;
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
        grid[y][x] = '0';
        
        for(int[] move : directions){
            int nextX = x + move[0];
            int nextY = y + move[1]; 
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