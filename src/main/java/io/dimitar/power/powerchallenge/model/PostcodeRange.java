package io.dimitar.power.powerchallenge.model;

/**
 * Request entity for batteries within a postcode range
 * 
 */
public class PostcodeRange {

	private int start;
	private int end;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
