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
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



/**
 *
 * @author Lucas
 */
public class JDBCMensagemDAO implements MensagemDAO{
     private Conexao fabrica;
     private Conexao fabrica2 = new Conexao();

	private static String SQLENVIAMENSAGEM = "INSERT INTO mensagem(remetente,destinatario,mensagem,data,hora) values(?,?,?,?,?)";
        private static String SQLBUSCAMENSAGENS = "call busca_mensagem(?,?)";

        private JDBCUsuarioDAO dbUsuario;
        public JDBCMensagemDAO(Conexao fabrica) {
		this.fabrica = fabrica;
                this.dbUsuario = new JDBCUsuarioDAO(fabrica);
	}

    
    @Override
    public void enviaMensagem(Mensagem msg) {
           try {
                        Connection con = fabrica.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLENVIAMENSAGEM);
			
                        //stmt.setInt(1, a.getId());
                        SimpleDateFormat diaFormat = new SimpleDateFormat("dd/MM/yyyy");
			stmt.setInt(1, msg.getRemetente().getId());
                        stmt.setInt(2,msg.getDestinatario().getId());
			stmt.setString(3,msg.getMensagem());
                        stmt.setDate(4, new java.sql.Date(diaFormat.parse(msg.getData()).getTime()));
                       
                        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
            
           
                        stmt.setTime(5, new java.sql.Time(horaFormat.parse(msg.getHora()).getTime()));
                        
                        
			stmt.executeUpdate();
			stmt.close();
                        con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

  
    @Override
    public ArrayList<Mensagem> buscaMensagens(Mensagem msg){
      ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
   
      	try {
                         Connection con = fabrica2.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLBUSCAMENSAGENS);
			
                        stmt.setInt(1, msg.getRemetente().getId());
                        stmt.setInt(2,msg.getDestinatario().getId());
                        
                       
                        
                        
                        ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
                                String textM = rs.getString("mensagem");
                                int remetente = rs.getInt("remetente");
                                int destinatario = rs.getInt("destinatario");
                                Usuario remetenteMe = dbUsuario.buscaUsuarioId(remetente);
                                Usuario destinatarioFriend = dbUsuario.buscaUsuarioId(destinatario);
                                Date dataDate = rs.getDate("data");
                                Time horaTime = rs.getTime("hora");
                                
                                SimpleDateFormat diaFormat = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
                                
                
                                DateFormat f = DateFormat.getDateInstance(DateFormat.FULL); //Data COmpleta
                                String data = f.format(dataDate.getTime());
                                String hora = horaFormat.format(horaTime.getTime());
                               
                                Mensagem m = new Mensagem(remetenteMe,destinatarioFriend,textM,data,hora);
                                
                                mensagens.add(m);
			}
                          
                    con.close();
                    stmt.close();
                    rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
      
    
      return mensagens;
    }
    
}
