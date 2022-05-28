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
public interface LocalDAO {
    
    public ArrayList<Cidade> listaCidades(int id_estado);
    public Cidade buscarCidade(int id);
    
    public ArrayList<Estado> listaEstados();
    public Estado buscarEstado(int id);
    
    public ArrayList<Pais> listaPais();
    public Pais buscarPais(int id);
    
    public void insereCidade(Cidade city);
    public int buscarCidadeId(Cidade city);
    
    
}
