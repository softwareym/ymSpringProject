import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

@Log4j
public class DBTest {
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void connection() {
        try(Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://:;DatabaseName=", "", "")) {
            log.info(conn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}