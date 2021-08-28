package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Item;
import entities.Manufacture;

public class ManufactureModel {

	public List<Manufacture> findAll() {
		List<Manufacture> manufactures = new ArrayList<Manufacture>();
		try {

			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM manufacture");
			ResultSet result = preparedstatement.executeQuery();
			while (result.next()) {
				Manufacture manufacture = new Manufacture();
				manufacture.setId(result.getInt("id"));
				manufacture.setName(result.getString("name"));
				manufacture.setAddress(result.getString("address"));
				manufacture.setTaxcode(result.getString("taxcode"));
				manufactures.add(manufacture);
			
			}
		} catch (Exception e) {
			manufactures = null;
		} finally {
			ConnectDB.disconnect();
		}
		return manufactures;
	}
	public boolean Create(Manufacture manufacture) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("insert into manufacture (name, address, taxcode) values (?,?,?)");
			preparestatement.setString(1,manufacture.getName());
			preparestatement.setString(2,manufacture.getAddress());
			preparestatement.setString(3, manufacture.getTaxcode());
			
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
	
	public boolean update(Manufacture manufacture) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("update manufacture set name = ?, address = ?, taxcode = ? where id =?");
			preparestatement.setString(1,manufacture.getName());
			preparestatement.setString(2,manufacture.getAddress());
			preparestatement.setString(3, manufacture.getTaxcode());
			preparestatement.setInt(4,manufacture.getId());
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
					.prepareStatement("delete from manufacture where id =?");
			preparedstatement.setInt(1,id);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	
	public Manufacture FindID(int id) {
		Manufacture manufacture = null;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from manufacture where id = ?");
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				manufacture = new Manufacture();
				manufacture.setId(resultset.getInt("id"));
				manufacture.setName(resultset.getString("name"));
				manufacture.setAddress(resultset.getString("address"));
				manufacture.setTaxcode(resultset.getString("taxcode"));				
			}	
		} catch (Exception e) {
		
		}finally {
			ConnectDB.disconnect();
		}
		return manufacture;
	}
	public List<Manufacture> findName(String name){
		List<Manufacture> manufactures = new ArrayList<Manufacture>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from manufacture where name like ?");
			preparedstatement.setString(1, "%" + name + "%");
			ResultSet result = preparedstatement.executeQuery();
			while(result.next()) {
				Manufacture manufacture = new Manufacture();
				manufacture.setId(result.getInt("id"));
				manufacture.setName(result.getString("name"));
				manufacture.setAddress(result.getString("address"));
				manufacture.setTaxcode(result.getString("taxcode"));
				manufactures.add(manufacture);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnectDB.disconnect();
		}
		return manufactures;
	}
	public Manufacture FindIManufactureById(int id){
		Manufacture manufacture = new Manufacture();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * from manufacture where  id =?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				manufacture.setId(resultSet.getInt("id"));
				manufacture.setName(resultSet.getString("name"));
				
			
			}
		
		} catch (Exception e) {
			manufacture = null;
		} finally {
			ConnectDB.disconnect();
		}
		return manufacture;
	
	}
}
