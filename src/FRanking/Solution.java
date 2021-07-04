package FRanking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://programmers.co.kr/learn/courses/30/lessons/49191
/*
문제 설명
n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다. 
권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다. 
심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
선수의 수는 1명 이상 100명 이하입니다.
경기 결과는 1개 이상 4,500개 이하입니다.
results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
모든 경기 결과에는 모순이 없습니다.

입출력 예
n	results	                                    return
5	[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	2

입출력 예 설명
2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
*/
public class Solution {
    Map < Integer, PlayerRecord> mapOfPlayerRecord;
    public int solution(int n, int[][] results) {
        int answer = 0;
        //생각의 흐름
        //1.  노드 별로 이긴놈 진놈을 다 저장하면?
        //  한 노드의 순위가 n-1번 승부를 통해서 결정이 되면 승리 K회 / 패배 J회 라고 했을때
        //  승리한 노드들은 K개의 노드들 간의 경기에서 K-1회의 결과(즉 자신을 제외한 모든 K 노드들과의 경기 결과)를 알면 순위가 정해진다.
        // 따라서 처음 순회를 하면서 노드 별로 이긴 상대/진 상대를 저장하고
        // 한 노드의 순위가 정해지면 그 노드를 기준으로 K / J 에 속하는 노드의 경기 결과만 남긴 후 갯수를 센다?
        // 를 반복 하면 순위가 정해진 노드의 갯수가 나옴. + 순위 만큼 반복하면서 순위만큼 분할 정복하는 느낌적인 느낌... 베스트케이스 일 때 NlogN???
        // 제거를 쉽게 하려면 리스트 순회가 아니라 맵으로 저장해서 있으면 지우는 방향으로...
        // - 경기결과를 1회 순회 하고 나서 정해진 순위 노드를 기준으로 트리...를 구성해 나가는 느낌적인 느낌.
        // 데이터 구조가 엄청 복잡해지겠다.

        //그래프 생성
        mapOfPlayerRecord = buildMapOfPlayerRecord(results);
        
        //System.out.println(mapOfPlayerRecord);
        // 최초 순위 선정을 위한 경기자는 모든 선수
        List<Integer> leaguePlayer = new ArrayList<Integer>();
        for(int i=1;i<=n;i++) leaguePlayer.add(i);

        answer = getFixedRankRecursive(leaguePlayer);
        return answer;
    }

    
    //n-1회 경기 결과를 가진 기록을 찾는다(순위 확정)
    //기록의 상위 리스트에 있는 노드들은 상위 노드만 남기고
    //기록의 하위 리스트에 있는 노드들은 하위 노드만 남긴다.
    //남은 노드수 w-1회, l-1회 경기 결과를 가진 기록을 찾는다. (왼쪽 트리 오른쪽 트리?)
    private int getFixedRankRecursive(List<Integer> leaguePlayer) {
        int leagueSize = leaguePlayer.size();
        int rankFixingMatchSize = leagueSize - 1;

        PlayerRecord rankFixedPlayerRecord = null;
        for(int playerIndex : leaguePlayer)
        {
            PlayerRecord pRecord = mapOfPlayerRecord.get(playerIndex);
            //리스트에 있는 노드만 남기고 제거 한 뒤에 
            pRecord.filterGameResult(leaguePlayer);
            
            //리그 내 모든 선수에게 이기고 진 선수를 찾으면
            if(pRecord.matchNumbers() == rankFixingMatchSize)
            {
                //순위가 결정되었다!
                rankFixedPlayerRecord = pRecord;
                break;
            }
        }
        //못찾으면 거기서부터 리턴한다. 
        //내 기준 위아래 있는 애는 내 순위를 모르니 모두 알 수 없음이 된다.
        if(rankFixedPlayerRecord==null) return 0;
        
        //이긴자의 리그
        int winner = getFixedRankRecursive(rankFixedPlayerRecord.getWinnerList());
        //패배자의 리그
        int loser = getFixedRankRecursive(rankFixedPlayerRecord.getLoserList());

        //이긴자 리그에서 찾은 랭크와 패배자 리그에서 찾은 랭크를 더하고
        //현재리그의 랭크도 찾았으니 +1 (중요!)
        return winner + loser + 1;
    }
    public Map < Integer, PlayerRecord> buildMapOfPlayerRecord(int[][] results) {
        Map < Integer, PlayerRecord> mapOfPlayerRecord = new HashMap < Integer, PlayerRecord> ();

        //경기 결과로 주어진 두개의 기록에 각각 상대 선수와의 경기 결과를 기록한다
        for (int[] gameResult: results) {
            PlayerRecord playerRecord;
            int winner = gameResult[0];
            //int loser = gameResult[1];

            for(int i=0;i<2;i++){
                //내가 메인노드 니가 서브노드
                int recordHolder = gameResult[i];
                int opponent = gameResult[ i==1 ? 0 : 1 ];

                //내 노드의 인접노드 리스트가 없으면 생성해준다.
                if (!mapOfPlayerRecord.containsKey(recordHolder)) {
                    playerRecord = new PlayerRecord (recordHolder);
                    mapOfPlayerRecord.put(recordHolder, playerRecord);
                }

                //내 노드의 인접노드 리스트를 가지고 와서 서브노드를 집어 넣는다.
                playerRecord = mapOfPlayerRecord.get(recordHolder);
                playerRecord.putGameResult(opponent, recordHolder==winner);// i==0 );
            }
        }

        // 나에게 이긴사람을 이긴사람은 모두 나를 이기고
        // 나에게 진 사람에게 진사람은 모두 나에게 진다.
        // 검출 로직을 n-1회 경기로 잡았기 때문에 기록을 모두 도출해야한다.ㅠㅠㅠ
        pRecordMerge(mapOfPlayerRecord);
        return mapOfPlayerRecord;
    }

    private void pRecordMerge(Map<Integer, PlayerRecord> mapOfPlayerRecord) {
        boolean isChanged=true;
        while(isChanged)
        {
            isChanged=false;
            // 경기 기록을 하나씩 꺼내서
            for(Map.Entry<Integer, PlayerRecord> pRecordSet : mapOfPlayerRecord.entrySet()){
                PlayerRecord pRecord = pRecordSet.getValue();
                int recordSize = pRecord.matchNumbers();

                List<Integer> playerList;
                // 나에게 이긴사람을 이긴사람은 모두 나를 이긴 걸로
                playerList = pRecord.getWinnerList();
                for(int winner : pRecord.getWinnerList())
                {
                    playerList =  union(
                        playerList,
                        mapOfPlayerRecord.get(winner).getWinnerList()
                    );
                }
                pRecord.setWinnerList(playerList);

                // 나에게 진 사람에게 진사람은 모두 나에게 진걸로
                playerList = pRecord.getLoserList();
                for(int loser : pRecord.getLoserList())
                {
                    playerList =  union(
                        playerList,
                        mapOfPlayerRecord.get(loser).getLoserList()
                    );
                }
                pRecord.setLoserList(playerList);

                // 경기 결과에 변동이 없을 때 까지 쭉 찾아낸다... 시간복잡도 폭발!.ㅠㅠ
                // DFS로 짜면 반복이 좀 줄어들 것 같긴 한데..ㅠ
                if(recordSize == pRecord.matchNumbers()){
                    isChanged=true;
                }
            }
        }
    }

    public class PlayerRecord{
        public int playerIndex;
        public List<Integer> winFromThisPlayers, loseFromThisPlayers;
        public PlayerRecord(int playerIndex){
            this.playerIndex = playerIndex;
            this.winFromThisPlayers = new ArrayList<Integer>();
            this.loseFromThisPlayers = new ArrayList<Integer>();
        }
        public void putGameResult(int opponent, boolean amIWinner)
        {
            if(amIWinner){
                winFromThisPlayers.add(opponent);
            }
            else{
                loseFromThisPlayers.add(opponent);
            }
        }
        public List<Integer> getWinnerList(){
            return winFromThisPlayers;
        }
        public List<Integer> getLoserList(){
            return loseFromThisPlayers;
        }
        public void setWinnerList(List<Integer> list){
            winFromThisPlayers = list;
        }
        public void setLoserList(List<Integer> list){
            loseFromThisPlayers = list;
        }

        public void filterGameResult(List<Integer> leagueList) {
            setWinnerList(
                intersection(winFromThisPlayers, leagueList)
            );
            setLoserList(
                intersection(loseFromThisPlayers, leagueList)
            );
        }
        
        public int matchNumbers()
        {
            return winFromThisPlayers.size() + loseFromThisPlayers.size();
        }
        public String toString() { 
            return "Name: '" + this.playerIndex + "', winFromThisPlayers: '" + this.winFromThisPlayers.toString() + "', loseFromThisPlayers: '" + this.loseFromThisPlayers.toString() + "\n'";
        } 
    }

    //리스트의 합집합 교집합 구하기
    //https://stackoverflow.com/questions/5283047/intersection-and-union-of-arraylists-in-java
    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

}
/*

*/