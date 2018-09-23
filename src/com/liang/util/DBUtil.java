package com.liang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBUtil {

	private static Connection conn = null;
	private static String url = "jdbc:mysql://localhost:3306/graduationdb";
	private static String dbname = "root";
	private static String dbpassword = "1234";

	/**
	 * 获取连接
	 * 
	 * @return conn
	 */
	public static Connection getConnection() {

		// 加载驱动名
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("connot find driver...");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, dbname, dbpassword);
			System.out.println("connect db success...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connot connect db...");
		}
		return conn;
	}

	/**
	 * 测试数据库连接
	 * @param args
	 */
	public static void main(String[] args) {
		Connection  conn=DBUtil.getConnection();
		String url="select * from notices";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(url);
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
