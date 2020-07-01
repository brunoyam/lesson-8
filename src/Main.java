import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static int[] maxLengthSubstringWithoutRepeating(String s) throws Exception {
        Set<Character> used = new HashSet<>();

        int left = 0, right = 0, res = 0;
        int n = s.length();
        int resultStart = -1;

        while (left < n && right < n) {
            char current = s.charAt(right);
            if (!used.contains(current)) {
                used.add(current);
                right++;
                if (res < right - left) {
                    res = right - left;
                    resultStart = left;
                }
            } else {
                used.remove(s.charAt(left));
                left++;
            }
        }

        int[] ans = new int[2];
        ans[0] = res;
        ans[1] = resultStart;
        return ans;
    }

    static void fun1() {
        System.out.println("Мы зашли в первую функцию");
        fun2();
        System.out.println("Мы вышли из первой функции");
    }

    static void fun2() {
        System.out.println("Мы зашли во вторую функцию");
        fun3();
        System.out.println("Мы вышли из второй функции");
    }

    static void fun3() {
        System.out.println("Мы зашли в третью функцию");
        //throw new Exception("Непонятная ошибка");
        System.out.println("Мы вышли из третьей функции");
    }


    static int[][] graph;
    static boolean[] used = new boolean[5];
    static int result = Integer.MAX_VALUE;

    static void dfs(int cur, int curMinCost) {
        if (cur == 1 && used[cur]) {
            if (curMinCost < result) {
                for (int i = 1; i < used.length; i++) {
                    if (!used[i]) return;
                }
                result = curMinCost;
                return;
            }
        }
        if (used[cur]) {
            return;
        }
        used[cur] = true;
        for (int i = 1; i < graph[cur].length; ++i) {
            if (graph[cur][i] != -1) {
                dfs(i, curMinCost + graph[cur][i]);
            }
        }
        used[cur] = false;
    }

    public static void main(String[] args) {

        graph = new int[5][5];

        for (int[] g : graph) {
            Arrays.fill(g, -1);
        }

        graph[1][2] = 1;
        graph[2][3] = 2;
        graph[2][4] = 4;
        graph[3][1] = 10;
        graph[3][4] = 3;
        graph[4][1] = 1;
        graph[4][3] = 5;

        dfs(1, 0);
        System.out.println(result);
    }
}
