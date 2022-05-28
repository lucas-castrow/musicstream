/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcaplayer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class JDBCLocalDAO implements LocalDAO{

    private Conexao fabrica;
    
    private static String SQLBUSCACIDADESPORID = "SELECT * FROM cidade where id = ?";
    private static String SQLBUSCACIDADES = "SELECT * FROM cidade where estado = ?";
    private static String SQLBUSCAESTADOS = "SELECT * FROM estado";
    private static String SQLBUSCAESTADOSPORID = "SELECT * FROM estado where id = ?";
    private static String SQLBUSCAPAIS = "SELECT * FROM pais";
    private static String SQLBUSCAPAISPORID = "SELECT * FROM pais where id = ?";
    private static String SQLINSERECIDADE = "INSERT INTO cidade (estado,nome) values (?,?)";
    private static String SQLBUSCACIDADEPORID = "SELECT * FROM cidade where estado = ? and nome = ?";
    public JDBCLocalDAO(Conexao fabrica){
        this.fabrica = fabrica;   
    }
    
    @Override
    public ArrayList<Cidade> listaCidades(int id_estado) {
     ArrayList<Cidade> cidades = new ArrayList<Cidade>();

		try {
                    
                        Connection con = fabrica.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLBUSCACIDADES);

			stmt.setInt(1, id_estado);
                        
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int cidadeID = rs.getInt("id");
                                String nome = rs.getString("nome");
                                int idEstado = rs.getInt("estado");
                                
                                Estado estado = buscarEstado(idEstado);
                                Cidade city = new Cidade(cidadeID,estado,nome);
                                cidades.add(city);

			}
                        rs.close();
			stmt.close();
                        con.close();
                            
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cidades;
    }

    @Override
    public Cidade buscarCidade(int id) {
          try {
                        Connection con = fabrica.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLBUSCAESTADOSPORID);

			stmt.setInt(1, id);
                        
			ResultSet rs = stmt.executeQuery();

			Cidade city = null;

			while (rs.next()) {
                                int idEstado = rs.getInt("estado");
                                int idCidade = rs.getInt("id");
                                String nome = rs.getString("nome");
                                Estado estado = buscarEstado(idEstado);
                                city = new Cidade(idCidade,estado,nome);
			}

			rs.close();
			stmt.close();
                        con.close();
			return city;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

    @Override
    public ArrayList<Estado> listaEstados() {
        ArrayList<Estado> estados = new ArrayList<Estado>();

		try {
                        Connection con = fabrica.getConnection();
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(SQLBUSCAESTADOS);

			while (rs.next()) {

				int estadoID = rs.getInt("id");
                                String uf = rs.getString("uf");
                                String nome = rs.getString("nome");
                                int idPais = rs.getInt("pais");

                                Pais pais = buscarPais(idPais);
                                Estado state = new Estado(estadoID,pais,nome,uf);
    				estados.add(state);

			}
                        rs.close();
			stmt.close();
                        con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estados;
    }

    @Override
    public Estado buscarEstado(int id) {
             try {
		       Connection con = fabrica.getConnection();
                       PreparedStatement stmt = con.prepareStatement(
					SQLBUSCAESTADOSPORID);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Estado state = null;

			while (rs.next()) {
                                int idEstado = rs.getInt("id");
                                int idPais = rs.getInt("pais");
                                String nome = rs.getString("nome");
                                String uf = rs.getString("uf");
                                Pais pais = buscarPais(idPais);
                                state = new Estado(idEstado,pais,nome,uf);
			}

			rs.close();
			stmt.close();
                        con.close();

			return state;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
        

    }

    @Override
    public ArrayList<Pais> listaPais() {
        ArrayList<Pais> pais = new ArrayList<Pais>();

		try {
                    Connection con = fabrica.getConnection();
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(SQLBUSCAPAIS);

			while (rs.next()) {

				String sigla = rs.getString("sigla");
                                String nome = rs.getString("nome");
                                int paisID = rs.getInt("id");

                                Pais country = new Pais(paisID,nome,sigla);
    				pais.add(country);

			}
                        rs.close();
			stmt.close();
                        con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pais;
    }

    @Override
    public Pais buscarPais(int id) {
        try {
                        Connection con = fabrica.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLBUSCAPAISPORID);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			Pais country = null;

			while (rs.next()) {
                            
                                int idPais = rs.getInt("id");
                                String nome = rs.getString("nome");
                                String sigla = rs.getString("sigla");

                                country = new Pais(idPais,nome,sigla);
			}

			rs.close();
			stmt.close();
                        con.close();
			return country;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
        
        
        
        }
    

    @Override
    public void insereCidade(Cidade city) {
          try {
                        Connection con = fabrica.getConnection();
                        
			PreparedStatement stmt = con.prepareStatement(
					SQLINSERECIDADE);
			
                        //stmt.setInt(1, a.getId());
			stmt.setInt(1, city.getEstado().getId());
                        stmt.setString(2, city.getNome());
                        
            
                       // stmt.setDate(4, a.getDataNasc());
                        
			stmt.executeUpdate();
			stmt.close();
                        con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public int buscarCidadeId(Cidade city) {
          try {
                        Connection con = fabrica.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					SQLBUSCACIDADEPORID);

			stmt.setInt(1,city.getEstado().getId());
                        stmt.setString(2, city.getNome());
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
                                int idCidade = rs.getInt("id");
                                return idCidade;
                        }

			rs.close();
			stmt.close();
                        con.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return -1;
   
        
    }
    
}
