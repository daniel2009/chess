package com.ldy.jugglefest;

import java.util.*;

/**
 *  
 * @author kaneki
 * Since there will be two lists after reading, I use a class to take them.
 */

public class ResultType {
	List<Juggler> jugglers;
	List<Circuit> circuits;
	
	public ResultType() {
		jugglers = new ArrayList<Juggler>();
		circuits = new ArrayList<Circuit>();
	}
}
