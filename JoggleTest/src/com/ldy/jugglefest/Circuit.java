package com.ldy.jugglefest;

import java.util.*;

public class Circuit {
	private int cid;
	private int h_val;
	private int e_val;
	private int p_val;
	
	public List<Juggler> jugglers;
	public int minScore;
	
	public Circuit(int cid, int h_val, int e_val, int p_val) {
		this.cid = cid;
		this.h_val = h_val;
		this.e_val = e_val;
		this.p_val = p_val;
		this.jugglers = new ArrayList<Juggler>();
		this.minScore = Integer.MAX_VALUE;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getH_val() {
		return h_val;
	}
	public void setH_val(int h_val) {
		this.h_val = h_val;
	}
	public int getE_val() {
		return e_val;
	}
	public void setE_val(int e_val) {
		this.e_val = e_val;
	}
	public int getP_val() {
		return p_val;
	}
	public void setP_val(int p_val) {
		this.p_val = p_val;
	}
	
	public void addJuggler(Juggler j) {
		this.jugglers.add(j);
		sortJuggler();
		this.minScore = this.jugglers.get(this.jugglers.size() - 1).curscore;
	}
	
	public void sortJuggler() {
		Collections.sort(this.jugglers, new Comparator<Juggler>(){
			public int compare(Juggler j1, Juggler j2) {
				if (j1.curscore < j2.curscore) {
					return 1;
				} else if (j1.curscore > j2.curscore) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
	
	public Juggler removeJuggler() {
		if (this.jugglers.size() > 1) {
			this.minScore = this.jugglers.get(this.jugglers.size() - 2).curscore;
		}
		return this.jugglers.remove(this.jugglers.size() - 1);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("C" + this.cid + " ");
		
		for (int i = 0; i < this.jugglers.size(); i++) {
			if (i != this.jugglers.size() - 1) {
				sb.append(this.jugglers.get(i) + ",");
			} else {
				sb.append(this.jugglers.get(i));
			}
		}
		return sb.toString();
	}
}
