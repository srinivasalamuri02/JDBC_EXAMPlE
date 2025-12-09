
import java.sql.*;
import java.util.Scanner;

public class JDBC_Operations {


    static final String url = "jdbc:mysql://localhost:3306/jdbc_example";
    static final String user_name = "root";
    static final String password = "root";
    static  Connection connection = null;

    public static void createStudent(String name, String email)
            throws SQLException
    {
        String query = "insert into students (name, email)" +
                "values (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, email);
            int result = ps.executeUpdate();
            System.out.println("The number of rows affected: " + result);
        }
    }

    public static void readStudent()
            throws SQLException
    {
        String query = "select * from students";
        try(PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getInt("id") + ", "
                        + rs.getString("name") + ", "
                        + rs.getString("email"));
            }
        }
    }

//    public static void updateStudent(int id, String name,
//                                     String email)
//            throws SQLException
//    {
//        String query = "update students set name = ?, " +
//                "email = ? where id = ?";
//        try(PreparedStatement ps = connection.prepareStatement(query)) {
//            ps.setString(1, id);
//            ps.setString(2, name);
//            ps.setString(3, email);
//            int result = ps.executeUpdate();
//            System.out.println("The number of rows affected: " + result);
//        }
//    }

    public static void updateStudent(int id, String name,
                                     String email)
            throws SQLException {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");
        }
    }

        public static void deleteStudent(int id)
            throws SQLException
        {
            String query = "delete from students where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                int result = ps.executeUpdate();
                System.out.println("The number of rows affected: " + result);
            }
        }

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(url, user_name, password);
            System.out.println("Connected to database successfully");

            // Taking the values from the user
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Name");
            String name = sc.nextLine();
            System.out.println("Enter Email");
            String email = sc.nextLine();

            createStudent(name, email);
            readStudent();
            updateStudent(1,"Ravi", "xyz@gmail.com");
            deleteStudent(2);
        
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        

    }
}
