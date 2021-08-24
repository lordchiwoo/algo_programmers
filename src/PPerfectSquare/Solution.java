package PPerfectSquare;

import java.util.*;

class Solution {
    public int numSquares(int n) {
        Map<Integer, Integer> squareNums = new HashMap<>();
        int i=1;
        int j=1;
        List<Integer> maxSquareRootList = new ArrayList<>();
        maxSquareRootList.add(0);

        //삽질 1
        //1의 제곱 부터 101까지
        for(;i<101;i++)
        {
            int squareNum = i*i;
            while(j<squareNum){
                //제곱수 이전 까지를 이전 제곱근으로 채운다
                maxSquareRootList.add(i-1);
                j++;
            }
            //제곱근-제곱수 SET 추가
            squareNums.put(i, squareNum);

            // n보다 작은 최대 제곱수를 찾으면 멈춘다.
            if(n/squareNum < 1) break;
        }
        maxSquareRootList.add(i);
		
        List<Integer> numOfSquares = new ArrayList<>();		
        numOfSquares.add(0);
        
        int squareRootNum, minSize;
        int squareNum, currentSize;
        //DP시작
        for(i=1; i<=n ; i++){
            //현재 숫자보다 작거나 같은 최대 제곱수의 제곱근을 가져와서
            squareRootNum = maxSquareRootList.get(i);
            minSize = Integer.MAX_VALUE;
            
            //1이 될 때까지 제곱수를 꺼내와서
            while(squareRootNum>=1){
                squareNum = squareNums.get(squareRootNum);
                //현재 숫자에서 해당 제곱수를 뺐을 때를 구성하는 최소 제곱수 갯수를 가지고 와서 
                currentSize = numOfSquares.get(i-squareNum);
                //가장 작은 수를 찾는다.
                if(minSize > currentSize)
                    minSize = currentSize;

                squareRootNum--;
            }
            //가장 작은 수 + 1 이 현재 숫자를 구성하는 최소 요소 갯수가 된다.
            numOfSquares.add(minSize+1);
        }
        
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