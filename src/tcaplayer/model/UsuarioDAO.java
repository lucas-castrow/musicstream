/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public interface UsuarioDAO {
    public void insere(Usuario a) throws Exception;
    public Usuario entrar(Usuario user);
    public Usuario buscaUsuario(String text);
    public Usuario buscaUsuarioId(int id);
    public Usuario recuperar(String nick);
    
   
}
