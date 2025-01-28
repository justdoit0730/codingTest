//https://school.programmers.co.kr/learn/courses/30/lessons/176963
package programmers.level1;

import java.util.Arrays;

public class lessons_176963_추억_점수 {
    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {
                {"kon", "kain", "may", "coni"},
                {"may", "kein", "kain", "radi"},
                {"may", "kein", "brin", "deny"}
        };
        System.out.println(solution(name, yearning, photo));
    }
    public static int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        int count = 0;
        for (String[] photoValue : photo) {
            for (int i = 0; i < name.length; i++) {
                if (Arrays.asList(photoValue).contains(name[i])) {
                    answer[count] += yearning[i];
                }
            }
            count++;
        }
        return answer;
    }
}