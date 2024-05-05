package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import knihovna.Knihovna;

public class Connect {
	private Connection conn; 
	
	public boolean connect() { 
		conn= null; 
	    
		try {
	    	conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");                       
	    } 
	    catch (SQLException e) { 
	    	System.out.println(e.getMessage());
		    return false;
	      }
	      return true;
	}
	
	public void disconnect() { 
		if (conn != null) {
			try {
				conn.close();
		    }
	        catch (SQLException ex) {
	        	System.out.println(ex.getMessage());
	        }
		}
	}
	
	public boolean createTable()
	{
		if (conn == null)
			return false;
	    
		String sql = "CREATE TABLE IF NOT EXISTS knihy (" +
				"id VARCHAR(255) PRIMARY KEY," +
				"nazev VARCHAR(255) NOT NULL,"+
				"autori VARCHAR(255), " +
				"rokVydani int, " +
				"vypujceno bit," +
				"typ VARCHAR(20)," +
				"zanr VARCHAR(20)," +
				"rocnik int" +
				");";
		
		try {
	        Statement stmt = conn.createStatement(); 
	        stmt.execute(sql);
	        return true;
	    } 
	    catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    return false;
	}
	
	public boolean selectAll(Knihovna knihovna){
		String id = "";
		String nazev = "";
		String autori = "";
		int rokVydani = 0;
		int vypujceno = 0;
		String typ = "";
		String zanr = "";
		int rocnik = 0;
		
		String sql = "SELECT * FROM knihy";
        
		try {
        	Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getString("id");
                nazev = rs.getString("nazev");
                autori = rs.getString("autori");
                rokVydani = rs.getInt("rokVydani");
                vypujceno = rs.getInt("vypujceno");
                typ = rs.getString("typ");
                zanr = rs.getString("zanr");
                rocnik = rs.getInt("rocnik");
                
                String[] autoriUpraveni = autori.split(";");
                
                boolean jeVypujceno;
                if (vypujceno == 0) {
                	jeVypujceno = false;
                }
                else {
                	jeVypujceno = true;
                }
                
                if(typ.equals("roman")) {               	
                	if(knihovna.pridejRoman(id, nazev, autoriUpraveni, rokVydani, jeVypujceno, zanr)) {
                		;
                	}
                	else {
                		System.out.println("Pri vkladani knihy do seznamu doslo k chybe");
                		return false;
                	}
                }
                else if(typ.equals("ucebnice")) {
                	if(knihovna.pridejUcebnici(id, nazev, autoriUpraveni, rokVydani, jeVypujceno, rocnik)) {
                		;
                	}
                	else {
                		System.out.println("Pri vkladani knihy do seznamu doslo k chybe");
                		return false;
                	}
                }
                else {
                	System.out.println("Pri nacitani typu knihy doslo k chybe");
                	return false;
                }
            }
        }
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        	return false;
        }
		
		return true;
	}
	
	public boolean deleteAll() {
        String sql = "DELETE FROM knihy";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
	
	public boolean insertRecord(Knihovna knihovna) {	
		if(this.deleteAll()) {
			;
		}
		else {
			System.out.println("Pri mazani prvku databaze doslo k chybe");
			return false;
		}
		
		int pocetKnih = knihovna.vratPocetKnih();
		
		for(int i = 0; i < pocetKnih; i++) {
			String id = knihovna.vratKlicNaPozici(i);
			if(id != null) {
				;
			}
			else {
				System.out.println("Pri hledani klice doslo k chybe");
				return false;
			}
			
			String nazev = knihovna.vratNazevPodleKlice(id);
			String autori = knihovna.vratAutoryPodleKlice(id);
			int rokVydani = knihovna.vratRokVydaniPodleKlice(id);
			int vypujceno = knihovna.vratVypujcenoPodleKlice(id);
			String typ = knihovna.vratTypPodleKlice(id);
			
			String zanr = null;
			int rocnik = 0;
			if(typ.equals("roman")) {
				zanr = knihovna.vratZanrPodleKlice(id);
			}
			else if(typ.equals("ucebnice")) {
				rocnik = knihovna.vratRocnikPodleKlice(id);
			}
			else {
				System.out.println("Pri urcovani typu doslo k chybe");
				return false;
			}
			
			String sql = "INSERT INTO knihy(id,nazev,autori,rokVydani,vypujceno,typ,zanr,rocnik) VALUES(?,?,?,?,?,?,?,?)";
	        
			try {
	        	PreparedStatement pstmt = conn.prepareStatement(sql); 
	            pstmt.setString(1, id);
	            pstmt.setString(2, nazev);
	            pstmt.setString(3, autori);
	            pstmt.setInt(4, rokVydani);
	            pstmt.setInt(5, vypujceno);
	            pstmt.setString(6, typ);
	            pstmt.setString(7, zanr);
	            pstmt.setInt(8, rocnik);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	            return false;
	        }
		}
		
		return true;
	}
}

