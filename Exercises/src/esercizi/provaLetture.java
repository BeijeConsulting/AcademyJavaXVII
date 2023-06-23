package esercizi;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class provaLetture {

	public static void main(String[] args) throws Exception{
		File f = new File("C:\\Users\\Padawan\\Desktop\\azienda.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		List<String> l1 = new ArrayList<>();
		while(br.ready()) {
			String r = br.readLine();
			l1.add(r);
		}
		
		for(String s : l1) {
			StringTokenizer tok = new StringTokenizer(s, " ");
			while(tok.hasMoreElements()) {
				System.out.println(tok.nextToken());
				
			}
		}
		
	}

}
