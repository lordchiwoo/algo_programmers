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

내가 너무 복잡하게 풀었네. 22ms
class Solution {
    public int numSquares(int n) {
       
        int[] f = new int[n+1];
        for(int i = 1; i<=n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 1;j*j <= i;j++){
                min = Math.min(min,f[i-j*j]);
            }
            f[i]=min+1;
        }
        return f[n];
    }
}

 와 씨 이건 뭐짘ㅋㅋㅋㅋㅋㅋㅋㅋㅋ 0ms
 class Solution {
    public int numSquares(int n) {
        while (n % 4 == 0) {
            n >>= 2;
        }
        if (n % 8 == 7) {
            return 4;
        } else if (isSquare(n)) {
            return 1;
        }
        for (int root = 1; root * root <= n; root++) {
            if (isSquare(n - root * root)) {
                return 2;
            }
        }
        return 3;
    }
    
    private boolean isSquare(int n) {
        int root = (int)Math.sqrt(n);
        return root * root == n;
    }
}

BFS 스타일 127ms
class Solution {
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList();
        int [] visited = new int[n+1];
        queue.add(n);
        int depth = 0;
        
        while(!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while(size-- >0) {
                int cur = queue.poll();
                for(int i=1; i*i <= cur;i++) {
                    int next = cur - i*i;
                    if(next == 0) {
                        return depth;
                    }
                    if(visited[next] == 1) {
                        continue;
                    }
                    queue.add(next);
                }
            }
        }
        return depth;
    }
}
*/