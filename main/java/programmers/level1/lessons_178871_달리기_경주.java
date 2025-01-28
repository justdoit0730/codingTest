//https://school.programmers.co.kr/learn/courses/30/lessons/178871
package programmers.level1;

import java.util.*;

public class lessons_178871_달리기_경주 {
    public static void main(String[] args) {
        //                  1       2       3     4       5
        String[] player = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine", "kai"};
        System.out.println(solution(player, callings));
    }
    public static String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rankMap.put(players[i], i);
        }
        
        for (String c : callings) {
            int rank = rankMap.get(c);
            if (rank > 0) {
                String frontPlayer = players[rank - 1];
                
                players[rank] = frontPlayer;
                players[rank - 1] = c;
                
                rankMap.put(c, rank - 1);
                rankMap.put(frontPlayer, rank);
            }
        }

        return players;
    }

    // 속도 효율성 test에서 문제 생김 -> 애초에 answer 는 필요 없음. 그냥 players 만 바뀌면 됨. 위에 다시 작성해 둠.
    public static String[] solution2(String[] players, String[] callings) {
        HashMap<Integer, String> rankMap = new HashMap<>();
        String[] answer = new String[players.length];
        int rank = 1;
        for (String p : players) {
            rankMap.put(rank, p);
            rank++;
        }

        for (String c : callings) {
            for (int i = 1; i <= rankMap.size(); i++) {
                if (rankMap.get(i).equals(c)) {
                    rankMap.replace(i, rankMap.get(i - 1));
                    rankMap.replace(i - 1, c);
                    break;
                }
            }
        }

        for (int i : rankMap.keySet()) {
            answer[i - 1] = rankMap.get(i);
        }

        return answer;
    }



}