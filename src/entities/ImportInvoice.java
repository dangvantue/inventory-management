package entities;

import java.util.Date;

public class ImportInvoice {

	private int id;
	private int accountid;
	private Date created;
	private int manufactureid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getManufactureid() {
		return manufactureid;
	}
	public void setManufactureid(int manufactureid) {
		this.manufactureid = manufactureid;
	}
	

}
