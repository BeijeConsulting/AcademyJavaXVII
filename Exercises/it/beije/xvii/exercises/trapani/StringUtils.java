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
		
	    for (int i= 0; i<=s.length()-str.length(); i++){		
	        boolean found = true;
	        for (int j = 0; j < str.length(); j++) {
	            if (s.charAt(i + j) != str.charAt(j)) {
	                found = false;
	                break;
	            }
	        }
	        if (found) {
	            return i;  
	        }
	    }

	    return -1;  
	}

	
	public static int indexOf(String s, String str, int fromIndex) {
		
	    for (int i= fromIndex; i<=s.length()-str.length(); i++){		
	        boolean found = true;
	        for (int j = 0; j < str.length(); j++) {
	            if (s.charAt(i + j) != str.charAt(j)) {
	                found = false;
	                break;
	            }
	        }
	        if (found) {
	            return i;  
	        }
	    }

	    return -1;  
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
		if(indexOf(s,str)!=-1) return true;
		else
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
	
	public static String replace(String s, String oldC, String newC) {
		String str="";
		int temp=0;
		int l=0;
		if(newC.length()>oldC.length()) {
			l=s.length()+(newC.length()-oldC.length());
		} else l=s.length();

		if(contains(s,oldC)) {															
			if(indexOf(s,oldC)==0) {							//SOSTITUZIONE INIZIO STRINGA	
				for(int i=0; i<newC.length(); i++) {			
					str+=newC.charAt(i);												
				}												
				for(int i=newC.length(); i<l; i++) {						
					str+=s.charAt(i);							
				}	
			} else {											//SOSTITUZIONE NON INIZIO STRINGA
				for(int i=0; i<indexOf(s,oldC); i++) {			
					str+=s.charAt(i);							
				} 
			
				for(int i=indexOf(s,oldC); i<newC.length(); i++) {			
				str+=newC.charAt(i);							
				temp=i+1;
				}
				for(int i=temp; i<l; i++) {			
					str+=s.charAt(i);
				}
			}
		} else str="stringa non sostituibile";
		
		return str;
	}
	
	public static void main(String[] args) {
		
		
		
		System.out.println(contains("domodazzola","azz"));
		//System.out.println(indexOf("domodossola","la",8));

		
	}

}
