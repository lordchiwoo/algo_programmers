package PPerfectSquare;

import java.util.*;

class Solution {
    public int numSquares(int n) {
        Map<Integer, Integer> squareNums = new HashMap<>();
        int i=1;
        int j=1;
        List<Integer> maxSquareRootList = new ArrayList<>();
        maxSquareRootList.add(0);
        for(;i<101;i++)
        {
            int squareNum = i*i;
            while(j<squareNum){
                maxSquareRootList.add(i-1);
                j++;
            }
            squareNums.put(i, squareNum);
            if(n/squareNum < 1) break;
        }
        maxSquareRootList.add(i);
        
        //List<List<Integer>> listOfSquares = new ArrayList<>();
        //listOfSquares.add(List.of());
		
        List<Integer> numOfSquares = new ArrayList<>();		
        numOfSquares.add(0);
        
        int squareRootNum, minSize;
        //minSquareRootNum, 
        int squareNum, currentSize;
        //List<Integer> minimumPerpectSquareList;
        for(i=1; i<=n ; i++){
            squareRootNum = maxSquareRootList.get(i);
            minSize = Integer.MAX_VALUE;
            //minSquareRootNum = Integer.MAX_VALUE;
            
            while(squareRootNum>=1){
                squareNum = squareNums.get(squareRootNum);
                //currentSize = listOfSquares.get(i-squareNum).size()+1;
                currentSize = numOfSquares.get(i-squareNum)+1;
                if(minSize > currentSize){
                    minSize = currentSize;
                    //minSquareRootNum = squareRootNum;
                }
                squareRootNum--;
            }
            //squareNum = squareNums.get(minSquareRootNum);
            //minimumPerpectSquareList = new ArrayList<>(listOfSquares.get(i-squareNum));
            //minimumPerpectSquareList = new ArrayList<>(listOfSquares.get(i-squareNum));
            //minimumPerpectSquareList.add(squareNum);
            //listOfSquares.add(minimumPerpectSquareList);
            numOfSquares.add(minSize);
        }
        
        //int answer = listOfSquares.get(n).size();
        int answer = numOfSquares.get(n);
        return answer;
    }
}
/*
https://leetcode.com/problems/perfect-squares/

Runtime: 183 ms, faster than 15.95% of Java online submissions for Perfect Squares.
Memory Usage: 38.1 MB, less than 73.53% of Java online submissions for Perfect Squares.
*/