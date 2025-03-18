package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.abc.config.DatabaseConfig;
import com.abc.entities.User;
@Repository
public class UserDAO {


	public User getUserByUserName(String userName) {

		try (Connection conn = DatabaseConfig.getConnection()) {
			String sql = "SELECT * FROM users Where username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"),rs.getString("created_at"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean registerUser(User user) {

		try (Connection conn = DatabaseConfig.getConnection()) {
			String sql = "INSERT INTO users (username, password) VALUES (? , ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassWord());

			return stmt.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
