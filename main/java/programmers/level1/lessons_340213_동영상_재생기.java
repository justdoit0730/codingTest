//https://school.programmers.co.kr/learn/courses/30/lessons/42576
package programmers.level1;

public class lessons_340213_동영상_재생기 {
    public static void main(String[] args) {
        String video_len = "07:22";
        String pos = "04:05";
        String op_start = "00:15";
        String op_end = "04:07";
        String[] commands = {"next"};
        System.out.println(solution(video_len, pos, op_start, op_end, commands));
    }

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int lenSec = parameterSecTransfer(video_len);
        int posSec = parameterSecTransfer(pos);
        int startSec = parameterSecTransfer(op_start);
        int endSec = parameterSecTransfer(op_end);

        for (String c : commands) {
            if (c.equals("next")) {
                posSec = Math.min(posSec + 10, lenSec);
            } else if (c.equals("prev")) {
                posSec = Math.max(posSec - 10, 0);
            }

            if (startSec <= posSec && posSec <= endSec) {
                posSec = endSec;
            }
        }

        // 굳이 이렇게 할 필요는 없을 것 같다.
//        String answerM = String.valueOf(posSec / 60);
//        String resultM = posSec / 60 < 10 ? "0" + answerM : answerM;
//        String answerS = String.valueOf(posSec % 60);
//        String resultS = "";
//        if (posSec % 60 < 10) {
//            resultS = "0" + answerS;
//        } else if (posSec % 60 == 0) {
//            resultS = "00";
//        }
//        answer = resultM + ":" + resultS;
//        return answer;

        int minutes = posSec / 60;
        int seconds = posSec % 60;
        return String.format("%02d:%02d", minutes, seconds);


    }

    public static int parameterSecTransfer(String time) {
        String[] timeList = time.split(":");
        return Integer.parseInt(timeList[0]) * 60 + Integer.parseInt(timeList[1]);
    }
}