package it.beije.xvii.exercises.trapani;

public class StringUtils {
	
	public static int indexOf(String s, char c) {
		int index = 0;
		for (int i=0; i<s.length(); i++) {
		if (s.charAt(i)== c) {
			index=i;
			break;
			} else 
				index=-1;	
		}
		return index;
	}
	
	public static int indexOf(String s, char c, int fromIndex) {
		int index=0;
		if(s.charAt(fromIndex)== c) {
			index=fromIndex;
		} else 
			index=-1;		
		return index;
	}
	
	public static int indexOf(String s, String str) {
		int index =0;
		for (int i=0; i<s.length(); i++) {
			for (int j=0; j<str.length(); j++) {
				if((s.charAt(i)==str.charAt(j))) {
					index=i-j;
				} else 
					index=-1;	
			}	
		}
		return index;
	}
	
	public static int indexOf(String s, String str, int fromIndex) {

		int index =0;
		for (int i=fromIndex; i<s.length(); i++) {
			for (int j=0; j<str.length(); j++) {
				if((s.charAt(i)==str.charAt(j))) {
					index=i-j;
				} else 
					index=-1;	
			}	
		}
		return index;
	}
	
	public static String substring(String s, int beginIndex) {
		String sub = "";
		if(beginIndex>= s.length()) {
			System.out.println("Indice non valido");
		}
		for(int i=beginIndex; i< s.length(); i++) {
			sub += s.charAt(i);
		}
		
		return sub;
	}
	
	public static String substring(String s, int beginIndex, int endIndex) {
		String sub= "";
		if((beginIndex>= s.length()) || (endIndex>s.length())){
			System.out.println("Indice non valido");
		}
		for(int i=beginIndex; i<endIndex; i++) {  //controlla gia' qui se beginIndex==endIndex lanciando exception
			sub += s.charAt(i);
		}
		
		
		return sub;
	}
	
	public static String toLowerCase(String s) {
		String low="";
		
		for(int i=0; i<s.length();i++) {
			if((s.charAt(i)>='A') && (s.charAt(i)<='Z')){
				char c = (char) (s.charAt(i)+32);
				low +=c;
			} else
				low +=s.charAt(i);
		}
		
		return low;
	}

	public static String toUpperCase(String s) {

		String up="";
		
		for(int i=0; i<s.length();i++) {
			if((s.charAt(i)>='a') && (s.charAt(i)<='z')){
				char c = (char) (s.charAt(i)-32);
				up +=c;
			} else
				up +=s.charAt(i);
		}
		
		return up;
	}
	
	public static boolean equals(String s, String str) {
		boolean eq=false;
		if(s.length()==str.length()){
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i)==str.charAt(i))
				eq=true;
			}
		}
		
		return eq;
	}
	
	public static boolean equalsIgnoreCase(String s, String str) {
		
		return equals(toLowerCase(s),toLowerCase(str));
	}
	
	public static boolean contains(String s,String str) {
		for(int i=0; i<str.length(); i++) {
			for(int j=0; j<str.length(); j++) {
				if(str.charAt(i)!=s.charAt(j)) {
					break;
				} else {
					return true;
				}
			}
			
		}		
		return false;
	}
	
	public static boolean startsWith(String s, String prefix){
		if(s.charAt(0)!=prefix.charAt(0)) return false;
		for(int i=1; i<prefix.length(); i++) {
			if(s.charAt(i)!=prefix.charAt(i)) return false;
		}
		
		
		return true;
	}
	
	public static boolean endsWith(String s, String suffix) {
		int in = s.length()-suffix.length();
		if((s.charAt(s.length()-1)!=suffix.charAt(suffix.length()-1)) || (s.charAt(in)!=suffix.charAt(0))) return false;
		
		for (int i=0; i<suffix.length(); i++) {
			if(s.charAt(i+in)!= suffix.charAt(i)) return false;
		}
		
		return true;
	}
	
	public static String replace(String s, char oldC, char newC) {
		String str = "";
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)==oldC) {
				str += newC;
			} else {
				str+= s.charAt(i);
			}		
		}	
		return str;
	}
	
//	public static String replace(String s, String oldC, String newC) {
//		String str="";
//		if(!(contains(s,oldC))) 
//			System.out.println("Stringa da sostituire non trovata");
//		for(int i=0; i<s.length(); i++) {
//			if(indexOf(s,oldC)==-1) {
//				str += 
//			}
//		}
//		
//		
//		
//		return str;
//	}
	
	public static void main(String[] args) {
		String s = "animal";
		String eq = "animal";
		String up= "ANIMAL";
		String up1 = "AnimAl";
		
//		System.out.println(indexOf(s, 'n'));		
//		System.out.println(indexOf(s, 's'));
//		
//		System.out.println(indexOf(s,'m', 3));		
//		System.out.println(indexOf(s,'m', 4));		
		
//		System.out.println(indexOf(s,"mal"));		
//		System.out.println(indexOf(s,"son"));
//		
//		System.out.println(indexOf(s,"al",1));		
//		System.out.println(indexOf(s,"als",1));
//		System.out.println(indexOf(s,"sdf", 1));
//		System.out.println(indexOf(up,"l", 1));
//		
		
//		System.out.println(substring(s,2));			
//		System.out.println(substring(s,7));
//		
//		System.out.println(substring(s,1,6));		
//		System.out.println(substring(s,1,7));
//		System.out.println(substring(s,1,1));
//		
//		System.out.println(toLowerCase(up));		
//		System.out.println(toLowerCase(up1));
//		
//		System.out.println(toUpperCase(s));
//		
//		System.out.println(equals(s,eq));
//		
//		System.out.println(equalsIgnoreCase(s,up));
//		
//		System.out.println(contains(s,"mal"));
//		System.out.println(contains(s,"fes"));
//		
//		System.out.println(startsWith(s, "ani"));
//		System.out.println(startsWith(s, "mal"));
//		
//		System.out.println(endsWith(s,"imal"));
//		
//		System.out.println(replace(s,'m','M'));*/
//		
//		System.out.println(replace(s,"msms", "hehe"));
		
	}

}
