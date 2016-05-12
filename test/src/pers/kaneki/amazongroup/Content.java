package pers.kaneki.amazongroup;

public class Content {
	int id;
	int startTime;
	int endTime;
	int duration;
	int location;
	
	public Content(int id, int duration) {
		this.id = id;
		this.duration = duration;
	}
	
	public Content(int id, int location, int startTime, int endTime) {
		this.id =id;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
