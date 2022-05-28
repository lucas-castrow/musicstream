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
public interface MensagemDAO {
    
    public void enviaMensagem(Mensagem msg);
    public ArrayList<Mensagem> buscaMensagens(Mensagem msg);
}
