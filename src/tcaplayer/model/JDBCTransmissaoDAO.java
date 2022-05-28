/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Lucas
 */
public class JDBCTransmissaoDAO implements TransmissaoDAO {

    private Conexao fabrica;
    private static String SQLINSERT = "INSERT INTO transmissao(transmissor,receptor,inicio_transmissao,fim_transmissao,musica) values(?,?,?,?,?)";
	
    
    public JDBCTransmissaoDAO(Conexao fabrica){
        this.fabrica = fabrica;
    }
    
    @Override
    public boolean insereTransmissao(Transmissao transmissao) {
         try {
			PreparedStatement stmt = fabrica.getConnection().prepareStatement(
					SQLINSERT);
			
                        //stmt.setInt(1, a.getId());
			stmt.setInt(1, transmissao.getTransmissor().getId());
                        stmt.setInt(2, transmissao.getReceptor().getId());
			
                        stmt.setTimestamp(3, new java.sql.Timestamp(transmissao.getInicio_transmissao()));
                       // stmt.setDate(4, a.getDataNasc());
                        stmt.setTimestamp(4,new java.sql.Timestamp(transmissao.getFim_transmissao()) );
                        stmt.setString(5,transmissao.getNome());
                       
			stmt.executeUpdate();
			stmt.close();
                        return true;
		} catch (Exception e) {
			e.printStackTrace();
                        return false;
		}
        
        
        
    }

    @Override
    public void atualizaTransmissao(Transmissao transmissao) {

    }
    
}
