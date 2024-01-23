package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {
	
	private String state1ab;
	private int state1no;
	private String state2ab;
	private int state2no;
	private int year;
	private int conttype;
	
	public Border(String state1ab, int state1no, String state2ab, int state2no, int year, int conttype) {
		super();
		this.state1ab = state1ab;
		this.state1no = state1no;
		this.state2ab = state2ab;
		this.state2no = state2no;
		this.year = year;
		this.conttype = conttype;
	}
	
	public String getState1ab() {
		return state1ab;
	}
	
	public int getState1no() {
		return state1no;
	}
	
	public String getState2ab() {
		return state2ab;
	}
	
	public int getState2no() {
		return state2no;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getConttype() {
		return conttype;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(conttype, state1ab, state1no, state2ab, state2no, year);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		return conttype == other.conttype && Objects.equals(state1ab, other.state1ab) && state1no == other.state1no
				&& Objects.equals(state2ab, other.state2ab) && state2no == other.state2no && year == other.year;
	}
	
	
	
}
	
	