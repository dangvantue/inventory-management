package entities;

public class ReturnInvoiceDetail {

	private double quantity;
	private int itemid;
	private int returninvoiceid;
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getReturninvoiceid() {
		return returninvoiceid;
	}
	public void setReturninvoiceid(int returninvoiceid) {
		this.returninvoiceid = returninvoiceid;
	}
	
}
