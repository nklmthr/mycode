
public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcd";
		char[] c = s.toCharArray();
		c[1] = 'x';
		System.out.println(s);
		
		
		String s1 = "abcd";
		String s2 = "abcd";
		String s3 = "defg";
		String s4 = "pqrt";
		
		String s11 = new String("abcd");
		String s12 = new String("abcd");
		String s13 = new String("defg");
		String s14 = new String("pqrt");
		
		System.out.println("s1 == s2:"+(s1 == s2));
		
		System.out.println("s1 == s11:"+(s1 == s11));
		System.out.println("s2 == s12:"+(s2 == s12));
		System.out.println("s3 == s13:"+(s3 == s13));
		System.out.println("s4 == s14:"+(s4 == s14));
	}

}
