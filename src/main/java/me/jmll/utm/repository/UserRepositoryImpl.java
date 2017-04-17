package me.jmll.utm.repository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;


import me.jmll.utm.model.User;

import java.sql.*;


	@Repository("userRepository")
	public class UserRepositoryImpl implements UserRepository {
		
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1434;databaseName=prolams;responseBuffering=adaptive;loginTimeout=50;user=sa;password=1234;";
		
		
		private static Map<String, User> userDB = new Hashtable<>();
		private static final Logger log = LogManager.getLogger();
		static {
			
			
			
		}
		
		private void instanceconnector()
		{
			
			try {
			//
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(connectionUrl);
				
				
			} 
			catch (SQLException e) {
				
				log.debug("Error: {}", e.getMessage());
			}
			catch (ClassNotFoundException e) {
				log.debug("Error: {}", e.getMessage());
			}
		}
		
		public UserRepositoryImpl(){
			try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(connectionUrl);
				
				
			} 
			catch (SQLException e) {
				
				log.debug("Error: {}", e.getMessage());
			}
			catch (ClassNotFoundException e) {
				log.debug("Error: {}", e.getMessage());
				log.debug("error2: {}", e.getLocalizedMessage());
			}
    		
		}
		
		@Override
		public List<User> getUsers() {
			List<User> userList = new ArrayList<User>();
			try{
				cstmt = null;
				if(con == null)
					this.instanceconnector();
				cstmt = con.prepareCall("{call dbo.listausers}");
				rs = cstmt.executeQuery();
				User usr = null;
				while(rs.next()){
					usr = new User(rs.getInt("idAdmin"),rs.getString("email"), rs.getString("password"), rs.getString("Nombre")+ " " + rs.getString("ApellidoP"));
					userList.add(usr);
				}
			}
			catch(SQLException e){
				log.debug("Error: {}", e.getMessage());
			}
			
			
			userList.addAll(userDB.values());
			return userList;
		}
	
		@Override
		public User getUserById(int id) {
			User usr = null;
			try{
				cstmt = null;
				cstmt = con.prepareCall("{call dbo.regresausuario (?)}");
				cstmt.setInt(1, id);
				rs = cstmt.executeQuery();
				
				while(rs.next()){
					usr = new User(rs.getInt("idAdmin"),rs.getString("email"), rs.getString("password"), rs.getString("Nombre")+ " " + rs.getString("ApellidoP"));
					break;
				}
			}
			catch(SQLException e){
				log.debug("Error: {}", e.getMessage());
			}
			return usr;
		}
	
		@Override
		public User createUser(String username, String password, String fullName) {
			int id = 0;
			try{
				cstmt = null;
				cstmt = con.prepareCall("{call dbo.InsAdmin (?,?,?,?,?)}");
				cstmt.setString(1, username);
				cstmt.setString(2, fullName);
				cstmt.setString(3, "");
				cstmt.setString(4, "");
				cstmt.setString(5, password);
				rs = cstmt.executeQuery();
				
				while(rs.next()){
					id = rs.getInt("Id");
					break;
				}
			}
			catch(SQLException e){
				log.debug("Error: {}", e.getMessage());
			}
			return this.getUserById(id);
		}
	
		@Override
		public boolean login(String username, String password) {
			log.debug("Trying to authenticate {}, {}", username, password);
			boolean ret = false;
			List<User> userList = new ArrayList<User>();
			userList = this.getUsers();
			User usr = null;
			for(int i = 0; i< userList.size(); i++)
			{
				usr = userList.get(i);
				if(usr.getUsername().equals(username))
				{
					
					ret = usr.getPassword().equals(password);
					
					break;
				}
			}
			return ret;
			
			
		}

		@Override
		public void deleteUser(String username) {
			//userDB.remove(username);
		}

		@Override
		public User updateUser(User user) {
			try{
				cstmt = null;
				cstmt = con.prepareCall("{call dbo.actualizaAdmin (?,?,?,?,?,?)}");
				cstmt.setInt(1, user.getId());
				cstmt.setString(2, user.getUsername());
				cstmt.setString(3, user.getFullName());
				cstmt.setString(4, "");
				cstmt.setString(5, "");
				cstmt.setString(6, user.getPassword());
				rs = cstmt.executeQuery();
				
				
			}
			catch(SQLException e){
				log.debug("Error: {}", e.getMessage());
			}
			return user;
		}
	}
