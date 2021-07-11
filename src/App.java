//import Aprinter.Solution;
//import BtargetNumber.*;
//import CNetwork.Solution;
//import DWordTransform.Solution;
//import EMostDistantNode.Solution;
//import FRanking.Solution;
import GImmigration.Solution;


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
        //int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        //int n = 3;//  
        
        //DWordTransform
        //String begin = "hit";
        //String target = "cog";
        //String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        
        //EMostDistantNode
        //int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        //int n = 3;//  

        //FRanking
        // int[][][] results = {
        //     {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}, {5, 6}, {6, 7}}   		,
        //     {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}}   						,
        //     {{1, 4}, {4, 2}, {2, 5}, {5, 3}}   								,
        //     {{3, 5}, {4, 2}, {4, 5}, {5, 1}, {5, 2}}   						,
        //     {{1, 2}, {1, 3}}   												,
        //     {{1, 6}, {2, 6}, {3, 6}, {4, 6}}  								,
        //     {{1, 2}, {3, 4}, {5, 6}, {7, 8}}   								,
        //     {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {6, 1}, {7, 1}, {8, 1}, {9, 1}},
        //     {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {2, 4}, {2, 6}}  		,
        //     {{4, 3}, {4, 2}, {3, 2}, {3, 1}, {4, 1}, {2, 1}}   				,
        //     {{3, 2}, {3, 1}}   												,
        //     {{1, 2}, {1, 3}, {3, 4}}   										}
        //     ;
        // int ns[] = {7,6,5,5,3,6,8,9,6,4,3,4 };//  

        // int answers[] = {4,6,5,1,1,0,0,1,6,4,1,1};
        
        // for(int i=0;i<12;i++){
        //     int answer = solved.solution(ns[i], results[i]);

        //     System.out.println("Answer is " + answer + "  expected answer is " + answers[i]);
        // }
        //EMostDistantNode
        int[] times = {7,10,11,12,61};
        long n = 91;//  
        long answer = solved.solution(n, times);
        System.out.println("Answer is " + answer + "  expected answer is " + 28);
    }
}
/*

*/