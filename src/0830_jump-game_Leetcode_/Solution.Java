import java.util.*;

class Solution {
    public boolean canJump(int[] nums) {
        // 뒤에서부터 0을 찾고 밟으면 안되는 인덱스들을 셋에 업데이트 한 뒤에
        // 0에서 갈수있는곳이 모두 셋에 포함되는지 체크?
        //마지막까지 가기만 하면 됨.
        int i = nums.length -2;
        Set<Integer> indexesToAvoid = new HashSet<Integer>();
        for ( ; i>0; i--){
            int num = nums[i];
            //중지 포지션을 찾지 못하면 피해야할 포지션인덱스에 추가
            if(cannotAvoidStopPosition(i, num, indexesToAvoid))
                indexesToAvoid.add(i);
        }
        
        //마지막까지 가기만 하면 됨.
        return nums.length==1 || !(cannotAvoidStopPosition(0, nums[0], indexesToAvoid));
    }
    private boolean cannotAvoidStopPosition(int i, int num, Set<Integer> indexesToAvoid){
        //0이면 피해야 하는 인덱스
        if(num==0) return true;

        //갈 수 있는 곳이 모두 피해야 하는 인덱스 인지 체크
        for(int x=i+1;x<=i+num;x++)
        {
            if(false == indexesToAvoid.contains(x))
                return false;
        }
        
        return true;
    }
}