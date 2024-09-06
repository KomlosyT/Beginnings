package com.prooktatas.traindriverdb;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author KomlosyT
 */
public class DB 
{
 
    final String JDBC_DRIVER= "";
    final String URL = "jdbc:derby:sampleDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";

    Connection conn = null;
    Statement createStatement = null;
    
    public DB() 
    {

        try 
        {
            conn = DriverManager.getConnection(URL);
            System.out.println("A hid létrejött");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van a connection létrehozásával!");
            System.out.println("" + ex);
        }    


        if(conn != null)
        {
            try 
            {
                createStatement = conn.createStatement();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Valami baj van a createStatement létrehozásakor!");
                System.out.println("" + ex);
            }
        }

        DatabaseMetaData dbmd = null;            
        try 
        {
            dbmd = conn.getMetaData();
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van a DatabaseMetaData létrehozásakor...");
            System.out.println("" + ex);
        }
            
        try 
        {
            ResultSet rs = dbmd.getTables(null, "APP", "TDS", null);
            if(!rs.next())
            {
                createStatement.execute("create table tds (lastName varchar(20)"
                        + ", firstName varchar(20), age varchar(20), category varchar(100)"
                        + ", type varchar(300), line varchar(300))");
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az adattáblák létrehozásokor");
            System.out.println("" + ex);
        }
    }
    
    public void addTD(TD td)
    {
        try 
        {
            String sql = "insert into tds values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, td.getLastName());
            preparedStatement.setString(2, td.getFirstName());
            preparedStatement.setInt(3, td.getAge());
            preparedStatement.setString(4, td.getCategory());
            preparedStatement.setString(5, td.getType());
            preparedStatement.setString(6, td.getLine());
            preparedStatement.execute();
            
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van a mozdonyvezető hozzáadásakor");
            System.out.println("" + ex);
        }
        
    }
    
    public ArrayList<TD> getAllTD()
    {   
        String sql = "select * from tds";
        ArrayList<TD> tds = null;
        
        try 
        {
            ResultSet rs = createStatement.executeQuery(sql);
            tds = new ArrayList<>();
            
            while(rs.next())
            {
                TD currentTD = new TD(
                                    rs.getString("lastName"),
                                    rs.getString("firstName"),
                                    rs.getInt("age"),
                                    rs.getString("category"),
                                    rs.getString("type"),
                                    rs.getString("line"));
                tds.add(currentTD);
            }
            Collator collator = Collator.getInstance();
            collator.setStrength(Collator.PRIMARY);

            Collections.sort(tds, new Comparator<TD>() 
            {
                @Override
                public int compare(TD td1, TD td2) {
                    int lastNameComparison = collator.compare(td1.getLastName(), td2.getLastName());
                    
                    if (lastNameComparison != 0) 
                    {
                    return lastNameComparison;
                    } 
                    else 
                    {
                    return collator.compare(td1.getFirstName(), td2.getFirstName());
                    }
                }
            });
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az user kiolvasáskor");
            System.out.println("" + ex); 
        }    
    return tds;    
    }

    public ArrayList<TD> searchTDByLastName(String lastName)
    {
        String sql = "SELECT * FROM tds WHERE LOWER(lastName) LIKE LOWER(?)";
        ArrayList<TD> tds = null;

        try 
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"%" + lastName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            tds = new ArrayList<>();

            while(rs.next())
            {
                TD currentTD = new TD(
                                    rs.getString("lastName"),
                                    rs.getString("firstName"),
                                    rs.getInt("age"),
                                    rs.getString("category"),
                                    rs.getString("type"),
                                    rs.getString("line"));
                tds.add(currentTD);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az MV keresésekor");
            System.out.println("" + ex); 
        }    
        return tds;
    }

    public ArrayList<TD> searchTDByFirstName(String firstName)
    {
        String sql = "SELECT * FROM tds WHERE LOWER(firstName) LIKE LOWER(?)";
        ArrayList<TD> tds = null;

        try 
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"%" + firstName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            tds = new ArrayList<>();

            while(rs.next())
            {
                TD currentTD = new TD(
                                    rs.getString("lastName"),
                                    rs.getString("firstName"),
                                    rs.getInt("age"),
                                    rs.getString("category"),
                                    rs.getString("type"),
                                    rs.getString("line"));
                tds.add(currentTD);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az MV keresésekor");
            System.out.println("" + ex); 
        }    
        return tds;
    }

    public ArrayList<TD> searchTDByAge(int age)
    {
        String sql = "SELECT * FROM tds WHERE age LIKE ?";
        ArrayList<TD> tds = null;

        try 
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, age + "%");
            ResultSet rs = preparedStatement.executeQuery();
            tds = new ArrayList<>();

            while(rs.next())
            {
                TD currentTD = new TD(
                                    rs.getString("lastName"),
                                    rs.getString("firstName"),
                                    rs.getInt("age"),
                                    rs.getString("category"),
                                    rs.getString("type"),
                                    rs.getString("line"));
                tds.add(currentTD);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az MV keresésekor");
            System.out.println("" + ex); 
        }    
        return tds;
    }

    public ArrayList<TD> searchTDByCategory(String category)
    {
        String sql = "SELECT * FROM tds WHERE category LIKE ?";
        ArrayList<TD> tds = null;

        try 
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + category + "%");
            ResultSet rs = preparedStatement.executeQuery();
            tds = new ArrayList<>();

            while(rs.next())
            {
                TD currentTD = new TD(
                                    rs.getString("lastName"),
                                    rs.getString("firstName"),
                                    rs.getInt("age"),
                                    rs.getString("category"),
                                    rs.getString("type"),
                                    rs.getString("line"));
                tds.add(currentTD);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az MV keresésekor");
            System.out.println("" + ex); 
        }    
        return tds;
    }

    public ArrayList<TD> searchTDByType(String type) 
    {
        String sql = "SELECT * FROM tds WHERE type LIKE ?" ;
        ArrayList<TD> tds = new ArrayList<>();

        try 
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            String[] typeArray = type.split(",");

            for (int i = 0; i < typeArray.length; i++) 
            {
                preparedStatement.setString(i + 1, "%" + typeArray[i].trim() + "%");
            }
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) 
            {
                TD currentTD = new TD(
                    rs.getString("lastName"),
                    rs.getString("firstName"),
                    rs.getInt("age"),
                    rs.getString("category"),
                    rs.getString("type"),
                    rs.getString("line")
                );
                tds.add(currentTD);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az MV keresésekor");
            System.out.println("" + ex);
        }
        return tds;
    }

    public ArrayList<TD> searchTDByLine(String line) 
    {
        String sql = "SELECT * FROM tds WHERE line LIKE ?" ;
        ArrayList<TD> tds = new ArrayList<>();

        try 
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            String[] lineArray = line.split(",");

            for (int i = 0; i < lineArray.length; i++) 
            {
                preparedStatement.setString(i + 1, "%" + lineArray[i].trim() + "%");
            }
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) 
            {
                TD currentTD = new TD(
                    rs.getString("lastName"),
                    rs.getString("firstName"),
                    rs.getInt("age"),
                    rs.getString("category"),
                    rs.getString("type"),
                    rs.getString("line")
                );
                tds.add(currentTD);
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Valami baj van az MV keresésekor");
            System.out.println("" + ex);
        }
        return tds;
    }

    public void updateTD(TD td, String originalLastName) 
    {
        String sql = "UPDATE tds SET lastName = ?, firstName = ?, age = ?, category = ?, type = ?, line = ? WHERE lastName = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setString(1, td.getLastName());  
            pstmt.setString(2, td.getFirstName());
            pstmt.setInt(3, td.getAge());
            pstmt.setString(4, td.getCategory());
            pstmt.setString(5, td.getType());
            pstmt.setString(6, td.getLine());
            pstmt.setString(7, originalLastName);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) 
            {
                System.out.println("A mozdonyvezető adatai sikeresen frissítve.");
            } 
            else 
            {
                System.out.println("Nem történt frissítés, lehet, hogy a mozdonyvezető nem található.");
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Hiba történt a mozdonyvezető adatainak frissítése közben: " + e.getMessage());
        }
    }

    public boolean deleteTD(String lastName, String firstName) {
        try 
        {
            String sql = "DELETE FROM tds WHERE lastName = ? AND firstName = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }


}


