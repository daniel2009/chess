package test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Apr07 {
	public void deleteDup(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}
		System.out.println(set.toString());
	}
	
	class Star {
		int x; 
		int y;
		int z;
		public Star(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public int findSquare_Distance() {
			return this.x * this.x + this.y * this.y + this.z * this.z;
		}
	}
	
	public Star[] findNearStar(String path) {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		Set<Star> stars = new HashSet<Star>(); 
		try {
			file = new File(path);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(" ");
				Star star = new Star(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				stars.add(star);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Queue<Star> res = new PriorityQueue<Star>(100, distanceComparator);
		for (Star star : stars) {
			if (res.size() < 100) {
				res.offer(star);
			} else {
				Star top = res.peek();
				if (top.findSquare_Distance() > star.findSquare_Distance()) {
					res.poll();
					res.offer(star);
				}
			}
		}
		return (Star[]) res.toArray();
	}
	
	
	public Comparator<Star> distanceComparator = new Comparator<Star>() {
		public int compare(Star s1, Star s2) {
			return s2.findSquare_Distance() - s1.findSquare_Distance();
		}
	};
	
	public List<String> findPhoneNumber(String path) {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			file = new File(path);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		List<String> res = new ArrayList<String>();
		String regex = "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sb.toString());
		while (matcher.find()) {
			res.add(sb.toString().substring(matcher.start(), matcher.end()));
		}
		return res;
	}
	
	
	
}
