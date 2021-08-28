package models;
import entities.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

public class InvoiceModel {
	public boolean update(int itemid, double quantity ) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update item set quantity = quantity +? where id =?");
			preparedStatement.setDouble(1, quantity );
			preparedStatement.setInt(2, itemid);
			
			result = preparedStatement.executeUpdate() > 0;
			return result;
			
		} catch (Exception e) {
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}  

	
	public Integer CreateImportInvoice(ImportInvoice invoice) {
		Integer result = -1; 
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into import_invoice(created, account_id,manufacture_id) value(?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1,new java.sql.Date(invoice.getCreated().getTime()));
			preparedStatement.setInt(2, invoice.getAccountid());
			preparedStatement.setInt(3, invoice.getManufactureid());			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = null;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public Integer CreateExportInvoice(ExportInvoice invoice) {
		Integer result = -1; 
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into export_invoice(created, account_id,customer_id) value(?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1,new java.sql.Date(invoice.getCreated().getTime()));
			preparedStatement.setInt(2, invoice.getAccountid());
			preparedStatement.setInt(3, invoice.getCustomerid());			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = null;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public boolean ImportInvoiceDetail(ImportInvoiceDetail invoice) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into import_invoice_detail(quantity, item_id,importinvoice_id) value(?,?,?) ");
			preparedStatement.setDouble(1,invoice.getQuantity());
			preparedStatement.setInt(2, invoice.getItemid());
			preparedStatement.setInt(3, invoice.getImportinvoiceid());
			
			
			result = preparedStatement.executeUpdate() > 0;
			return result;
			
		} catch (Exception e) {
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}  
	public boolean ExportInvoiceDetail(ExportInvoiceDetail invoice) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into export_invoice_detail(quantity, item_id,exportinvoice_id) value(?,?,?) ");
			preparedStatement.setDouble(1,invoice.getQuantity());
			preparedStatement.setInt(2, invoice.getItemid());
			preparedStatement.setInt(3, invoice.getExportinvoiceid());
			
			
			result = preparedStatement.executeUpdate() > 0;
			return result;
			
		} catch (Exception e) {
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}  
	public Integer CreateReturnInvoice(ReturnInvoice invoice) {
		Integer result = -1; 
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into return_invoice(created, account_id,customer_id) value(?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1,new java.sql.Date(invoice.getCreated().getTime()));
			preparedStatement.setInt(2, invoice.getAccountid());
			preparedStatement.setInt(3, invoice.getCustomerid());			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = null;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public boolean ReturnInvoiceDetail(ReturnInvoiceDetail invoice) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into return_invoice_detail(quantity, item_id,returninvoice_id) value(?,?,?) ");
			preparedStatement.setDouble(1,invoice.getQuantity());
			preparedStatement.setInt(2, invoice.getItemid());
			preparedStatement.setInt(3, invoice.getReturninvoiceid());
			
			
			result = preparedStatement.executeUpdate() > 0;
			return result;
			
		} catch (Exception e) {
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}  
	public List<ExportInvoice> findAllExportInvoice() {
		List<ExportInvoice> exportInvoices = new ArrayList<ExportInvoice>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From export_invoice");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ExportInvoice exportInvoice = new ExportInvoice();
				exportInvoice.setId(resultSet.getInt("id"));
				exportInvoice.setCreated(resultSet.getDate("created"));
				exportInvoice.setCustomerid(resultSet.getInt("customer_id"));
				exportInvoice.setAccountid(resultSet.getInt("account_id"));
				exportInvoices.add(exportInvoice);
				
			}
		} catch (Exception e) {
			exportInvoices = null;
		} finally {
			ConnectDB.disconnect();
		}
		return exportInvoices;
	}
	
	public List<ReturnInvoice> findAllReturnInvoice() {
		List<ReturnInvoice> returnInvoices = new ArrayList<ReturnInvoice>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From return_invoice");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReturnInvoice returnInvoice = new ReturnInvoice();
				returnInvoice.setId(resultSet.getInt("id"));
				returnInvoice.setCreated(resultSet.getDate("created"));
				returnInvoice.setCustomerid(resultSet.getInt("customer_id"));
				returnInvoice.setAccountid(resultSet.getInt("account_id"));
				returnInvoices.add(returnInvoice);
				
			}
		} catch (Exception e) {
			returnInvoices = null;
		} finally {
			ConnectDB.disconnect();
		}
		System.out.println(returnInvoices.size());
		return returnInvoices;
	}
	public ReturnInvoice findReturnInvoiceById(int id) {
		ReturnInvoice returnInvoice = null;
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From return_invoice where id=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				returnInvoice = new ReturnInvoice();
				returnInvoice.setId(resultSet.getInt("id"));
				returnInvoice.setCreated(resultSet.getDate("created"));
				returnInvoice.setCustomerid(resultSet.getInt("customer_id"));
				returnInvoice.setAccountid(resultSet.getInt("account_id"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnInvoice = null;
			
		} finally {
			ConnectDB.disconnect();
		}
		
		return returnInvoice;
	}
	
	public List<ReturnInvoiceDetail> findReturnInvoiceDetail(ReturnInvoice returnInvoice) {
		List<ReturnInvoiceDetail> returnInvoiceDetails = new ArrayList<ReturnInvoiceDetail>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From return_invoice_detail where returninvoice_id=?");
			preparedStatement.setInt(1,returnInvoice.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReturnInvoiceDetail returnInvoiceDetail = new ReturnInvoiceDetail();
				returnInvoiceDetail.setQuantity(resultSet.getDouble("quantity"));
				returnInvoiceDetail.setItemid(resultSet.getInt("item_id"));
				returnInvoiceDetails.add(returnInvoiceDetail);
				
			}
		} catch (Exception e) {
			returnInvoiceDetails = null;
		} finally {
			ConnectDB.disconnect();
		}
		return returnInvoiceDetails;
	}
	
	public List<ImportInvoice> findAllImportInvoice() {
		List<ImportInvoice> importInvoices = new ArrayList<ImportInvoice>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From import_invoice");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ImportInvoice importInvoice = new ImportInvoice();
				importInvoice.setId(resultSet.getInt("id"));
				importInvoice.setCreated(resultSet.getDate("created"));
				importInvoice.setManufactureid(resultSet.getInt("manufacture_id"));
				importInvoice.setAccountid(resultSet.getInt("account_id"));
				importInvoices.add(importInvoice);
				
			}
		} catch (Exception e) {
			importInvoices = null;
		} finally {
			ConnectDB.disconnect();
		}
		return importInvoices;
	}
	public ImportInvoice findImportInvoiceById(int id) {
		ImportInvoice importInvoice = null;
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From import_invoice where id=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				importInvoice = new ImportInvoice();
				importInvoice.setId(resultSet.getInt("id"));
				importInvoice.setCreated(resultSet.getDate("created"));
				importInvoice.setManufactureid(resultSet.getInt("manufacture_id"));
				importInvoice.setAccountid(resultSet.getInt("account_id"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			importInvoice = null;
			
		} finally {
			ConnectDB.disconnect();
		}
		
		return importInvoice;
	}
	
	public List<ImportInvoiceDetail> findImportInvoiceDetail(ImportInvoice importInvoice) {
		List<ImportInvoiceDetail> importInvoiceDetails = new ArrayList<ImportInvoiceDetail>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From import_invoice_detail where importinvoice_id=?");
			preparedStatement.setInt(1,importInvoice.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ImportInvoiceDetail importInvoiceDetail = new ImportInvoiceDetail();
				importInvoiceDetail.setQuantity(resultSet.getDouble("quantity"));
				importInvoiceDetail.setItemid(resultSet.getInt("item_id"));
				importInvoiceDetails.add(importInvoiceDetail);
				
			}
		} catch (Exception e) {
			importInvoiceDetails = null;
		} finally {
			ConnectDB.disconnect();
		}
		System.out.println(importInvoiceDetails.size());
		return importInvoiceDetails;
	}
	
	public ExportInvoice findExportInvoiceById(int id) {
		ExportInvoice exportInvoice = null;
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From export_invoice where id=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				exportInvoice = new ExportInvoice();
				exportInvoice.setId(resultSet.getInt("id"));
				exportInvoice.setCreated(resultSet.getDate("created"));
				exportInvoice.setCustomerid(resultSet.getInt("customer_id"));
				exportInvoice.setAccountid(resultSet.getInt("account_id"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			exportInvoice = null;
			
		} finally {
			ConnectDB.disconnect();
		}
		
		return exportInvoice;
	}
	public List<ExportInvoiceDetail> findExportInvoiceDetail(ExportInvoice exportInvoice) {
		List<ExportInvoiceDetail> exportInvoiceDetails = new ArrayList<ExportInvoiceDetail>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From export_invoice_detail where exportinvoice_id=?");
			preparedStatement.setInt(1,exportInvoice.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ExportInvoiceDetail exportInvoiceDetail = new ExportInvoiceDetail();
				exportInvoiceDetail.setQuantity(resultSet.getDouble("quantity"));
				exportInvoiceDetail.setItemid(resultSet.getInt("item_id"));
				exportInvoiceDetails.add(exportInvoiceDetail);
				
			}
		} catch (Exception e) {
			exportInvoiceDetails = null;
		} finally {
			ConnectDB.disconnect();
		}
		System.out.println(exportInvoiceDetails.size());
		return exportInvoiceDetails;
	}
	
	public List<ExportInvoiceDetail> findExportInvoiceByItem(Item item) {
		List<ExportInvoiceDetail> exportInvoiceDetails = new ArrayList<ExportInvoiceDetail>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From export_invoice_detail where item_id=?");
			preparedStatement.setInt(1,item.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ExportInvoiceDetail exportInvoiceDetail = new ExportInvoiceDetail();
				exportInvoiceDetail.setExportinvoiceid(resultSet.getInt("exportinvoice_id"));
				exportInvoiceDetail.setQuantity(resultSet.getDouble("quantity"));
				exportInvoiceDetail.setItemid(resultSet.getInt("item_id"));
				exportInvoiceDetails.add(exportInvoiceDetail);
				
			}
		} catch (Exception e) {
			exportInvoiceDetails = null;
		} finally {
			ConnectDB.disconnect();
		}
		System.out.println(exportInvoiceDetails.size());
		return exportInvoiceDetails;
	}
	
	public List<ImportInvoiceDetail> findImportInvoiceDetailByItem(Item item) {
		List<ImportInvoiceDetail> importInvoiceDetails = new ArrayList<ImportInvoiceDetail>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From import_invoice_detail where item_id=?");
			preparedStatement.setInt(1,item.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ImportInvoiceDetail importInvoiceDetail = new ImportInvoiceDetail();
				importInvoiceDetail.setImportinvoiceid(resultSet.getInt("importinvoice_id"));
				importInvoiceDetail.setQuantity(resultSet.getDouble("quantity"));
				importInvoiceDetail.setItemid(resultSet.getInt("item_id"));
				importInvoiceDetails.add(importInvoiceDetail);
				
			}
		} catch (Exception e) {
			importInvoiceDetails = null;
		} finally {
			ConnectDB.disconnect();
		}
		System.out.println(importInvoiceDetails.size());
		return importInvoiceDetails;
	}
	
	public List<ReturnInvoiceDetail> findReturnInvoiceDetailByItem(Item item) {
		List<ReturnInvoiceDetail> returnInvoiceDetails = new ArrayList<ReturnInvoiceDetail>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From return_invoice_detail where item_id=?");
			preparedStatement.setInt(1,item.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ReturnInvoiceDetail returnInvoiceDetail = new ReturnInvoiceDetail();
				returnInvoiceDetail.setReturninvoiceid(resultSet.getInt("returninvoice_id"));
				returnInvoiceDetail.setQuantity(resultSet.getDouble("quantity"));
				returnInvoiceDetail.setItemid(resultSet.getInt("item_id"));
				returnInvoiceDetails.add(returnInvoiceDetail);
				
			}
		} catch (Exception e) {
			returnInvoiceDetails = null;
		} finally {
			ConnectDB.disconnect();
		}
		return returnInvoiceDetails;
	}
	
	public List<ImportInvoice> findImportInvoiceByMacnufacture(Manufacture manufacture) {
		List<ImportInvoice> importInvoices = new ArrayList<ImportInvoice>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From import_invoice where manufacture_id=?");
			preparedStatement.setInt(1,manufacture.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ImportInvoice importInvoice = new ImportInvoice();
				importInvoice.setId(resultSet.getInt("id"));
				importInvoice.setCreated(resultSet.getDate("created"));
				importInvoice.setAccountid(resultSet.getInt("account_id"));
				importInvoice.setManufactureid(resultSet.getInt("manufacture_id"));
				importInvoices.add(importInvoice);
				
			}
		} catch (Exception e) {
			importInvoices = null;
		} finally {
			ConnectDB.disconnect();
		}
		
		return importInvoices;
	}
}
