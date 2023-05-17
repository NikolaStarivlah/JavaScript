package model;

import db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {
    public static final String INSERT_ACTIVITY = "insert into summerplan values (?,?,?);";
    public static final String SELECT_ACTIVITY_CATEGORY = "select * from summerplan where category = ?;";
    public static final String SELECT_ALL = "select * from summerplan;";


    public static boolean insertActivity(Activity activity) {
        boolean result = false;
        //instead try-catch-finally, using try-with-resource
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(INSERT_ACTIVITY);) {
            ps.setInt(1, activity.getId());
            ps.setString(2, activity.getCategory());
            ps.setString(3, activity.getActivity());
            result = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return result;
    }

    public static List<Activity> selectActivityCategory(String category) {// throws SQLException {
        List<Activity> list = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ACTIVITY_CATEGORY);
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Activity(
                        rs.getInt("id"), rs.getString("category"),
                        rs.getString("activity")));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return list;
    }

    public static List<Activity> selectAllActivities() {// throws SQLException {
        List<Activity> list = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Activity(
                        rs.getInt("id"), rs.getString("category"),
                        rs.getString("activity")));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return list;
    }
}
