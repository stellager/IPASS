package database;

import java.net.URI;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class BaseDAO {
 private DataSource connectionPool;
 public BaseDAO() {
 try {
 final String DATABASE_URL_PROP = System.getenv("DATABASE_URL");
 if (DATABASE_URL_PROP != null) {
 URI dbUri = new URI(DATABASE_URL_PROP);
 String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
 BasicDataSource pool = new BasicDataSource();
 pool.setUsername("rzbhfoxfyusude");
 pool.setPassword("9982acff54b7fe17ab15923cc4bb6ac11e865e35fa52fa689776174cef90c2ff");
 if (dbUri.getUserInfo() != null) {
 pool.setUsername("rzbhfoxfyusude");
 pool.setPassword("9982acff54b7fe17ab15923cc4bb6ac11e865e35fa52fa689776174cef90c2ff");
 }
 pool.setDriverClassName("org.postgresql.Driver");
 pool.setUrl(dbUrl);
 pool.setInitialSize(0);
 pool.setMinIdle(0);
 pool.setMaxIdle(0);
 

 connectionPool = pool;
 } else {
	 
 InitialContext ic = new InitialContext();
 
 connectionPool = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
 
 }
 } catch (Exception e) {
 throw new RuntimeException(e);
 }
 }
 protected final Connection getConnection() {
 try {
 return connectionPool.getConnection();
 } catch (Exception ex) {
 throw new RuntimeException(ex);
 }
 }
}