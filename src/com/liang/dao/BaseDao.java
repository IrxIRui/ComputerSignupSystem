package com.liang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDao {
	
	protected static Connection conn = null;
	protected static Statement stmt = null;
	protected static PreparedStatement pstmt = null;
	protected static ResultSet rs = null;

}
