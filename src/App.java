//import Aprinter.Solution;
//import BtargetNumber.*;
import CNetwork.Solution;;

public class App {
    public static void main(String[] args) throws Exception {
        Solution solved = new Solution();

        //Aprinter
        //int{} priorities = {2, 1, 3, 2};//  {1, 1, 9, 1, 1, 1};//  {2, 2, 5, 6, 7, 4, 1, 8, 3, 1, 3, 1, 5, 7, 4, 9, 3, 2};
        //int location =2;//  0;//  8;
        //int myJobSequence = solved.solution(priorities, location);
        
        //BtargetNumber
        //int{} numbers = {1, 1, 1, 1, 1};
        //int target = 3;// 

        //CNetwork
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int n = 3;//  
        
        int myJobSequence = solved.solution(n, computers);

        System.out.println("Hello, World!" + myJobSequence);
    }
}
/*
테스트 1 〉	통과 (0.18ms, 52.9MB)
테스트 2 〉	통과 (0.23ms, 53.7MB)
테스트 3 〉	통과 (0.08ms, 53.3MB)
테스트 4 〉	통과 (0.07ms, 52.6MB)
테스트 5 〉	통과 (0.07ms, 52.7MB)
테스트 6 〉	통과 (0.10ms, 53.2MB)
테스트 7 〉	통과 (0.08ms, 52.9MB)
테스트 8 〉	통과 (0.15ms, 53.3MB)
테스트 9 〉	통과 (0.11ms, 53.9MB)
테스트 10 〉	통과 (0.12ms, 52.2MB)
테스트 11 〉	통과 (0.18ms, 52.3MB)
테스트 12 〉	통과 (0.05ms, 52.3MB)
테스트 13 〉	통과 (0.12ms, 53.1MB)
테스트 14 〉	통과 (0.06ms, 52.7MB)
테스트 15 〉	통과 (0.07ms, 52.3MB)
테스트 16 〉	통과 (0.13ms, 52MB)
테스트 17 〉	통과 (0.48ms, 53.6MB)
테스트 18 〉	통과 (0.11ms, 53MB)
테스트 19 〉	통과 (0.17ms, 52.6MB)
테스트 20 〉	통과 (0.09ms, 53.6MB)
*/