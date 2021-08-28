package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.ExceptionItem;
import entities.Item;


public class ExceptionItemModel {

	public List<ExceptionItem> findAll(){
		List<ExceptionItem> exceptions = new ArrayList<ExceptionItem>();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From exception");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ExceptionItem exception = new ExceptionItem();
				exception.setId(resultSet.getInt("id"));
				exception.setItem_id(resultSet.getInt("item_id"));
				exception.setContent(resultSet.getString("content"));
				exception.setStatus(resultSet.getBoolean("status"));
				
				exceptions.add(exception);
			}
		
		} catch (Exception e) {
			exceptions = null;
		} finally {
			ConnectDB.disconnect();
		}
		return exceptions;
	
	}
	public boolean Create(ExceptionItem exception) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("insert into exception (item_id, content , status) values (?,?,?)");
			preparestatement.setInt(1,exception.getItem_id());
			preparestatement.setString(2, exception.getContent());
			preparestatement.setBoolean(3,exception.isStatus());
			result = preparestatement.executeUpdate() > 0;
					} catch (Exception e) {
						System.out.print(e.getMessage());
			return false;
		}
		finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public boolean Delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("delete from exception where item_id =?");
			preparedstatement.setInt(1,id);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
		
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
}
