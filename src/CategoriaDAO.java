package e2;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    
    private static final String JDBC_DRIVER = Dados.JDBC_DRIVER;
    private static final String JDBC_URL = Dados.JDBC_URL;
    private static final String JDBC_USER = Dados.JDBC_USER;
    private static final String JDBC_SENHA = Dados.JDBC_SENHA;
    
    //Função para pegar o maior ID no banco
    private int max_id(){
        int id = 0;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_SENHA);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(id) FROM categoria")) {
                ResultSet resultSet = preparedStatement.executeQuery(); 
                if (resultSet.next()) { 
                    id = resultSet.getInt(1); 
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
    
    public String obterCategoria(int id) {
        String descricao= null;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_SENHA);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT descricao FROM categoria WHERE id = ?")) {
                preparedStatement.setInt(1, id); 
                ResultSet resultSet = preparedStatement.executeQuery(); 
                if (resultSet.next()) { 
                    descricao = resultSet.getString("descricao"); 
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return descricao;
    }
    
    public List<Categoria> obterTodas(){
        List<Categoria> categorias = new ArrayList<>(); 
        int max_id = max_id();
        for (int i = 1; i <= max_id; i++){
           String descricao = obterCategoria(i);
           if(descricao != null){
               Categoria categoria = new Categoria();
               categoria.setId(i);
               categoria.setDescricao(descricao);
               categorias.add(categoria);
           }
        }
        return categorias;
    }

    public boolean inserir(String descricao){
        boolean sucesso = consulta_padrao("INSERT INTO categoria(descricao) VALUES ('"+descricao+"')");
        return sucesso;
    }
    
    public boolean atualizar(String descricao, int id){
        boolean sucesso = consulta_padrao("UPDATE categoria SET descricao='"+descricao+"' where id="+id);
        return sucesso;
    }
    
    public boolean remover(int id){
        boolean sucesso = consulta_padrao("DELETE FROM categoria WHERE id="+id);
        return sucesso;
    }
    
    private boolean consulta_padrao(String consulta){
        boolean sucesso = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_SENHA); PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {
                sucesso = (preparedStatement.executeUpdate()==1);
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return sucesso;        
    }
}