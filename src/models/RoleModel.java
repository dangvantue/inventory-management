package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.RoleAccount;

public class RoleModel {
	
	public List<RoleAccount> findAll() {
		List<RoleAccount> roleAccounts = new ArrayList<RoleAccount>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from role_account");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				RoleAccount roleAccount = new RoleAccount();
				roleAccount.setId(resultSet.getInt("id"));
				roleAccount.setName(resultSet.getString("name"));
				roleAccounts.add(roleAccount);
			}
		} catch (Exception e) {
			roleAccounts = null;
		} finally {
			ConnectDB.disconnect();
		}
		return roleAccounts;
	}
	
	public RoleAccount findId(int id) {
		RoleAccount roleAccount = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from role_account where id = ?");
			preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				roleAccount = new RoleAccount();
				roleAccount.setId(resultSet.getInt("id"));
				roleAccount.setName(resultSet.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			roleAccount = null;
		}finally {
			ConnectDB.disconnect();
		}
		return roleAccount;
	}

}
