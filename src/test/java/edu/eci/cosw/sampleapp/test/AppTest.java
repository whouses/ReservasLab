package edu.eci.cosw.sampleapp.test;

import edu.eci.pdsw.samples.entities.SistemaOperativo;
import edu.eci.pdsw.samples.entities.Solicitud;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.webapp.model.ServicesFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;

import org.junit.Test;

public class AppTest {

@Before
    public void setUp() {
    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=MYSQL", "sa", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from SOLICITUD");
        stmt.execute("delete from SISTEMA_OPERATIVO");
        stmt.execute("delete from LABORATORIO");
        conn.commit();
        conn.close();
    }
  
  @Test
  public void emailOPaginaIncorrectosTest() {
      ServicesFacade sf = ServicesFacade.getInstance("h2-applicationconfig.properties");
      boolean posible=true;
      Solicitud s = new Solicitud();
      s.setSoftware("DevC++");
      s.setLink_descarga("http://dev-c.softonic.com/");
      s.setLink_licencia("licencia");
      SistemaOperativo so = new SistemaOperativo("Windows", "8.1");
      s.setSo(so);
      try{
        sf.saveSolicitud(s);
      }catch(Exception e){
          posible=false;
      }
      Assert.assertTrue(posible);
  }
  
  @Test
  public void laboratoriosConSistemaOperativoTest(){
      ServicesFacade sf = ServicesFacade.getInstance("h2-applicationconfig.properties");
      Solicitud s = new Solicitud();
      s.setSoftware("DevC++");
      s.setLink_descarga("http://dev-c.softonic.com/");
      s.setLink_licencia("licencia");
      SistemaOperativo so = new SistemaOperativo("Windows", "8.1");
      s.setSo(so);
      
  }
} 

