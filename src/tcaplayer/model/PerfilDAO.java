/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.io.File;

/**
 *
 * @author Lucas
 */
public interface PerfilDAO {
    
  //  public boolean inserePerfil(Perfil perfil,int idUser);
    public Perfil buscaPerfil(Usuario usuario);
    public boolean alteraPerfil(Perfil perfil);
    public Perfil buscaPerfilId(int id);
    public void baixaFoto(Perfil perfil);
}
