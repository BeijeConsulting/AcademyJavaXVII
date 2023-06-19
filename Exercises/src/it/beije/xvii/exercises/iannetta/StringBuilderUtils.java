package it.beije.xvii.exercises.iannetta;

public class StringBuilderUtils {

	public static StringBuilder append(StringBuilder sb, String str) {
		String s = sb.toString() + str;
		return new StringBuilder(s);
	}
	
	public static StringBuilder insert(StringBuilder sb, int offset, String str) throws Exception {
		String s = sb.toString();
		return new StringBuilder(StringUtils.substring(s, 0, offset) + str + StringUtils.substring(s, offset));

	}

	public static StringBuilder delete(StringBuilder sb, int start, int end) throws Exception {
		String s = sb.toString();
		return new StringBuilder(StringUtils.substring(s, 0, start) + StringUtils.substring(s, end));
	}

	public static StringBuilder deleteCharAt(StringBuilder sb, int start) throws Exception {
		return delete(sb, start, start + 1);
	}
	
	public static StringBuilder reverse(StringBuilder sb) throws Exception {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < sb.length(); i++) {
			String sToAdd = sb.charAt(i) + "";
			result = append(new StringBuilder(sToAdd), result.toString());
		}
		return new StringBuilder(result);
	}
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder("THIS is a stRing to TesT meThods   ");
		System.out.println(sb.toString());
		
		String stringToAppend = "addddddd";
		System.out.println("\nAdd: " + stringToAppend);
		System.out.println("My method: " + append(sb, stringToAppend));
		System.out.println("String method: " + sb.append(stringToAppend));
		
		String stringToInsert = "Add";
		int index = 0;
		System.out.println("\nInsert " + stringToInsert + " from index " + index);
		System.out.println("My method: " + insert(sb, index, stringToInsert));
		System.out.println("String method: " + sb.insert(index, stringToInsert));


		int fromIndex = 6;
		int toIndex = 15;
		System.out.println("\nDelete from index " + fromIndex + " to index " + toIndex);
		System.out.println("My method: " + delete(sb, fromIndex, toIndex));
		System.out.println("String method: " + sb.delete(fromIndex, toIndex));
		
		index = 7;
		System.out.println("\nDelete char at index " + index);
		System.out.println("My method: " + deleteCharAt(sb, index));
		System.out.println("String method: " + sb.deleteCharAt(index));
		
		System.out.println("\nReverse ");
		System.out.println("My method: " + reverse(sb));
		System.out.println("String method: " + sb.reverse());
		
	}

}
