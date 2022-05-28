/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer;

import java.sql.Connection;
import tcaplayer.controller.ControllerJanelaEntrar;
import tcaplayer.model.Conexao;

/**
 *
 * @author Lucas
 */
public class Tcaplayer  {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws Exception{
                       Conexao fabrica = new Conexao();
                       //Connection fabrica = new Conexao().getConnection();
                       ControllerJanelaEntrar j = new ControllerJanelaEntrar(fabrica);
                       j.abrir();
                   }
    
}
