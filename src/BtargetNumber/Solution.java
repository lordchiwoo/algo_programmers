package BtargetNumber;

import java.util.Arrays;
//https://programmers.co.kr/learn/courses/30/lessons/43165
/* 
문제 설명
n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
각 숫자는 1 이상 50 이하인 자연수입니다.
타겟 넘버는 1 이상 1000 이하인 자연수입니다.
입출력 예
numbers	target	return
[1, 1, 1, 1, 1]	3	5
*/
public class Solution {
    
    static int[] sumOfSortedNumbers;
    static int[] numbers;
    static int target;
    static int answer = 0;
    public int solution(int[] pNumbers, int pTarget) {
        //사고의 흐름
        //비트 연산으로 1이면 + 0이면 -  // 000...000 ~ 111...111 
        //Case = 0 ~ Math.pow(2, numbers.length) - 1;
        //재귀 + DFS??

        //코딩 시작
        //공통인자들은 굳이 파라미터로 넘기고 싶지 않아서...
        numbers = pNumbers;
        target = pTarget;   
        int numIndex   = numbers.length;        

        // 추가 아이디어 : 특정인덱스보다 아래에 있는 수들의 총합으로 아래에서 조합이 가능한지 미리 판별하기 위해 Sort And Summation
        Arrays.sort(numbers);    //sort했으니 0번째가 가장 작은수 numIndex번째가 가장 큰 수
        sumOfSortedNumbers = new int[numIndex];
        for(int index=0, sum = 0;index < numIndex; index++)
        {
            sum += numbers[index]; // 작은 수 들부터 더해서 총합 어레이에 대입해둔다.
            sumOfSortedNumbers[index] = sum;
        }
        
        DFSlikeRecursion(numIndex-1, 0);  
        return answer;
    }
    public static void DFSlikeRecursion(int numIndex, int currentSum){
        if(numIndex >= 0)//마지막까지 내려왔으면 총 합이 target과 같은지 체크해서 결과를 리턴
        {
            //현재 인덱스 이하의 수를 모두 더한 값을 
            if(Math.abs(target-currentSum) > sumOfSortedNumbers[numIndex] ) return;     //더하든 빼든 타겟에 도달할 수 없는 상태라면
            //아래까지 살펴볼 필요가 없다.

            DFSlikeRecursion(numIndex-1, currentSum-numbers[numIndex] );     //현재 인덱스 위치의 수를 뺐을 때 
            DFSlikeRecursion(numIndex-1, currentSum+numbers[numIndex] );    //현재  인덱스 위치의 수를 더했을 때
        }
        else
        {
            if(target==currentSum)   answer++;
        }
    }
}

/*
// 절대값으로 한번에 판별
테스트 1 〉	통과 (1.68ms, 52.4MB)
테스트 2 〉	통과 (1.49ms, 52.5MB)
테스트 3 〉	통과 (0.62ms, 52.5MB)
테스트 4 〉	통과 (0.49ms, 52.8MB)
테스트 5 〉	통과 (0.89ms, 52.5MB)
테스트 6 〉	통과 (0.48ms, 53.6MB)
테스트 7 〉	통과 (0.50ms, 53.4MB)
테스트 8 〉	통과 (0.70ms, 52.8MB)

//더하기 빼기 모두 포함
        //if(target > (currentSum + sumOfSortedNumbers[numIndex]) ) return 0;     //더해도 타겟보다 작은 상태라면
        //if(target < (currentSum - sumOfSortedNumbers[numIndex]) ) return 0;      //빼도 타겟보다 큰 상태라면        
테스트 1 〉	통과 (1.79ms, 51.9MB)
테스트 2 〉	통과 (1.47ms, 52.6MB)
테스트 3 〉	통과 (1.46ms, 52.3MB)
테스트 4 〉	통과 (0.50ms, 53.3MB)
테스트 5 〉	통과 (1.80ms, 52.9MB)
테스트 6 〉	통과 (0.50ms, 52.2MB)
테스트 7 〉	통과 (0.40ms, 53MB)
테스트 8 〉	통과 (0.78ms, 52.7MB)

//빼도 타겟보다 큰 상태라면 로직 제거
테스트 1 〉	통과 (6.92ms, 52.8MB) <-- 빼도 타겟보다 큰 상태 워스트 케이스 로직이 많은듯.
테스트 2 〉	통과 (4.59ms, 52.1MB) <-- 빼도 타겟보다 큰 상태 워스트 케이스 로직이 많은듯.
테스트 3 〉	통과 (0.68ms, 53.1MB)
테스트 4 〉	통과 (0.66ms, 52.4MB)
테스트 5 〉	통과 (3.37ms, 52.5MB)
테스트 6 〉	통과 (0.72ms, 52.9MB)
테스트 7 〉	통과 (0.97ms, 52.6MB)
테스트 8 〉	통과 (0.90ms, 54.8MB)

 //더해도 타겟에 도달하지 못한다면  로직 제거
테스트 1 〉	통과 (9.91ms, 52.3MB) <-- 더해도 타겟보다 작은 상태 워스트 케이스 로직이 많은듯.
테스트 2 〉	통과 (11.51ms, 52.5MB) <-- 더해도 타겟보다 작은 상태 워스트 케이스 로직이 많은듯.
테스트 3 〉	통과 (0.65ms, 52.7MB)
테스트 4 〉	통과 (0.70ms, 52MB)
테스트 5 〉	통과 (1.02ms, 53.2MB)
테스트 6 〉	통과 (0.81ms, 53.7MB)
테스트 7 〉	통과 (0.59ms, 51.9MB)
테스트 8 〉	통과 (1.12ms, 52.6MB)

//둘다 제거
테스트 1 〉	통과 (15.09ms, 51.7MB) <-- 이 아니라 그냥 얘가 덩어리가 크구나.     테스트 1 〉	통과 (10.55ms, 54.1MB) <-- ???
테스트 2 〉	통과 (14.77ms, 52.4MB) <-- 이 아니라 그냥 얘가 덩어리가 크구나.     테스트 2 〉	통과 (7.23ms, 52.4MB) <-- ???
테스트 3 〉	통과 (0.60ms, 52.1MB)
테스트 4 〉	통과 (0.75ms, 52.5MB)
테스트 5 〉	통과 (2.01ms, 52.8MB)
테스트 6 〉	통과 (0.77ms, 54.3MB)
테스트 7 〉	통과 (0.70ms, 52.3MB)
테스트 8 〉	통과 (1.37ms, 52.4MB)
*/