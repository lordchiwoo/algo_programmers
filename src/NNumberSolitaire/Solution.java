package NNumberSolitaire;

import java.util.ArrayList;
import java.util.List;

//디버그 포인트 찍어놓고 실행흐름대로 설명을 해보자.
public class Solution {
    // minimun Max Value  =  a[0] + a[N] where 2<= N <= 100000
    // each element Range = -10000~10000
    // from 1 to N-2(-5), 
    //maximal result means above 0 shoud be always included
    // Initial Thought ==> Fail
    // Build Max Value among 6 Element / if MinusValue is presented more than 5 in a row
    // worst case example 
    // -100 -100 -100 -100 -100 -100 -100 -10 -10 -10 -1 N
    int[] numArr;
    public int solution(int[] numArr) {
        //numArr = arr;
        int answer = 0;
        
        // At each position, find maxiMum Possible value where it came from among 1-6 previous square.
        // 각 위치에서 1-6개의 이전 사각형 중에서 나온 최대 가능한 값을 찾습니다.
        List<Integer> bestPathValueList= new ArrayList<>();
        bestPathValueList.add(numArr[0]);
        for(int i =1; i<numArr.length; i++)
        {
            int myValue = numArr[i];

            // 일단 내 바로 앞에서 왔다고 가정하고.
            int maxPathSum = Integer.MIN_VALUE;

            // 내 앞 6개의 값(나에게 올 수 있는 후보들) 중에 최대값을 선택한다.
            for(int prevSearchIdx = 1; prevSearchIdx<7; prevSearchIdx++)
            {
                if(i-prevSearchIdx < 0) break;
                int pathSum = bestPathValueList.get(i-prevSearchIdx);
                if(maxPathSum < pathSum){
                    maxPathSum = pathSum;
                }
            }

            // 현재 스퀘어로 올때 가질 수 있는 최대값을 저장한다.
            bestPathValueList.add(maxPathSum+myValue);
        }

        answer = bestPathValueList.get(numArr.length-1);
        return answer;
    }
}

/*
정확성  테스트
테스트 1 〉	통과 (15.00ms, 53.7MB)
테스트 2 〉	통과 (17.42ms, 52.7MB)
테스트 3 〉	통과 (7.90ms, 52.9MB)
테스트 4 〉	통과 (8.18ms, 53.6MB)
테스트 5 〉	통과 (9.45ms, 53.2MB)
테스트 6 〉	통과 (13.04ms, 53MB)
테스트 7 〉	통과 (6.42ms, 52.9MB)
테스트 8 〉	통과 (3.65ms, 53MB)
테스트 9 〉	통과 (9.21ms, 51.6MB)
테스트 10 〉	통과 (11.41ms, 53.8MB)
테스트 11 〉	통과 (10.08ms, 53.3MB)
테스트 12 〉	통과 (14.29ms, 53MB)
테스트 13 〉	통과 (11.17ms, 52.3MB)
테스트 14 〉	통과 (9.96ms, 53.5MB)
테스트 15 〉	통과 (7.42ms, 52.7MB)
효율성  테스트
테스트 1 〉	통과 (29.94ms, 57.9MB)
테스트 2 〉	통과 (42.72ms, 56.4MB)
테스트 3 〉	통과 (47.46ms, 57MB)
테스트 4 〉	통과 (34.43ms, 54.5MB)
테스트 5 〉	통과 (37.65ms, 55.1MB)

 */

// https://app.codility.com/programmers/lessons/17-dynamic_programming/number_solitaire/
/*
 A game for one player is played on a board consisting of N consecutive squares, numbered from 0 to N − 1. There is a number written on each square. A non-empty array A of N integers contains the numbers written on the squares. Moreover, some squares can be marked during the game.

At the beginning of the game, there is a pebble on square number 0 and this is the only square on the board which is marked. The goal of the game is to move the pebble to square number N − 1.

During each turn we throw a six-sided die, with numbers from 1 to 6 on its faces, and consider the number K, which shows on the upper face after the die comes to rest. Then we move the pebble standing on square number I to square number I + K, providing that square number I + K exists. If square number I + K does not exist, we throw the die again until we obtain a valid move. Finally, we mark square number I + K.

After the game finishes (when the pebble is standing on square number N − 1), we calculate the result. The result of the game is the sum of the numbers written on all marked squares.

For example, given the following array:

    A[0] = 1
    A[1] = -2
    A[2] = 0
    A[3] = 9
    A[4] = -1
    A[5] = -2
one possible game could be as follows:

the pebble is on square number 0, which is marked;
we throw 3; the pebble moves from square number 0 to square number 3; we mark square number 3;
we throw 5; the pebble does not move, since there is no square number 8 on the board;
we throw 2; the pebble moves to square number 5; we mark this square and the game ends.
The marked squares are 0, 3 and 5, so the result of the game is 1 + 9 + (−2) = 8. This is the maximal possible result that can be achieved on this board.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A of N integers, returns the maximal result that can be achieved on the board represented by array A.

For example, given the array

    A[0] = 1
    A[1] = -2
    A[2] = 0
    A[3] = 9
    A[4] = -1
    A[5] = -2
the function should return 8, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−10,000..10,000].

 */

/*

 */