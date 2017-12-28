package hanaall;

import java.util.Date;

/**
 * Class holding information on a person.
 */
public class Message {

	public static final String MESSAGE_TYPE = "899A7B539BD19D044957";
	
	private String device;
	private Date sysTimestamp;
	private int dato;

	public Message (String device) {
		this.device = device;
	}
	
	public Message () {
	}
	
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Date getSysTimestamp() {
		return sysTimestamp;
	}

	public void setSysTimestamp(Date sysTimestamp) {
		this.sysTimestamp = sysTimestamp;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

}
