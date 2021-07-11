package GImmigration;

//https://programmers.co.kr/learn/courses/30/lessons/43238
/*
문제 설명
n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.

처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다. 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.

모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.

입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

제한사항
입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
심사관은 1명 이상 100,000명 이하입니다.
입출력 예
n	times	return
6	[7, 10]	28
입출력 예 설명
가장 첫 두 사람은 바로 심사를 받으러 갑니다.

7분이 되었을 때, 첫 번째 심사대가 비고 3번째 사람이 심사를 받습니다.

10분이 되었을 때, 두 번째 심사대가 비고 4번째 사람이 심사를 받습니다.

14분이 되었을 때, 첫 번째 심사대가 비고 5번째 사람이 심사를 받습니다.

20분이 되었을 때, 두 번째 심사대가 비지만 6번째 사람이 그곳에서 심사를 받지 않고 1분을 더 기다린 후에 첫 번째 심사대에서 심사를 받으면 28분에 모든 사람의 심사가 끝납니다.

출처

※ 공지 - 2019년 9월 4일 문제에 새로운 테스트 케이스를 추가하였습니다. 도움을 주신 weaver9651 님께 감사드립니다.
*/
public class Solution {
    public long solution(long n, int[] times) {
        long answer = 0;
        // 생각의 흐름  - 최소공배수를 먼저 구하고 최소공배수까지 각각의 심사관이 몇명 처리 하는지 계산하면
        // (int) (N+1 /  최소공배수)   + 나머지 처리에 걸리는 시간 을 하면 되지 않을까?
        // https://twpower.github.io/69-how-to-get-gcd-and-lcm 
        //....음? 제한조건 - 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.
        // 20분이 되었을 때, 두 번째 심사대가 비지만 6번째 사람이 그곳에서 심사를 받지 않고 1분을 더 기다린 후에 첫 번째 심사대에서 심사를 받으면 28분에 모든 사람의 심사가 끝납니다.
        // 이 부분 처리가 핵심인듯.

        //int LCM = times[0];
        //for(int i=1; i<times.length;i++)
        //{
        //    LCM = lcm(LCM, times[i]);
        //}
        //System.out.println(LCM); // {7,10,11,12,61}; => 281820 //this is 노답

        java.util.Arrays.sort(times); 
        long min=n/times.length * times[0];
        long max=n/times.length * times[times.length-1];

        long mid;
        
        while(min<=max)
        {
            mid = (min+max)/2;
            long processCnt = processImmigration(mid, times);

            if(processCnt<n)
                min = mid+1;
            else{
                max = mid-1;
                answer = mid;
            }
        }

        return answer;
    }

    private long processImmigration(long mid, int[] times) {
        long processedTraveler = 0;
        for(int time : times)
        {
            processedTraveler+=mid/time;
        }
        System.out.println( processedTraveler );
        return processedTraveler;
    }
}

/*
테스트 1 〉	통과 (0.69ms, 52.2MB)
테스트 2 〉	통과 (1.22ms, 52.7MB)
테스트 3 〉	통과 (3.55ms, 54.2MB)
테스트 4 〉	통과 (106.97ms, 59.4MB)
테스트 5 〉	통과 (98.38ms, 60.2MB)
테스트 6 〉	통과 (119.88ms, 60.1MB)
테스트 7 〉	통과 (131.40ms, 59.4MB)
테스트 8 〉	통과 (141.55ms, 60.2MB)
테스트 9 〉	통과 (1.95ms, 52MB)
    int gcd(int a, int b){
        while(b!=0){
            int r = a%b;
            a= b;
            b= r;
        }
        return a;
    }
    
    int lcm(int a, int b){
        return a * b / gcd(a,b);
    }

*/