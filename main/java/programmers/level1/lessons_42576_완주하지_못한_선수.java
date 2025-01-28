//https://school.programmers.co.kr/learn/courses/30/lessons/42576
package programmers.level1;

import java.util.*;
public class lessons_42576_완주하지_못한_선수 {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "mislav", "ana"};
        System.out.println(solution2(participant, completion));
    }

    // HashMap 처리 속도가 압도적으로 빠르다. 참고 -> https://justdoit0730.tistory.com/142
    public static String solution2(String[] participant, String[] completion) {
        HashMap<String, Integer> participantMap = new HashMap<>();
        String answer = "";

        for (String p : participant) {
            participantMap.put(p, participantMap.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            participantMap.put(c, participantMap.get(c) - 1);
        }

        for (String p : participant) {
            if (participantMap.get(p) > 0) {
                 answer = p;
            }
        }
        return answer;
    }

    // contains 처리 속도가 매우 느림.
    public static String solution(String[] participant, String[] completion) {
        ArrayList<String> participantList = new ArrayList<>(Arrays.asList(participant));
        for (String c : completion) {
            if (participantList.contains(c)) {
                participantList.remove(c);
            }
        }
        return participantList.get(0);
    }
}