package org.j2os.project.common;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

public class JDBC {
public static final int XE=1;
private static BasicDataSource basicDataSource = new BasicDataSource();
    static {
        basicDataSource.setPassword("myjava123");
        basicDataSource.setUsername("sorayya");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setMaxTotal(10);

        System.out.println("con");
    }
    public static Connection getConnection()throws Exception{
        Connection connection;
        connection=basicDataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;

    }
}
