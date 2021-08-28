package entities;

public class ExportInvoiceDetail {

	private double quantity;	
	private int itemid;
	private int exportinvoiceid;
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
	public int getExportinvoiceid() {
		return exportinvoiceid;
	}
	public void setExportinvoiceid(int exportinvoiceid) {
		this.exportinvoiceid = exportinvoiceid;
	}	


}
