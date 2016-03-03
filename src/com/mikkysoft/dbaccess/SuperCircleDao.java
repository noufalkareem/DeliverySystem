package com.mikkysoft.dbaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mikkysoft.model.Circle;

public abstract class SuperCircleDao<T extends Circle> extends CircleDao {

	public SuperCircleDao() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public abstract CircleDao getSubCircleDao() throws SQLException;

	@SuppressWarnings("unchecked")
	public List<T> getSubCircles(int parentId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pStatement = conn
				.prepareStatement("select unitid from circle where parentid = ?");
		pStatement.setInt(1, parentId);
		ResultSet rs = pStatement.executeQuery();
		List<T> circles = new ArrayList<>();
		while (rs.next()) {
			int circleId = rs.getInt("unitid");
			T circle = (T) getSubCircleDao().read(circleId);
			circles.add(circle);
		}
		return circles;

	}

	public void addSubCircleToParent(int parentId, List<T> subCircles)
			throws SQLException {
		for (T circle : subCircles) {
			getSubCircleDao().create(parentId, circle);
		}
	}

}
