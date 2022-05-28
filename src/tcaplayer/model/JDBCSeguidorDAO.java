/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class JDBCSeguidorDAO implements SeguidorDAO {
        Conexao fabrica;

	private static String SQLINSERT = "INSERT INTO seguidor(usuario,usuario_seguido) values(?,?)";
        private static String SQLBUSCAAMIGOS = "call usuarios_seguidos(?)";
        private static String SQLBUSCASEGUEM = "call usuarios_que_seguem(?)";
        private static String SQLSEGUE = "call seguir(?,?)";
        private static String SQLPARARDESEGUIR = "DELETE FROM seguidor WHERE usuario = ? and usuario_seguido = ?";
	private JDBCUsuarioDAO dbUsuario;
        private JDBCPerfilDAO dbPerfil;
        public JDBCSeguidorDAO(Conexao fabrica) {
		this.fabrica = fabrica;
                this.dbUsuario = new JDBCUsuarioDAO(fabrica);
                this.dbPerfil = new JDBCPerfilDAO(fabrica);
	}

    
    @Override
    public boolean insereAmigo(int meuId,String text) {
           try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLSEGUE);
			
                        //stmt.setInt(1, a.getId());
                        
			stmt.setInt(1, meuId);
                        stmt.setString(2,text);
			
			stmt.executeUpdate();
			stmt.close();
                        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
           return false;
    }

    @Override
    public boolean pararDeSeguir(Seguidor dado) {

          try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLPARARDESEGUIR);
			
                        //stmt.setInt(1, a.getId());
                        
			stmt.setInt(1, dado.getEu().getId());
                        stmt.setInt(2,dado.getAmigo().getId());
			
			stmt.executeUpdate();
			stmt.close();
                        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
           return false;
        
        
        
    }
    @Override
    public ArrayList<Perfil> buscaMeusAmigos(Usuario usuario){
      ArrayList<Perfil> perfilMeusAmigos = new ArrayList<Perfil>();
   
      	try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBUSCAAMIGOS);
			
                        stmt.setInt(1, usuario.getId());
                        
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
                                int id = rs.getInt("id");
                                Perfil perf = dbPerfil.buscaPerfilId(id);
                                perfilMeusAmigos.add(perf);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
      
      
     
      return perfilMeusAmigos;
    }

    @Override
    public ArrayList<Perfil> buscaMeSeguem(Usuario usuario) {
    ArrayList<Perfil> perfilMeSeguem = new ArrayList<Perfil>();
   
      	try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBUSCASEGUEM);
			
                        stmt.setInt(1, usuario.getId());
                        
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
                                int id = rs.getInt("id");
                                Perfil perf = dbPerfil.buscaPerfilId(id);
                                perfilMeSeguem.add(perf);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
      
      
     
      return perfilMeSeguem;
    
    
    }
    
}
