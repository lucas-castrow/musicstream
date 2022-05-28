/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lucas
 */
public class JDBCPerfilDAO implements PerfilDAO {

       private Conexao fabrica;
//        Connection con = new Conexao().getConnection();

        private static String SQLBUSCAPERFIL= "SELECT * FROM perfil WHERE id_usuario = ?";
        private static String SQLBAIXAFOTO = "SELECT foto FROM perfil WHERE id_usuario = ?";
        private static String SQLLISTA = "SELECT * FROM perfil";
        private static String SQLBUSCAPORID = "SELECT * from perfil WHERE id_usuario = ?";
        private static String SQLALTERA = "UPDATE perfil SET cidade_natal = ?,sobre = ? , foto = ? WHERE id_usuario = ?";
        
        private JDBCUsuarioDAO dbUsuario;
        private JDBCLocalDAO dbLocal;
	
        public JDBCPerfilDAO(Conexao fabrica) {
               this.fabrica = fabrica;
               this.dbUsuario = new JDBCUsuarioDAO(fabrica);
               this.dbLocal = new JDBCLocalDAO(fabrica);
	}
    
    @Override
    public boolean alteraPerfil(Perfil perfil){
        try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLALTERA);
                        stmt.setInt(1,perfil.getCidade_natal().getId());
			stmt.setString(2, perfil.getSobre());
                        FileInputStream inputStream = new FileInputStream(perfil.getFoto());
                        byte[] b = new byte[(int)perfil.getFoto().length()];
                        stmt.setBinaryStream(3,inputStream, (int) perfil.getFoto().length());
                        stmt.setInt(4,perfil.getUsuario().getId());

			stmt.executeUpdate();
			
			stmt.close();
                        return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
     
     @Override
    public void baixaFoto(Perfil perfil) {
             try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBAIXAFOTO);

			stmt.setInt(1, perfil.getUsuario().getId());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
                                
				Blob blob = rs.getBlob("foto");
                                InputStream is = blob.getBinaryStream();
                                //File f  = new File(blob.getBytes(2, (int)blob.length()));
                                FileOutputStream fos = new FileOutputStream(perfil.getUsuario().getNomeDeUsuario()+".jpg");
                                int b = 0;
                                while ((b = is.read()) != -1)
                                {
                                    fos.write(b); // fos is the instance of FileOutputStream
                                } 
                                fos.close();
                                
                        }
                        
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public Perfil buscaPerfil(Usuario usuario) {
         try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBUSCAPERFIL);

			stmt.setInt(1, usuario.getId());

			ResultSet rs = stmt.executeQuery();

			Perfil perfil = null;

			while (rs.next()) {
                            
                                int id_usuario = rs.getInt("id_usuario");
                                int cidade = rs.getInt("cidade_natal");
                                String sobre = rs.getString("sobre");
                            
                                Blob blob = rs.getBlob("foto");
                                if(blob == null){
                                  
                                File arqFoto = new File("icon2.jpg");
                                
                                Cidade city = dbLocal.buscarCidade(cidade);
				perfil = new Perfil(usuario,city,arqFoto,sobre);
                               // insereFotoSemFoto(perfil);
                                }else{
                                    InputStream is = blob.getBinaryStream();
                                //File f  = new File(blob.getBytes(2, (int)blob.length()));
                                    FileOutputStream fos = new FileOutputStream(usuario.getNomeDeUsuario()+".jpg");
                                    int b = 0;
                                    while ((b = is.read()) != -1)
                                    {
                                        fos.write(b); // fos is the instance of FileOutputStream
                                    } 
                                    fos.close();
                                    File arqFoto = new File(usuario.getNomeDeUsuario()+".jpg");
                                
                                    Cidade city = dbLocal.buscarCidade(cidade);
                                    perfil = new Perfil(usuario,city,arqFoto,sobre);
                                
                                }
                                
				
                        }

			rs.close();
			stmt.close();

			return perfil;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

    }

    @Override
    public Perfil buscaPerfilId(int id) {
          try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBUSCAPORID);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Perfil perf = null;

			while (rs.next()) {
				int id_usuario = rs.getInt("id_usuario");
                                String sobre = rs.getString("sobre");
                                int cidade = rs.getInt("cidade_natal");
                                Usuario userAmigo = dbUsuario.buscaUsuarioId(id_usuario);
                                
                                Blob blob = rs.getBlob("foto");
                              
                                if(blob == null){
                                  
                                 File arqFoto = new File("icon2.jpg");
                                
                                 Cidade city = dbLocal.buscarCidade(cidade);
				 perf = new Perfil(userAmigo,city,arqFoto,sobre);
                               // insereFotoSemFoto(perfil);
                                }else{
                                  InputStream is = blob.getBinaryStream();
                                //File f  = new File(blob.getBytes(2, (int)blob.length()));
                                FileOutputStream fos = new FileOutputStream("friends\\"+userAmigo.getNomeDeUsuario()+".jpg");
                                int b = 0;
                                while ((b = is.read()) != -1)
                                {
                                    fos.write(b); // fos is the instance of FileOutputStream
                                } 
                                fos.close();
                                File arqFoto = new File("friends\\"+userAmigo.getNomeDeUsuario()+".jpg");
                                
                                Cidade city = dbLocal.buscarCidade(cidade);
				perf = new Perfil(userAmigo,city,arqFoto,sobre);
                                
                              }
                                
                                
                                
			}

			rs.close();
			stmt.close();

			return perf;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

    
}
