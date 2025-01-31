//https://school.programmers.co.kr/learn/courses/30/lessons/155652
package programmers.level1;


import java.util.HashSet;

public class lessons_155652_둘만의_암호 {
    public static void main(String[] args) {
        // 반례 참고! : https://school.programmers.co.kr/questions/72893
        String s = "a";     // 5 ≤ s의 길이 ≤ 50
        String skip = "cb";     // 1 ≤ skip의 길이 ≤ 10
        int index = 2;          // 1  ≤ index ≤ 20
        System.out.println(solution(s, skip, index));
    }

    public static String solution(String s, String skip, int index) {
        // 97 ~ 122
        String answer = "";
        byte[] sByte = s.getBytes();
        HashSet skipByteSet = new HashSet();
        for (int i : skip.getBytes()) {
            skipByteSet.add(i);
        }

        for (int i : sByte) {
            answer += String.valueOf((char) cryptoByte(i, skipByteSet, index));
        }
        return answer;
    }

    public static int cryptoByte(int byteData, HashSet skipByteSet, int index) {
        for (int i = 0; i < index; i++) {
            byteData += 1;
            for (int y = 0; y < skipByteSet.size(); y++) {
                if (skipByteSet.contains(byteData)) {
                    byteData += 1;
                }
                if (byteData > 122) {
                    byteData = 97;
                }
            }
        }
        return byteData;
    }

    // byteData 값과 byteData+index 값 사이에 skip 값 포함(이상, 이하) 있다면 skip 의 수 만큼 + 1 하면 된다. -> 이거 가정부터가 잘못됨 -> byteData+index 사이에 skip 값이 포함되면 더 커지기 떄문!
    // byteData+index 값이 122 초과라면 97부터 다시 센다.
    public static int cryptoByte2(int byteData, byte[] skipByte, int index) {
        // byteData 값과 byteData+index 값 사이에 skip 값 포함(이상, 이하) 있다면 skip 의 수 만큼 + 1 하면 된다.
        // byteData+index 값이 122 초과라면 97부터 다시 센다.
        int byteDataResult = byteData;

        for (int skipValue : skipByte) {
            if (byteData <= skipValue && skipValue <= byteData + index) {
                if (byteDataResult > 122) {
                    byteDataResult = 97;
                } else {
                    byteDataResult += 1;
                }
            }
        }


        return 0;
    }
}