package bdor3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author oracle
 */
public class bdor3 {
    
    Connection conexion;
    ResultSetMetaData rsmd;
    Statement st;
    ResultSet rs;
    Struct struct;
    Object[] campos;

    public bdor3() {
        try {
            DriverManager.deregisterDriver(new OracleDriver());
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex);
        }
    }

    private Connection conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex);
        }
        return conexion;
    }

    public void verEmpregado() {
        try {
            String consulta = "select empregado.* from empregado";
            st = conectar().createStatement();
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                if (rs.getObject(1) instanceof java.sql.Struct) {
                    struct = (Struct) rs.getObject(1);
                    campos = struct.getAttributes();
                    for (Object ob : campos) {
                        System.out.println(ob.toString());
                    }
                }
                System.out.println(rs.getObject(2).toString());
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex);
        }
    }

    public static void main(String[] args) {
        new bdor3().verEmpregado();
    }

}
