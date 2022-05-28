/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

/**
 *
 * @author Lucas
 */
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JDialog;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.JanelaCadastroUsuario;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import tcaplayer.model.Conexao;


/**
 *
 * @author Lucas
 */
public class ControllerJanelaCadastroUsuario implements ActionListener,MouseListener{
    
    private JanelaCadastroUsuario janela;
    private Conexao fabrica;
    private JDBCUsuarioDAO usuarioDAO;
    
   
    private JDBCPerfilDAO perfilDAO;
    
    public ControllerJanelaCadastroUsuario(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
    }
    public void abrir(){
        this.janela = new JanelaCadastroUsuario();
        this.janela.btCadastrar.addActionListener(this);
        this.janela.btCadastrar.addMouseListener(this);
        refreshComboBoxDataNascimento();
        JDialog dialog = new JDialog();
        dialog.setContentPane(this.janela);
        dialog.setSize(this.janela.getSize());
        dialog.setVisible(true); 
        dialog.setLocationRelativeTo(null);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == janela.btCadastrar){
            cadastrar();
        }
    }
    private void cadastrar(){
        Usuario u = new Usuario();
        try {
            if(janela.tfNome.getText().trim().length() > 0)
                u.setNome(janela.tfNome.getText().trim());
            else
                throw new Exception("Preencha o campo nome corretamente!");
            
            if(janela.tfNomeDeUsuario.getText().trim().length() > 0)
                u.setNomeDeUsuario(janela.tfNomeDeUsuario.getText().trim());
            else
                throw new Exception("Preencha o campo nome de usuário corretamente!");
            
            if(janela.tfEmail.getText().trim().length() > 0)
                u.setEmail(janela.tfEmail.getText().trim());
            else
                throw new Exception("Preencha o campo email corretamente!");
            
            if(janela.tfSenha.getText().trim().length() > 0)
                u.setSenha(janela.tfSenha.getText().trim());
            else
                throw new Exception("Preencha o campo senha corretamente!");
                        
            int dia = (int) janela.cbDia.getSelectedItem();
            int mes = (int) janela.cbMes.getSelectedItem();
            int ano = (int) janela.cbAno.getSelectedItem();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String dataNasc =String.format ("%02d.%02d.%02d", ano,mes,dia); 
            Date date = null;
            try {
              date = dateFormat.parse(dataNasc);
              u.setDataNasc(date);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerJanelaCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            usuarioDAO.insere(u);
            janela.mensagem("Usuário cadastrado!");
            
        } catch (Exception e) {
            janela.mensagem(e.getMessage());
        }
    }
    private void refreshComboBoxDataNascimento(){
	
		
		for (int x = 1; x<32;x++) {
                    janela.cbDia.addItem(x);
		}
                
		for (int x = 1; x<13;x++) {
                    janela.cbMes.addItem(x);
		}
                for (int x = 2017; x>1950;x--) {
                    janela.cbAno.addItem(x);
		}
	}

    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == janela.btCadastrar){
            janela.setCursor(Cursor.getPredefinedCursor(12));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == janela.btCadastrar){
            janela.setCursor(Cursor.getPredefinedCursor(0));
            
        }
    }
    
    
}
