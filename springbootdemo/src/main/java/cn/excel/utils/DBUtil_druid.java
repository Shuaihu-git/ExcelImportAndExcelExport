package cn.excel.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author 张帅虎
 * @Data 2022/9/6
 */
public class DBUtil_druid {
    private static DataSource pool;

    static {
        InputStream is = DBUtil_druid.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties ps = new Properties();
        try {
            ps.load(is);
            pool = DruidDataSourceFactory.createDataSource(ps);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回一个连接池实例
     * @return 返回一个连接池实例
     */
    public static DataSource getDataSource(){
        return pool;
    }

    /**
     * 从连接池中返回一个Connection连接对象
     * @return 返回一个Connection对象
     */
    public static Connection getConnection(){
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭结果集,sql执行载体对象,归还连接给连接池
     * @param res 结果集
     * @param stat sql执行载体
     * @param conn 连接对象
     */
    public static void closeConnection(ResultSet res, Statement stat,Connection conn) throws SQLException {
        try {
            if(res != null){
                res.close();
            }
            if(stat != null){
                stat.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.commit();
        conn.rollback();
        conn.setAutoCommit(true);
        conn.close();
    }
}
