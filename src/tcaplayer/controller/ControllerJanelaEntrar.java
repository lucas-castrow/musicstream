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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import tcaplayer.model.Conexao;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Usuario;
import tcaplayer.view.JanelaEntrar;

/**
 *
 * @author Lucas
 */
public class ControllerJanelaEntrar implements ActionListener , MouseListener,WindowListener{

    private JanelaEntrar janela;
    private Conexao fabrica;
    private ControllerJanelaCadastroUsuario ctrlCadastrar;
    private ControllerJanelaPrincipal ctrlJanelaPrincipal;
    private ControllerJanelaRecuperarSenha ctrlJanelaRecuperar;
    private JDBCUsuarioDAO usuarioDAO;
    private JDialog dialog;
    
    public ControllerJanelaEntrar(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.ctrlCadastrar = new ControllerJanelaCadastroUsuario(fabrica);
        this.ctrlJanelaPrincipal = new ControllerJanelaPrincipal(fabrica);
        this.ctrlJanelaRecuperar = new ControllerJanelaRecuperarSenha(fabrica);
    }
    public void abrir(){
        
        this.janela = new JanelaEntrar();
        this.janela.btCadastrar.addActionListener(this);
        this.janela.btEntrar.addActionListener(this);
        this.janela.btCadastrar.addMouseListener(this);
        this.janela.btEntrar.addMouseListener(this);
        this.janela.btRecuperarSenha.addActionListener(this);
        this.janela.btRecuperarSenha.addMouseListener(this);
        this.janela.cbManterConectado.addActionListener(this);
        
      //  janela.setVisible(true);
        dialog = new JDialog();
        dialog.setContentPane(this.janela);
        dialog.setSize(this.janela.getSize());
        dialog.setVisible(true);
        dialog.addWindowListener(this);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        String login = "";
        String pass = "";
        try{
            Scanner scan = new Scanner(new File("logado.txt"));
            while(scan.hasNextLine()){
            login = scan.nextLine();
            pass = scan.nextLine();
            }
            scan.close();
            }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            }
        if(login.length() > 0){
            this.janela.tfEmail.setText(login);
            this.janela.pfSenha.setText(pass);
        }
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == janela.btEntrar){
            try {
                entrar();
            } catch (Exception ex) {
                Logger.getLogger(ControllerJanelaEntrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource() == janela.btCadastrar){
            ctrlCadastrar.abrir();
          //  dialog.setVisible(false);
        }else if(e.getSource() == janela.btRecuperarSenha){
            ctrlJanelaRecuperar.abrir();
        }
        

    }
    private void entrar() throws Exception{
        String email, senha;
        try {
            if(janela.tfEmail.getText().trim().length() > 0)
                email = janela.tfEmail.getText();
            else
                throw new Exception("Preencha o campo Usuario");
               
            if(janela.pfSenha.getText().trim().length() > 0)
                senha = new String(janela.pfSenha.getPassword());
            else
                throw new Exception("Preencha o campo Senha");
            
            Usuario user = new Usuario(email,senha);
            Usuario a = usuarioDAO.entrar(user);

            if(a.getId() == -1)
                throw new Exception("Usuario ou Senha inválidos!");
            
            if(janela.cbManterConectado.isSelected()){


                FileWriter arq = new FileWriter("logado.txt");
                PrintWriter gravarArq = new PrintWriter(arq);

                  gravarArq.println(email);
                  gravarArq.println(senha);

                arq.close();

            }else{
                 File f = new File("logado.txt");
                 f.delete();
            }

            dialog.dispose();
            ctrlJanelaPrincipal.abrir(a);

        } catch (Exception e) {
            janela.mensagem(e.getMessage());
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
        if(e.getSource() == janela.btCadastrar || e.getSource() == janela.btEntrar || e.getSource() == janela.btRecuperarSenha){
                janela.setCursor(Cursor.getPredefinedCursor(12));
        }  
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == janela.btCadastrar || e.getSource() == janela.btEntrar || e.getSource() == janela.btRecuperarSenha){
                janela.setCursor(Cursor.getPredefinedCursor(0));
        }  
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
      int op = janela.fechar_janela();
      if(op == 1){
          dialog.dispose();
      }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
    
    
    
}
