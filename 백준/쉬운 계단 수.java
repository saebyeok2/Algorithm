/*
 * 백준 10844번: 쉬운 계단 수
 * 알고리즘 분류: #dp
 */
import java.io.*;

public class Main { 
	static long[][] dp;
	static int N;
	static long mod = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long result = 0;
		N = Integer.parseInt(br.readLine());
		dp = new long[N+1][10];
		// 한자리수일 때 1로 초기화
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		// 길이가 N인 수에 대해 첫 번째 수가 0~9일때 경우의 수를 모두 더함
		for (int i = 0; i <= 9; i++) {
			result+=stair(N, i);
		}
		
		System.out.println(result%mod);
	}
	
	public static long stair(int N, int i) {
		if(N==1) {
			// 한자리수일 경우 리턴
			return dp[N][i];
		}
		
		if(dp[N][i] == 0) {
			if (i == 0)
				// N이 0일경우 앞자리 수는 1
				dp[N][i] = stair(N-1,1);
			else if (i == 9)
				// N이 9일경우 앞자리 수는 8
				dp[N][i] = stair(N-1,8);
			else {
				// 그 외의 경우는 i-1, i+1 둘 다 올수있음
				dp[N][i] = stair(N-1,i-1)+stair(N-1,i+1);
			}
		}
		
		return dp[N][i]%mod;
	}
}








