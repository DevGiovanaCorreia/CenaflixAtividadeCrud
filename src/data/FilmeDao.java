/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




/**
 * Classe de acesso aos dados para a entidade Filme.
 * Responsável por realizar operações CRUD no banco de dados.
 */
public class FilmeDao {
   Connection conn;
   PreparedStatement st;
   ResultSet rs;
   
   /**
     * Estabelece conexão com o banco de dados.
     * @return true se a conexão foi bem sucedida, false caso ocorra algum erro.
     */
   public boolean conectar(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ATIVIDADE1","root","G1vt24DiFw1996");
           return true;
           } catch (ClassNotFoundException | SQLException ex){
               System.out.println("Erro ao conectar:" + ex.getMessage());
               return false;
           }
   }
   
   /**
     * Insere um novo filme no banco de dados.
     * @param f Objeto Filme com os dados a serem cadastrados.
     * @return 1 se inserido com sucesso, ou código de erro SQL em caso de falha.
     */
    public int cadastrar(Filme f) {
        
         int status;
        try {
            st = conn.prepareStatement("INSERT INTO filmes(nome,datalancamento,categoria) VALUES(?,?,?)");
            st.setString(1,f.getNome());
            
           java.sql.Date dataSql =
            new java.sql.Date(f.getDatalancamento().getTime());
        st.setDate(2, dataSql);
             st.setString(3, f.getCategoria());
             
            status = st.executeUpdate();
            
            return status; 
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
     /**
     * Lista todos os filmes do banco.
     * @return Lista de filmes cadastrados.
     */
    public List<Filme> listar(){
        List<Filme> lista = new ArrayList<>();
        
        try{
            st = conn.prepareStatement("SELECT * FROM filmes");
            rs = st.executeQuery();
            
            while(rs.next()){
                Filme f = new Filme();
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setDatalancamento(rs.getDate("datalancamento"));
                
                f.setCategoria(rs.getString("categoria"));
                
                lista.add(f);}
            }catch(SQLException ex){
                    System.out.println("Erro ao lista filmes:" + ex.getMessage());
                    }
            return lista;
        }
    
    
     /**
     * Preenche uma JTable com todos os filmes cadastrados.
     * @param tabela JTable que será preenchida.
     */
    public void preencherTabela(JTable tabela) {

    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    modelo.setRowCount(0);

    List<Filme> filmes = listar(); 

    for (Filme f : filmes) {
        modelo.addRow(new Object[]{
            f.getId(),
            f.getNome(),
            f.getDatalancamento(),
            f.getCategoria()
        });
    }
}
    
    
     /**
     * Lista filmes filtrados por categoria.
     * @param categoria Categoria a ser pesquisada.
     * @return Lista de filmes correspondentes à categoria.
     */
    public List<Filme> listarPorCategoria(String categoria) {

    List<Filme> lista = new ArrayList<>();

    try {
        st = conn.prepareStatement(
            "SELECT * FROM filmes WHERE categoria LIKE ?"
        );
        st.setString(1, "%" + categoria + "%");

        rs = st.executeQuery();

        while (rs.next()) {
            Filme f = new Filme();

            f.setId(rs.getInt("id"));
            f.setNome(rs.getString("nome"));
            f.setDatalancamento(rs.getDate("datalancamento"));
            f.setCategoria(rs.getString("categoria"));

            lista.add(f);
        }

    } catch (SQLException ex) {
        System.out.println("Erro ao listar por categoria: " + ex.getMessage());
    }

    return lista;
}
    
    /**
     * Preenche uma JTable com filmes filtrados por categoria.
     * @param tabela JTable que será preenchida.
     * @param categoria Categoria a ser pesquisada.
     */
    public void preencherTabelaPorCategoria(JTable tabela, String categoria) {

    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    modelo.setRowCount(0);

    List<Filme> lista = listarPorCategoria(categoria);

    for (Filme f : lista) {
        modelo.addRow(new Object[]{
            f.getId(),
            f.getNome(),
            f.getDatalancamento(),
            f.getCategoria()
        });
    }
}
    
   /**
     * Obtém um filme pelo seu ID.
     * @param id ID do filme a ser obtido.
     * @return Objeto Filme correspondente, ou null se não encontrado.
     */ 
  public Filme obter(int id) {

    Filme f = null;

    try {
        st = conn.prepareStatement("SELECT * FROM filmes WHERE id = ?");
        st.setInt(1, id);
        rs = st.executeQuery();

        if (rs.next()) {
            f = new Filme();
            f.setId(rs.getInt("id"));
            f.setNome(rs.getString("nome"));
            f.setDatalancamento(rs.getDate("datalancamento"));
            f.setCategoria(rs.getString("categoria"));
        }

    } catch (SQLException ex) {
        System.out.println("Erro ao obter filme: " + ex.getMessage());
    }

    return f;
}
    
    
  /**
     * Atualiza os dados de um filme existente.
     * @param f Filme com ID e novos valores.
     * @return 1 se atualizado com sucesso, ou código de erro SQL em caso de falha.
     */
      public int atualizar(Filme f){
        int status;
        try {
            st = conn.prepareStatement("UPDATE filmes SET nome = ?, datalancamento = ?, categoria = ? where id = ?");
            st.setString(1,f.getNome());
            java.sql.Date dataSql =
            new java.sql.Date(f.getDatalancamento().getTime());
        st.setDate(2, dataSql);
            st.setString(3, f.getCategoria());
        st.setInt(4,f.getId());
            
            status = st.executeUpdate();
            return status; 
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
      
     /**
     * Exclui um filme do banco pelo seu ID.
     * @param id ID do filme a ser excluído.
     * @return 1 se excluído com sucesso, ou código de erro SQL em caso de falha.
     */  
       public int excluir(int id) {
         int status;

        try  {
             st = conn.prepareStatement("DELETE FROM filmes WHERE id=?");
             st.setInt(1, id);
          
             status = st.executeUpdate();
             return status;

        } catch (SQLException ex) {
        System.out.println("Erro ao tentar excluir: " + ex.getMessage());
        return ex.getErrorCode();
    } 
        
        }
  
       /**
     * Fecha a conexão com o banco de dados.
     */
 public void desconectar(){
     try{
         conn.close();
     }catch(SQLException ex){
         
     }
 }
   
   

  
   
   
}
