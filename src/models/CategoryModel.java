package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Category;
import models.ConnectDB;

public class CategoryModel {

	public List<Category> findAll() {
		List<Category> categories = new ArrayList<Category>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From category");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			
			}
		} catch (Exception e) {
			categories = null;
		} finally {
			ConnectDB.disconnect();
		}
		return categories;
	}
	public boolean Create(Category category) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("insert into category (name) values (?)");
			preparestatement.setString(1,category.getName());			
			result = preparestatement.executeUpdate() > 0;
					} catch (Exception e) {
						System.out.print("loi o day");
			return false;
		}
		finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	
	public Category findId(int id) {
		Category category = null;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from category where id = ?");
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				category = new Category();
				category.setId(resultset.getInt("id"));
				category.setName(resultset.getString("name"));	
				
			}	
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return category;
	}
	
	public boolean update(Category category) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("update category set name = ? where id =?");
			preparestatement.setString(1,category.getName());
			preparestatement.setInt(2, category.getId());
			result = preparestatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	public boolean deleted(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("delete from category where id =?");
			preparedstatement.setInt(1,id);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
}
