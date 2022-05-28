/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author Lucas
 */
public class JDBCUsuarioDAO implements UsuarioDAO {
        Conexao fabrica;

	private static String SQLINSERT = "INSERT INTO usuario(nome,nome_de_usuario,email,senha,data_nascimento,online) values(?,?,?,?,?,?)";
	private static String SQLENTRAR = "call login(?,?)";
        private static String SQLBUSCAUSER= "SELECT * FROM usuario WHERE email = ?";
        private static String SQLBUSCAUSERID= "SELECT id,nome,nome_de_usuario,email,data_nascimento FROM usuario WHERE id = ?";
        private static String SQLLISTA = "SELECT * FROM usuario";
        private static String SQLRECUPERAR = "SELECT * FROM usuario WHERE nome_de_usuario = ?";
	
        public JDBCUsuarioDAO(Conexao fabrica) {
		this.fabrica = fabrica;
               
	}

    @Override
    public void insere(Usuario a) throws Exception{
        try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLINSERT);
			
                        //stmt.setInt(1, a.getId());
			stmt.setString(1, a.getNome());
                        stmt.setString(2, a.getNomeDeUsuario());
			stmt.setString(3, a.getEmail());
			stmt.setString(4, a.getSenha());
                        
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                        
                       // stmt.setDate(4, a.getDataNasc());
                        stmt.setDate(5, new java.sql.Date(a.getDataNasc().getTime()));
                        
                        stmt.setBoolean(6, false);
                        
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e){
                    throw new Exception(e.getMessage());
                }catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
 
    @Override
    public Usuario entrar(Usuario user) {
        try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLENTRAR);

			stmt.setString(1,user.getEmail());
                        stmt.setString(2, user.getSenha());
			ResultSet rs = stmt.executeQuery();

			Usuario a = user;
                        a.setId(-1);

			while (rs.next()) {
				int usuarioID = rs.getInt("id");
				String nome = rs.getString("nome");
                                String nomeDeUsuario = rs.getString("nome_de_usuario");
                                String emailx = rs.getString("email");
                                
                             //   Date dataNasc = rs.getDate("date");
                                
                                
                                
				a.setId(usuarioID);
                              //  a.setDataNasc(dataNasc);
                                a.setNome(nome);
                                a.setEmail(emailx);
                                a.setNomeDeUsuario(nomeDeUsuario);
                               // a.entrou(a);
			}

			rs.close();
			stmt.close();

			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

    @Override
    public Usuario buscaUsuario(String text) {
            try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBUSCAUSER);

			stmt.setString(1,text);
			ResultSet rs = stmt.executeQuery();

                        
                        Usuario usuario;
			while (rs.next()) {
				 int idUsuario = rs.getInt("id");
                                 String nome = rs.getString("nome");
                                 String nomeDeUsuario = rs.getString("nome_de_usuario");
                                 String email = rs.getString("email");
                                 String senha = rs.getString("senha");
                                 
                                 usuario = new Usuario(idUsuario,nome,nomeDeUsuario,email,senha);
                                    
                                 return usuario;
                        }

			rs.close();
			stmt.close();
                        
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;


    }

   @Override
    public Usuario buscaUsuarioId(int id){
     
        try {
                        Connection con = fabrica.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLBUSCAUSERID);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Usuario user = null;

			while (rs.next()) {
				int idUsuario = rs.getInt("id");
                                String nome = rs.getString("nome");
                                String email = rs.getString("email");
                                Date dataNasc = rs.getDate("data_nascimento");
                                String nomeDeUsuario = rs.getString("nome_de_usuario");
				user = new Usuario(idUsuario,nome,nomeDeUsuario,email,dataNasc);
			}
                        
			rs.close();
			stmt.close();
                        con.close();
                        
			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

    @Override
    public Usuario recuperar(String nick) {
         try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLRECUPERAR);

                        stmt.setString(1, nick);
			ResultSet rs = stmt.executeQuery();

                        Usuario a;
			while (rs.next()) {
				int usuarioID = rs.getInt("id");
				String nome = rs.getString("nome");
                                String nomeDeUsuario = rs.getString("nome_de_usuario");
                                String emailx = rs.getString("email");
                                String senha = rs.getString("senha");

                                
				a = new Usuario(usuarioID,nome,nomeDeUsuario,emailx,senha);
                                return a;
                               // a.entrou(a);
			}

			rs.close();
			stmt.close();
                        
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
         return null;


    }
    
    public ResultSet relatorioMaisSeguidos(){
        try{
            PreparedStatement stmt = fabrica.getConnection().prepareStatement("select * from ranking_dos_mais_seguidos");
            return stmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultSet relatorioMaisMusicas(){
        try{
            PreparedStatement stmt = fabrica.getConnection().prepareStatement("select * from quem_postou_mais_musica");
            return stmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultSet relatorioMaisOuviu(){
        try{
            PreparedStatement stmt = fabrica.getConnection().prepareStatement("select * from ranking_de_quem_mais_ouve");
            return stmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
