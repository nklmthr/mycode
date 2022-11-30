package code.project.india.projects.ppp;

public class PhoneCall {

	public static void main(String[] args) {
		solution(125);

	}

	private static boolean solution(int n) {
		int i = 2;
		while (Math.pow(n, ((double)1 / (double)i)) >= 2) {
			if (Math.ceil(Math.pow(Math.round(Math.pow(n, (double)1 / (double)i)), (double)i)) == n)
				return true;
			i++;
		}
		return n == 1;

	}

	/*
	 * static boolean solution(int score1, int score2) { boolean result = false; if
	 * (score1 >= 0 && score1 < 5) { result |= (score2 < 0 || score2 > 6) ? true :
	 * false; } if (score1 >= 5 && score1 < 7) { if (score2 >= 5) { result |= score2
	 * < 5 || score2 > 7 ? true : false; } else { result |= (score2 < 0 || score2 >=
	 * 6) ? true : false; } } if (score1 == 7) { result |= (score2 < 5 || score2 >
	 * 6) ? true : false; } if (score1 > 7 || score2 > 7 || (score1 < 6 && score2 <
	 * 6)) { result |= true; }
	 * 
	 * if (score2 >= 0 && score2 < 5) { result |= (score1 < 0 || score1 > 6) ? true
	 * : false; } if (score2 >= 5 && score2 < 7) { if (score1 >= 5) { result |=
	 * score1 < 7 || score1 > 7 ? true : false; } else { result |= (score1 < 0 ||
	 * score1 > 6) ? true : false; } } if (score2 == 7) { result |= (score1 <= 5 ||
	 * score1 > 6) ? true : false; }
	 * 
	 * return !result; }
	 * 
	 * static int solution(int min1, int min2_10, int min11, int s) { int time = 0;
	 * if (s < min1) { return time; } if (s >= min1) { time += 1; s -= min1; }
	 * System.out.println("s=" + s + ",time=" + time); if (s >= min2_10) { if ((s -
	 * (min2_10 * 9)) < 0) { time += s / min2_10; } else { time += 9; } s -= min2_10
	 * * 9;
	 * 
	 * } System.out.println("s=" + s + ",time=" + time); if (s >= min11) { time += s
	 * / min11; s -= ((s / min11) * min11); } System.out.println("s=" + s + ",time="
	 * + time); return time; }
	 */
}
