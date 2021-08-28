package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import entities.Account;
public class AccountModel {
	
	public boolean create(Account account) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("insert into account(username, password, full_name, email, phone, address, birthday, role_id, status) values(?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getFullname());
			preparedStatement.setString(4, account.getEmail());
			preparedStatement.setInt(5, account.getPhone());
			preparedStatement.setString(6, account.getAddress());
			preparedStatement.setDate(7, new java.sql.Date(account.getBirthday().getTime()));
			preparedStatement.setInt(8, account.getRole_id());
			preparedStatement.setBoolean(9, account.isStatus());
			result = preparedStatement.executeUpdate() > 0;		
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public Account find(String username) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from account where username = ?");
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account = new Account();
				account.setAddress(resultSet.getString("address"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setEmail(resultSet.getString("email"));
				account.setFullname(resultSet.getString("full_name"));
				account.setId(resultSet.getInt("id"));
				account.setPassword(resultSet.getString("password"));
				account.setUsername(resultSet.getString("username"));
				account.setPhone(resultSet.getInt("phone"));
				account.setRole_id(resultSet.getInt("role_id"));
				account.setStatus(resultSet.getBoolean("status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
		} finally {
			ConnectDB.disconnect();
		}
		return account;
	}
	
	public Account findId(int id) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from account where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account = new Account();
				account.setAddress(resultSet.getString("address"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setEmail(resultSet.getString("email"));
				account.setFullname(resultSet.getString("full_name"));
				account.setId(resultSet.getInt("id"));
				account.setPassword(resultSet.getString("password"));
				account.setUsername(resultSet.getString("username"));
				account.setPhone(resultSet.getInt("phone"));
				account.setRole_id(resultSet.getInt("role_id"));
				account.setStatus(resultSet.getBoolean("status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
		} finally {
			ConnectDB.disconnect();
		}
		return account;
	}
	
	
	public boolean login(String username, String password) {
		Account account = find(username);
		if(account != null) {
			if(BCrypt.checkpw(password, account.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection().prepareStatement("select * from account");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("id"));
				account.setUsername(resultSet.getString("username"));
				account.setPassword(resultSet.getString("password"));
				account.setBirthday(resultSet.getDate("birthday"));
				account.setFullname(resultSet.getString("full_name"));
				account.setEmail(resultSet.getString("email"));
				account.setPhone(resultSet.getInt("phone"));
				account.setAddress(resultSet.getString("address"));
				account.setRole_id(resultSet.getInt("role_id"));
				account.setStatus(resultSet.getBoolean("status"));
				accounts.add(account);
			}
		} catch (Exception e) {
			accounts = null;
		} finally {
			ConnectDB.disconnect();
		}
		return accounts;
	}
	
	public List<Account> search(String keyword) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select * from account where username like ?");
			preparedStatement.setString(1,"%" + keyword + "%");
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()) {
				Account account = new Account();
				account.setId(resultset.getInt("id"));
				account.setUsername(resultset.getString("username"));
				account.setFullname(resultset.getString("full_name"));
				account.setEmail(resultset.getString("email"));
				account.setPhone(Integer.parseInt(resultset.getString("phone")));
				account.setAddress(resultset.getString("address"));
				account.setBirthday(resultset.getDate("birthday"));
				account.setRole_id(resultset.getInt("role_id"));
				account.setStatus(resultset.getBoolean("status"));
				accounts.add(account);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			accounts = null;
		} finally {
			ConnectDB.disconnect();
		}
		return accounts;
		
	}
	
	public boolean update(Account account) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("update account set username = ?, password = ?, full_name = ?, email = ?, phone = ?, address = ?, birthday = ?, role_id = ?, status = ? where id = ?");
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getFullname());
			preparedStatement.setString(4, account.getEmail());
			preparedStatement.setInt(5, account.getPhone());
			preparedStatement.setString(6, account.getAddress());
			preparedStatement.setDate(7, new java.sql.Date(account.getBirthday().getTime()));	
			preparedStatement.setInt(8, account.getRole_id());
			preparedStatement.setBoolean(9, account.isStatus());
			preparedStatement.setInt(10, account.getId());
			result = preparedStatement.executeUpdate() > 0;
		
		} catch (Exception e) {
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public boolean delete(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("delete from account where id = ?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			result = false;
		} finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public Account findByUsernameAndEmail(String username, String email) {
		Account account = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.connection()
					.prepareStatement("select u from account u where u.username and u.email = :email");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account = new Account();
				account.setEmail(resultSet.getString("email"));
				account.setUsername(resultSet.getString("username"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
		} finally {
			ConnectDB.disconnect();
		}
		return account;
	}
}
