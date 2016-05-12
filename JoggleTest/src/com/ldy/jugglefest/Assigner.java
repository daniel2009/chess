package com.ldy.jugglefest;

import java.util.*;

public class Assigner {
	private List<Circuit> circuits;
	private List<Juggler> jugglers;
	
	public int num_circuit;
	public int num_juggler;
	public int volume_circuit;
	
	public Assigner(ResultType rt) {
		this.circuits = rt.circuits;
		this.jugglers = rt.jugglers;
		this.num_circuit = this.circuits.size();
		this.num_juggler = this.jugglers.size();
		this.volume_circuit = this.num_juggler / this.num_circuit;
	}
	
	public void assign() {
		
		Random random = new Random(System.currentTimeMillis());
		boolean stop = false;
		while (!stop) {
			stop = true;
			for (int i = 0; i < this.num_juggler; i++) {
				
				Juggler juggler = jugglers.get(i);
				if (!juggler.isAssigned) {
					
					// Try to assign jugglers to the circuit which they prefer as the order in the preference.
					// If the circuit is not full, just add it. Or this juggler will get a larger score than someone in the 
					// circuit, replace the last one and sort again.
					int priority = juggler.cid_rank_in_preference + 1;
					int[] preference_cid = juggler.getPreferenceCid();
					int[] score = juggler.getScores();
					
					while (priority < preference_cid.length) {
						int curcid = preference_cid[priority];
						int curscore = score[priority];
						Circuit c = circuits.get(curcid);
						juggler.curscore = curscore;
						juggler.curcid = c.getCid();
						if (c.jugglers.size() < this.volume_circuit) {
							c.addJuggler(juggler);
							juggler.isAssigned = true;
							
							break;
						} else if (curscore > c.minScore) {
							 Juggler removed_juggler = c.removeJuggler();
							 removed_juggler.isAssigned = false;
							 c.addJuggler(juggler);
							 juggler.isAssigned = true;
							 // someone is not assigned, it has to keep loop
							 stop = false;
							 break;
						} else {
							priority++;
						}
					}
					
					// if the jugglers can not be assigned in the circuits they prefer, judge the randomly selected circuit and the
					// juggler. If this circuit is not full, just add it. Or this juggler can get a higher score in this circuit, 
					// replace the last one.
					if (juggler.isAssigned == false) {
						while (true) {
							Circuit randomCircuit = this.circuits.get(random.nextInt(2000));
							juggler.curcid = randomCircuit.getCid();
							juggler.curscore = this.findScore(juggler, randomCircuit);
							if (randomCircuit.jugglers.size() < this.volume_circuit) {
								randomCircuit.addJuggler(juggler);
								juggler.isAssigned = true;
								break;
							} else if (this.findScore(juggler, randomCircuit) > randomCircuit.minScore) {
								Juggler removed_juggler = randomCircuit.removeJuggler();
								removed_juggler.isAssigned = false;
								stop = false;
								juggler.isAssigned = true;
								randomCircuit.addJuggler(juggler);
								// someone is not assigned, it has to keep loop
								stop = false;
								break;
							}
						}
					}
				}
			}
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = this.circuits.size() - 1; i >= 0; i--) {
			Circuit c = this.circuits.get(i);
			sb.append(c );
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public int findScore(Juggler j, Circuit c) {
		return j.getE_val() * c.getE_val() + j.getH_val() * c.getH_val() + j.getP_val() * c.getP_val();
	}
}
