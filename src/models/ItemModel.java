package models;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Item;

import models.ConnectDB;

public class ItemModel {
	
	public List<Item> findItemByUnitId(int unitid){
		List<Item> items = new ArrayList<Item>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from item where unit_id=?");
			preparedstatement.setInt(1,unitid);
			ResultSet result = preparedstatement.executeQuery();
			while(result.next()) {
				Item item = new Item();
				item.setId(result.getInt("id"));
				item.setName(result.getString("name"));
				item.setUnitid(result.getInt("unit_id"));
				item.setPrice(result.getDouble("price"));
				item.setQuantity(result.getDouble("quantity"));
				item.setCategoryid(result.getInt("category_id"));
				item.setManufactureid(result.getInt("manufacture_id"));
				item.setCreated(result.getDate("created"));
				items.add(item);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnectDB.disconnect();
		}
		return items;
	}

	public List<Item> findAll(){
		List<Item> items = new ArrayList<Item>();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From item");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getInt("id"));
				item.setName(resultSet.getString("name"));
				item.setPrice(resultSet.getDouble("price"));
				item.setQuantity(resultSet.getInt("quantity"));
				item.setUnitid(resultSet.getInt("unit_id"));
				item.setDescription(resultSet.getString("description"));
				item.setCreated(resultSet.getDate("created"));
				item.setManufactureid(resultSet.getInt("manufacture_id"));
				item.setCategoryid(resultSet.getInt("category_id"));
				items.add(item);
			}
		
		} catch (Exception e) {
			items = null;
		} finally {
			ConnectDB.disconnect();
		}
		return items;
	
	}
	
	public List<Item> findAllInCategory(int categoryid){
		List<Item> items = new ArrayList<Item>();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From item where category_id =?");
			preparedStatement.setInt(1, categoryid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getInt("id"));
				item.setName(resultSet.getString("name"));
				item.setPrice(resultSet.getDouble("price"));
				item.setQuantity(resultSet.getInt("quantity"));
				item.setUnitid(resultSet.getInt("unit_id"));
				item.setDescription(resultSet.getString("description"));
				item.setCreated(resultSet.getDate("created"));
				item.setCategoryid(resultSet.getInt("category_id"));
				items.add(item);
			}
		
		} catch (Exception e) {
			items = null;
		} finally {
			ConnectDB.disconnect();
		}
		return items;
	
	}
	
	public List<Item> findCategoryId(int category_id){
		List<Item> items = new ArrayList<Item>();
		
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM item WHERE category_id = ?");
	
			preparedstatement.setInt(1, category_id);
			
			ResultSet result = preparedstatement.executeQuery();
			while(result.next()) {
				Item item = new Item();
				item.setId(result.getInt("id"));
				item.setName(result.getString("name"));
				item.setUnitid(result.getInt("unit_id"));
				item.setPrice(result.getDouble("price"));
				item.setQuantity(result.getDouble("quantity"));
				item.setCategoryid(result.getInt("category_id"));
				item.setManufactureid(result.getInt("manufacture_id"));
				item.setCreated(result.getDate("created"));
				items.add(item);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		} finally {
			ConnectDB.disconnect();
		}
		return items;
	}
	
	public List<Item> findName(String name){
		List<Item> items = new ArrayList<Item>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from item where name like ?");
			preparedstatement.setString(1, "%" + name + "%");
			ResultSet result = preparedstatement.executeQuery();
			while(result.next()) {
				Item item = new Item();
				item.setId(result.getInt("id"));
				item.setName(result.getString("name"));
				item.setUnitid(result.getInt("unit_id"));
				item.setPrice(result.getDouble("price"));
				item.setQuantity(result.getDouble("quantity"));
				item.setCategoryid(result.getInt("category_id"));
				item.setManufactureid(result.getInt("manufacture_id"));
				item.setCreated(result.getDate("created"));
				items.add(item);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnectDB.disconnect();
		}
		return items;
	}

	public Item findId(int id){
		Item item = null;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from item where id = ?");
			preparedstatement.setInt(1,id);
			ResultSet result = preparedstatement.executeQuery();
			while(result.next()) {
				item = new Item();
				item.setId(result.getInt("id"));
				item.setName(result.getString("name"));
				item.setUnitid(result.getInt("unit_id"));
				item.setPrice(result.getDouble("price"));
				item.setQuantity(result.getDouble("quantity"));
				item.setCategoryid(result.getInt("category_id"));
				item.setManufactureid(result.getInt("manufacture_id"));
				item.setCreated(result.getDate("created"));
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnectDB.disconnect();
		}
		return item;
	}
	public boolean Create(Item item) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("insert into item (name, unit_id, price, quantity, category_id, manufacture_id) values (?,?,?,?,?,?)");
			preparestatement.setString(1,item.getName());
			preparestatement.setInt(2,item.getUnitid());
			preparestatement.setDouble(3, item.getPrice());
			preparestatement.setDouble(4,item.getQuantity());
			preparestatement.setInt(5, item.getCategoryid());
			preparestatement.setInt(6,item.getManufactureid());
			//preparestatement.setDate(7,new java.sql.Date(item.getCreated().getTime()));
			
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
	
	public boolean deleted(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("delete from item where id =?");
			preparedstatement.setInt(1,id);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public boolean update(Item item) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("update item set name = ?, unit_id = ?, category_id = ? where id =?");
			preparestatement.setString(1,item.getName());
			preparestatement.setInt(2,item.getUnitid());
			preparestatement.setInt(3,item.getCategoryid());
			preparestatement.setInt(4,item.getId());
			result = preparestatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	

	public Item FindItemById(int id){
		Item item = new Item();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From item where  id =?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				item.setId(resultSet.getInt("id"));
				item.setName(resultSet.getString("name"));
				item.setPrice(resultSet.getDouble("price"));
				item.setQuantity(resultSet.getInt("quantity"));
				item.setUnitid(resultSet.getInt("unit_id"));
				item.setDescription(resultSet.getString("description"));
				item.setCreated(resultSet.getDate("created"));
				item.setCategoryid(resultSet.getInt("category_id"));
			
			}
		
		} catch (Exception e) {
			item = null;
		} finally {
			ConnectDB.disconnect();
		}
		return item;
	
	}
}
