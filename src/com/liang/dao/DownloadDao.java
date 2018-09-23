package com.liang.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liang.entity.Download;
import com.liang.util.DBUtil;
import com.liang.util.JDBCCloss;
import com.opensymphony.xwork2.ActionContext;

public class DownloadDao extends BaseDao {
	String sql = null;
	List<Download> downloadsLsit = null;
	Download download = null;


	private List<Download> MultiNotice(String sql) {
		downloadsLsit = new ArrayList<Download>();
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				download = new Download();
				download.setDownloadsId(rs.getInt(1));
				download.setDownloadsName(rs.getString(2));
				download.setDownloadsPath(rs.getString(3));
				download.setDownloadsDate(rs.getTimestamp(4));

				downloadsLsit.add(download);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCCloss.close(conn, stmt, pstmt, rs);
		return downloadsLsit;
	}

	public List<Download> getDownloads() {
		sql = "select * from announcement order by createDate DESC";// 按时间降序排列
		return MultiNotice(sql);
	}
}
