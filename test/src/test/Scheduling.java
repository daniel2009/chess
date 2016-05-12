package test;
import java.util.*;

public class Scheduling {
	/*
	static final int adNum = 10;
	private Map<Integer, Integer> valueMap;
	private Map<Integer, Content> isScheduled;
	
	public Scheduling() {
		isScheduled = new HashMap<Integer, Content>();
		valueMap = new HashMap<Integer, Integer>();
		for (int i = 1; i <= adNum; i++) {
			valueMap.put(id[i - 1], value[i - 1]);
		}
		
	}
	
	public List<Content> reschedule(int[][] input) {
		
		List<Content> contentList = inputDataToContent(input);
		
		//modifyOverlapByCutting(contentList);
		modifyOverlapByDeleting(contentList);
		Collections.sort(contentList, listComparator);
		isScheduled.clear();
		List<Edge> edgeList = contentToEdge(contentList);
		Collections.sort(edgeList, edgeComparator);
		List<Content> scheduledList = removeRedundancy(contentList, edgeList);
		return scheduledList;
	}
	
	private List<Content> inputDataToContent(int[][] input) {
		List<Content> contentList = new ArrayList<Content>();
		for (int[] inputList : input) {
			int id = inputList[0];
			int startTime = inputList[1];
			int endTime = inputList[2];
			contentList.add(new Content(id, startTime, endTime));
		}
		return contentList;
	}
	
	
	private List<Edge> contentToEdge(List<Content> contentList) {
		List<Edge> edgeList = new ArrayList<Edge>();
		for (Content c : contentList) {
			edgeList.add(new Edge(c.id, c.startTime, true, c.value));
			edgeList.add(new Edge(c.id, c.endTime, false, c.value));
		}
		return edgeList;
	}
	
	private List<Content> removeRedundancy(List<Content> contentList, List<Edge> edgeList) {
		int countContent = 0;
		List<Content> result = new ArrayList<Content>();
		int contentIndex = 0;
		for (Edge e : edgeList) {
			if (e.isStart) {
				if (countContent < 3) {
					countContent++;
					result.add(contentList.get(contentIndex++));
				} else {
					contentIndex++;
				}
			} else {
				countContent--;
			}
		}
		return result;
	}
	
	private void modifyOverlapByDeleting(List<Content> contentList) {
		for (int i = 0; i < contentList.size(); i++) {
			Content c = contentList.get(i);
			if (isScheduled.get(c.id) == null) {
				isScheduled.put(c.id, c);
			} else {
				Content dup = isScheduled.get(c.id);
				if (c.endTime < dup.endTime) {
					contentList.remove(c);
				}
				else if (c.startTime < dup.endTime){
					isScheduled.put(c.id, new Content(c.id, dup.startTime, c.endTime, dup.value));
					contentList.remove(i);
				} else {
					isScheduled.put(c.id, c);
				}
			}
		}
	}
	
	private void modifyOverlapByCutting(List<Content> contentList) {
		for (int i = 0; i < contentList.size(); i++) {
			Content c = contentList.get(i);
			if (isScheduled.get(c.id) == null) {
				isScheduled.put(c.id, c);
			} else {
				Content dup = isScheduled.get(c.id);
				if (c.endTime < dup.endTime) {
					contentList.remove(c);
				}
				else if (c.startTime < dup.endTime){
					isScheduled.put(c.id, new Content(c.id, dup.startTime, c.endTime, dup.value));
					contentList.set(i, new Content(c.id, dup.endTime, c.endTime, c.value));
				} else {
					isScheduled.put(c.id, c);
				}
			}
		}
	}
	
	public Comparator<Content> listComparator = new Comparator<Content>(){
		public int compare(Content c1, Content c2) {
			return c1.startTime - c2.startTime;
		}
	};
	
	public Comparator<Edge> edgeComparator = new Comparator<Edge>(){
		public int compare(Edge e1, Edge e2) {
			if (e1.timeStamp != e2.timeStamp) {
				return e1.timeStamp - e2.timeStamp;
			}
			else if (e1.isStart && e2.isStart) {
				return e1.value - e2.value;
			}
			else if (!e1.isStart && !e2.isStart) {
				return e2.value - e1.value;
			} else {
				return e1.isStart ? 1 : -1;
			}
		}
		
	};
	*/
}

class Ad {
	int id;
	int value;
	public Ad() {}
}

class Content {
	int id;
	int startTime;
	int endTime;
	public Content(int id, int startTime, int endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}

class Edge {
	int id;
	boolean isStart;
	int timeStamp;
	int value;
	public Edge(int id, int timeStamp, boolean isStart, int value) {
		this.id = id;
		this.isStart = isStart;
		this.timeStamp = timeStamp;
		this.value = value;
	}
}
