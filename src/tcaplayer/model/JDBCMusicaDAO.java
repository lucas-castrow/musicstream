/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class JDBCMusicaDAO implements MusicaDAO {
    
    private Conexao fabrica;
    private static String SQLINSERT = "INSERT INTO musica(id_usuario,nome_musica,duracao,musica) values(?,?,?,?)";
    private static String SQLBAIXAR = "SELECT nome_musica,musica FROM musica WHERE id=?";
    private static String SQLLISTA = "SELECT * FROM musica";
    private JDBCUsuarioDAO dbUsuario;
    
    public JDBCMusicaDAO(Conexao fabrica){
        this.fabrica = fabrica;
        this.dbUsuario = new JDBCUsuarioDAO(fabrica);
    }
    
    
     @Override
    public void insere(Musica a) {
        try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLINSERT);
			
                        //stmt.setInt(1, a.getId());
                        FileInputStream inputStream = new FileInputStream(a.getFile());
                        byte[] b = new byte[(int)a.getFile().length()];

			stmt.setInt(1,a.getUsuario().getId());
                        stmt.setString(2,a.getNome() );
			stmt.setInt(3,a.getDuracao());
                        stmt.setBinaryStream(4,inputStream, (int) a.getFile().length());
			
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
       @Override
    public void baixar(int id,String diretorio) {
        try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLBAIXAR);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Musica a = null;

			while (rs.next()) {
				String nome = rs.getString("nome_musica");
                                
				Blob blob = rs.getBlob("musica");
                                InputStream is = blob.getBinaryStream();
                                //File f  = new File(blob.getBytes(2, (int)blob.length()));
                                FileOutputStream fos = new FileOutputStream(diretorio+"\\"+nome);
                                int b = 0;
                                while ((b = is.read()) != -1)
                                {
                                    fos.write(b); // fos is the instance of FileOutputStream
                                } 
                                fos.close();
                                
                               // File f  = new File(fos);

                             //   a.setId(alunoID);
			}
                        
			rs.close();
			stmt.close();

			//return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	return null;
    }
    @Override
    public ArrayList<Musica> todasMusicas() {
       ArrayList<Musica> musicas = new ArrayList<Musica>();

		try {
			Statement stmt = fabrica.getConnection().createStatement();

			ResultSet rs = stmt.executeQuery(SQLLISTA);

			while (rs.next()) {

				int usuarioID = rs.getInt("id_usuario");
				int musicaID = rs.getInt("id");
                                String nome = rs.getString("nome_musica");
                                int duracao = rs.getInt("duracao");
                                Usuario usuario = dbUsuario.buscaUsuarioId(usuarioID);
                                Musica msc = new Musica(musicaID,usuario,duracao,nome);
				musicas.add(msc);
                               

			}
                            
		} catch (Exception e) {
			e.printStackTrace();
		}

		return musicas;
    }
    

    
    
}
