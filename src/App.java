//import Aprinter.Solution;
//import BtargetNumber.*;
//import CNetwork.Solution;
//import DWordTransform.Solution;
//import EMostDistantNode.Solution;
import FRanking.Solution;


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

        //EMostDistantNode
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}, {5, 6}, {6, 7}};
        int n = 7;//  

        int answer = solved.solution(n, results);

        System.out.println("Answer is " + answer);
    }
}
/*

*/