package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.Manufacture;



public class CustomerModel {
	
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<Customer>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From Customer");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setName(resultSet.getString("name"));
				customers.add(customer);
			
			}
		} catch (Exception e) {
			customers = null;
		} finally {
			ConnectDB.disconnect();
		}
		return customers;
	}

	public Customer FindCustomerById(int id){
		Customer customer = new Customer();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * from customer where  id =?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer.setId(resultSet.getInt("id"));
				customer.setName(resultSet.getString("name"));		
			}
		
		} catch (Exception e) {
			customer = null;
		} finally {
			ConnectDB.disconnect();
		}
		return customer;
	
	}
	public boolean CreateCustomer(Customer customer) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into customer(name) value (?) ");
			preparedStatement.setString(1, customer.getName());
			result = preparedStatement.executeUpdate() > 0;
			return result;
			
		} catch (Exception e) {
			result = false;
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}  

}
