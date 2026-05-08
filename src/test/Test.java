package test;
import java.sql.Connection;
import java.sql.DriverManager;


public class Test {
    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/world?serverTimezone=Asia/Seoul",
                            "root",
                            "1234"
                    );

            System.out.println("연결 성공");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
