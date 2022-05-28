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
public interface SeguidorDAO {
    
    public boolean insereAmigo(int meuId,String text);
    public boolean pararDeSeguir (Seguidor dado);
    public ArrayList<Perfil> buscaMeusAmigos(Usuario usuario);
    public ArrayList<Perfil> buscaMeSeguem(Usuario usuario);
    
}
