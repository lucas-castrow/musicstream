/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import tcaplayer.model.Cidade;
import tcaplayer.model.Conexao;
import tcaplayer.model.Estado;
import tcaplayer.model.JDBCLocalDAO;
import tcaplayer.model.JDBCPerfilDAO;
import tcaplayer.model.JDBCUsuarioDAO;
import tcaplayer.model.Pais;
import tcaplayer.model.Perfil;
import tcaplayer.model.Usuario;
import tcaplayer.view.JanelaEntrar;
import tcaplayer.view.PanelEditarMeuPerfil;
import tcaplayer.view.PanelMeuPerfil;

/**
 *
 * @author Lucas
 */
public class ControllerPanelEditarPerfil implements ActionListener, MouseListener,ItemListener{

    private PanelEditarMeuPerfil janela;
    private Conexao fabrica;
    private ControllerJanelaCadastroUsuario ctrlCadastrar;
    private ControllerPanelMeuPerfil ctrlPanelPerfil;
    private JDBCUsuarioDAO usuarioDAO;
    private Perfil perfil;
    private JDBCLocalDAO localDAO;
    private Usuario usuario;
    private JDBCPerfilDAO perfilDAO;
    private JDialog dialog;
    
    public ControllerPanelEditarPerfil(Conexao fabrica){
        this.fabrica = fabrica;
        this.usuarioDAO = new JDBCUsuarioDAO(fabrica);
        this.perfilDAO = new JDBCPerfilDAO(fabrica);
        this.localDAO = new JDBCLocalDAO(fabrica);
       
    }
    public void abrir(Usuario usuario,Perfil perfil ,PanelMeuPerfil pnMeuPerfil){
        this.perfil = perfil;
        this.usuario = usuario;
        this.janela = new PanelEditarMeuPerfil();
        this.janela.btAlterarFoto.addActionListener(this);
        this.janela.btSalvar.addActionListener(this);
        this.janela.btSalvar.addMouseListener(this);
        this.janela.btAlterarFoto.addMouseListener(this);
        this.janela.cbEstados.addItemListener(this);
      //  janela.setVisible(true);
        this.janela.tfSobre.setText(perfil.getSobre());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(usuario.getNomeDeUsuario()+".jpg").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        this.janela.lbFoto.setIcon(imageIcon);
        dialog = new JDialog(this.dialog,"Editar Perfil de "+usuario.getNome(),true);
        refreshComboBoxPais();
        
        dialog.setContentPane(this.janela);
        dialog.setSize(this.janela.getSize());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == janela.btSalvar){
          
            opcoesAltera();
           // ctrlPanelPerfil.atualizaPerfil();
           
        }
        else if(e.getSource() == janela.btAlterarFoto){
            
            openFile();
          
            
        }

    }
   
    
    public void opcoesAltera(){
        String sobre = janela.tfSobre.getText();
        Pais altPais = (Pais) janela.cbPais.getSelectedItem();
        Estado altEstado = (Estado) janela.cbEstados.getSelectedItem();
        Cidade altCity = (Cidade) janela.cbCidades.getSelectedItem();
        perfil.setCidade_natal(altCity);
        perfil.setSobre(sobre);
        if(perfilDAO.alteraPerfil(perfil)){
           perfilDAO.baixaFoto(perfil);
        
            this.janela.tfSobre.setText(perfil.getSobre());
            janela.mensagem("Alterado com sucesso!");
        }else{
            janela.mensagem("Erro na alteração");
        }
        
        
    }
    
        private void refreshComboBoxPais(){
	
		ArrayList<Pais> comb = localDAO.listaPais();
		
		for (Pais pais : comb) {
			janela.cbPais.addItem(pais);
			
		}
                refreshComboBoxEstados();
		
		
	}
        
    
        private void refreshComboBoxEstados(){
	
		ArrayList<Estado> comb = localDAO.listaEstados();
		
		for (Estado estados : comb) {
			janela.cbEstados.addItem(estados);
			
		}
		
		
	}
        private void refreshComboBoxCidades(int id){
	
		ArrayList<Cidade> comb = localDAO.listaCidades(id);
		
                janela.cbCidades.removeAllItems();
		for (Cidade cidades : comb) {
			janela.cbCidades.addItem(cidades);
			
		}
		
		
	}
    
    private void openFile() {
		JFileChooser fileChooser = null;
		
		
                fileChooser = new JFileChooser();
                
		
		
		FileFilter wavFilter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Alterar Foto(*.JPG)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".jpg");
				}
			}
		};

		
		fileChooser.setFileFilter(wavFilter);
		fileChooser.setDialogTitle("Selecionar Foto");
		fileChooser.setAcceptAllFileFilterUsed(false);

		int userChoice = fileChooser.showOpenDialog(janela);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			String arquivow = fileChooser.getSelectedFile().getAbsolutePath();

                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(arquivow).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
                        this.janela.lbFoto.setIcon(imageIcon);
                        
			
                        File arquivoFoto = new File(arquivow);
                        
                        perfil.setFoto(arquivoFoto);
                      
		}
	}
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == janela.cbEstados){
            
            System.out.println("Aperto!!!");
            System.out.println(janela.cbEstados.getSelectedItem());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

           if(e.getSource() == janela.cbEstados){
            
            Estado p = (Estado) janela.cbEstados.getSelectedItem();
            refreshComboBoxCidades(p.getId());
           }
    }
    
}
