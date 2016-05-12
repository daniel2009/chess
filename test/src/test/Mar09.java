package test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Mar09 {
	class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}
	
	public ListNode reverseListRecursive(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseListRecursive(head.next);
		head = head.next.next;
		head.next = null;
		return newHead;
	}
	
	public void StringToJson() {
		String str = "{\"test\": \"result of test\"}";
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(str);
			String test = (String)json.get("test");
			System.out.println(test);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class Test {
		String test;
		public Test(String test) {
			this.test = test;
		}
		public void settest(String test) {
			this.test = test;
		}
		public String gettest() {
			return this.test;
		}
	}
}
