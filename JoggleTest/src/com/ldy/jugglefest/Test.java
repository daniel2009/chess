package com.ldy.jugglefest;

import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		
		// Read data 
		String path = "/Users/kaneki/Desktop/jugglefest.txt";
		ResultType rt = Test.readData(path);
		Assigner assigner = new Assigner(rt);
		
		assigner.assign();
		
		// Write data
		FileOutputStream fos = new FileOutputStream("output.txt");
		Writer writer = new OutputStreamWriter(fos);
		try {
			writer.write(assigner.toString());
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}
		int sum = 0;
		for (Juggler juggler : rt.circuits.get(1970).jugglers) {
			sum += juggler.getJid();
		}
		System.out.println(sum);
 	}
	
	public static ResultType readData(String path) {
		BufferedReader br = null;
		ResultType rt = new ResultType();
		try {
			String sCurLine = "";
			br = new BufferedReader(new FileReader(path));
			while ((sCurLine = br.readLine()) != null) {
				String[] str = sCurLine.split(" ");
				if ("C".equals(str[0])) {
					int cid = Integer.parseInt(str[1].substring(1));
					int h_val = Integer.parseInt(str[2].substring(2));
					int e_val = Integer.parseInt(str[3].substring(2));
					int p_val = Integer.parseInt(str[4].substring(2));
					Circuit cur = new Circuit(cid, h_val, e_val, p_val);
					rt.circuits.add(cur);
				} else if ("J".equals(str[0])){
					int jid = Integer.parseInt(str[1].substring(1));
					int h_val = Integer.parseInt(str[2].substring(2));
					int e_val = Integer.parseInt(str[3].substring(2));
					int p_val = Integer.parseInt(str[4].substring(2));
					String[] preference = str[5].split(",");
					int[] preferenceCid = new int[preference.length];
					int[] scores = new int[preference.length];
					for (int i = 0; i < preference.length; i++) {
						preferenceCid[i] = Integer.parseInt(preference[i].substring(1));
						scores[i] = h_val * rt.circuits.get(preferenceCid[i]).getH_val() 
								+ e_val * rt.circuits.get(preferenceCid[i]).getE_val() 
								+ p_val * rt.circuits.get(preferenceCid[i]).getP_val();
					}
					Juggler juggler = new Juggler(jid, h_val, e_val, p_val, preferenceCid, scores);
					rt.jugglers.add(juggler);				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return rt;
	}
}

