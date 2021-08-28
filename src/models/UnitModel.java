package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Item;
import entities.Unit;

public class UnitModel {


	public List<Unit> findAll() {
		List<Unit> units = new ArrayList<Unit>();
		try {

			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From unit");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Unit unit = new Unit();
				unit.setId(resultSet.getInt("id"));
				unit.setName(resultSet.getString("name"));
				units.add(unit);
			
			}
		} catch (Exception e) {
			units = null;
		} finally {
			ConnectDB.disconnect();
		}
		return units;
	}
	
	public Unit FindUnitById(int id){
		Unit unit = new Unit();
		try {
			
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("Select * From unit where  id =?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				unit.setId(resultSet.getInt("id"));
				unit.setName(resultSet.getString("name"));
			
			}
		
		} catch (Exception e) {
			unit = null;
		} finally {
			ConnectDB.disconnect();
		}
		return unit;
	
	}
	
	public Unit findId(int id) {
		Unit unit = null;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from unit where id = ?");
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				unit = new Unit();
				unit.setId(resultset.getInt("id"));
				unit.setName(resultset.getString("name"));	
				
			}	
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return unit;
	}
	
	public boolean Create(Unit unit) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("insert into unit (name) values (?)");
			preparestatement.setString(1,unit.getName());			
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
	public boolean update(Unit unit) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("update unit set name = ? where id =?");
			preparestatement.setString(1,unit.getName());
			preparestatement.setInt(2, unit.getId());
			result = preparestatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}

	public boolean deleted(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("delete from unit where id =?");
			preparedstatement.setInt(1,id);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
}
