/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class Conexao {
    
    
 public Connection getConnection() {
     try {
         //return DriverManager.getConnection("jdbc:mysql://localhost/info15_marques?useSSL=false", "root", "");
         return DriverManager.getConnection("jdbc:mysql://infoprojetos.com.br:3132/info15_marques", "info15_marques", "lucas123");
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
    
    
}
