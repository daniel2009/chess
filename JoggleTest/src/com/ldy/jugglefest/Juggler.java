package com.ldy.jugglefest;

public class Juggler {
	private int jid;
	private int h_val;
	private int e_val;
	private int p_val;
	private int[] preferenceCid;
	private int[] scores;
	
	public boolean isAssigned;
	public int curcid;
	public int curscore;
	public int cid_rank_in_preference;
	
	public Juggler(int jid, int h_val, int e_val, int p_val, int[] preferenceCid, int[] scores) {
		super();
		this.jid = jid;
		this.h_val = h_val;
		this.e_val = e_val;
		this.p_val = p_val;
		this.preferenceCid = preferenceCid;
		this.scores = scores;
		this.isAssigned = false;
		
		// if assigned, id of the assigned circuit
		this.curcid = -1;          
		
		// if assigned, the score which the juggler can obtain
		this.curscore = -1;
		
		// if assigned, rank of the assigned circuit in the juggler's preference
		this.cid_rank_in_preference = -1;
	}
	
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
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
	public int[] getPreferenceCid() {
		return preferenceCid;
	}
	public void setPreferenceCid(int[] preferenceCid) {
		this.preferenceCid = preferenceCid;
	}
	public int[] getScores() {
		return scores;
	}
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("J" + this.jid);
		
		for (int i = 0; i < this.preferenceCid.length; i++) {
			sb.append(" C" + this.preferenceCid[i] + ":" + this.scores[i]);
		}
		return sb.toString();
	}
}
