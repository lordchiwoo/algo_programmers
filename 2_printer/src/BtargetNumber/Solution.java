package BtargetNumber;

import java.util.Arrays;

public class Solution {
    
    static int[] sumOfSortedNumbers;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        //비트 연산으로 1이면 + 0이면 -
        //재귀 + DFS
        int numIndex   = numbers.length;
        int sum = 0;
        
        sumOfSortedNumbers = new int[numIndex];

        //SORT
        Arrays.sort(numbers);

        for(int index=0;index < numIndex; index++)
        {
            sum += numbers[index];
            sumOfSortedNumbers[index] = sum;
        }
        System.out.println(Arrays.toString(sumOfSortedNumbers));
        
        // 000...000 ~ 111...111 
        //(int maxCase = Math.pow(2, numbers.length) - 1;)  
        answer = DFSlikeRecursion(numIndex-1, 0, target, numbers);  
        return answer;
    }
    public static int DFSlikeRecursion(int numIndex, int currentSum, int target, int[] numbers){
        //마지막까지 내려왔으면 총 합이 target과 같은지 체크해서 결과를 리턴
        if(numIndex == -1)
        {
            if(currentSum==target)   return 1;
            else return 0;
        }

        //현재 인덱스 이하의 수를 모두 더한 값을 
        if(target > (currentSum + sumOfSortedNumbers[numIndex]) ) return 0;     //더해도 타겟보다 작은 상태라면
        if(target < (currentSum - sumOfSortedNumbers[numIndex]) ) return 0;      //빼도 타겟보다 큰 상태라면
        //아래까지 살펴볼 필요가 없다.
        
        int answeMinus = DFSlikeRecursion(numIndex-1, currentSum-numbers[numIndex], target, numbers); //현재 인덱스 위치의 수를 빼고 다음인덱스로 넘어간다
        int answerPlus = DFSlikeRecursion(numIndex-1, currentSum+numbers[numIndex], target, numbers); //현재  인덱스 위치의 수를 더하고 다음인덱스로 넘어간다

        return answeMinus + answerPlus+0;
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