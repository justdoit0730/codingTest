//https://school.programmers.co.kr/learn/courses/30/lessons/64061
// 2019 kakao 개발자 겨울 인턴십

package programmers.level1;

import java.util.*;

public class lessons_64061_크레인_인형뽑기_게임 {
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},
                        {0,0,1,0,3},
                        {0,2,5,0,1},
                        {4,2,4,4,2},
                        {3,5,1,3,1}};
        int[] move = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board, move));
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        HashMap<Integer, Integer> bucket = new HashMap<>();
        int count = 0;

        for (int move : moves) {
            --move;
            for (int y = 0; y < board.length; y++) {
                if (board[y][move] != 0) {
                    bucket.put(count, board[y][move]);
                    board[y][move] = 0;
                    count++;
                    if (bucket.size() > 1 && bucket.get(bucket.size() - 1) == bucket.get(bucket.size() - 2)) {
                        bucket.remove(bucket.size() - 1);
                        bucket.remove(bucket.size() - 1);
                        answer += 2;
                        count = bucket.size();
                    }
                    break;
                }
            }
        }
        return answer;
    }

}