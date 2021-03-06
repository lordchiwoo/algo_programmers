import NNumberSolitaire.Solution;
//import MRescueBoat.Solution;

public class App {
    public static void main(String[] args) throws Exception {
        
        Solution solved = new Solution();
        
        //LLinkIsland
        // int[] people = {70, 50, 80, 50}; // 7;
        // int limit = 100;
        // int answer = solved.solution(people, limit);
        
        int[] arr = { 1 , -2, 0 , -100, -100, -100, -100, -100, -100, -100, -10, -10, -10, -10, -1, 9 , -1, -2 };
        int answer = solved.solution(arr);
        
        System.out.println("Answer is " + answer);
    }
}
/*
//import Aprinter.Solution;
//import BtargetNumber.*;
//import CNetwork.Solution;
//import DWordTransform.Solution;
//import EMostDistantNode.Solution;
//import FRanking.Solution;
//import GImmigration.Solution;
//import HNumberOfRooms.Solution;
//import ILockAndKey.Solution;
//import JPillarAndBeam.Solution;
//import LLinkIsland.Solution;

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
        //GImmigration
        // int[] times = {7,10,11,12,61};
        // long n = 91;//  
        // long answer = solved.solution(n, times);
        // System.out.println("Answer is " + answer + "  expected answer is " + 28);
        
        //HNumberOfRooms
        // int[] arrows = {6, 5, 2, 7, 1, 4, 2, 4, 6};//{6, 5, 2, 7, 1, 4, 2, 4, 6   };
        // long answer = solved.solution(arrows);
        // System.out.println("Answer is " + answer + "  expected answer is " + 28);

        //ILockAndKey
        
        //int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        //int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};//{6, 5, 2, 7, 1, 4, 2, 4, 6   };
        //int[][] key = {{1, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        //int[][] lock = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        // int[][] key = {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        // int[][] lock = {{1, 1, 1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        // boolean answer = solved.solution(key,lock);
        // System.out.println("Answer is " + answer + "  expected answer is " + 28);

        
        //JPillarAndBeam
        // int n = 5;
        // int[][] buildFrame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        //int[][] answer = solved.solution(n, buildFrame);

        
        //KSurveilanceCamera
        // int[][] routes = {{0,2},{2,3},{3,4},{4,6}};
        // int answer = solved.solution(routes);

        
        //LLinkIsland
        // int n = 5;
        // int[][] costs = {{1, 0, 1}, {2, 3, 1}, {1, 2, 5}}; // 7;
        // int answer = solved.solution(n, costs);
    }
}

*/