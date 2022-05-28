/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

/**
 *
 * @author Lucas
 */
public interface TransmissaoDAO {
    
    public boolean insereTransmissao(Transmissao transmissao);
    public void atualizaTransmissao(Transmissao transmissao);
    
}
