/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.Date;

/**
 * Classe modelo que representa um Filme.
 * Contém informações básicas como id, nome, data de lançamento e categoria.
 */
public class Filme {
    private int id;
    private String nome;
    private Date datalancamento;
    private String categoria;
    
     /**
     * Construtor padrão da classe Filme.
     */
    public Filme(){
        
    }

    /**
     * Retorna o ID do filme.
     * @return ID do filme
     */
    public int getId() {
        return id;
    }

     /**
     * Retorna o nome do filme.
     * @return Nome do filme
     */
    public String getNome() {
        return nome;
    }

     /**
     * Retorna a data de lançamento do filme.
     * @return Data de lançamento
     */
    public Date getDatalancamento() {
        return datalancamento;
    }

     /**
     * Retorna a categoria do filme.
     * @return Categoria do filme
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define o ID do filme.
     * @param id ID do filme
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Define o nome do filme.
     * @param nome Nome do filme
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a data de lançamento do filme.
     * @param datalancamento Data de lançamento
     */
    public void setDatalancamento(Date datalancamento) {
        this.datalancamento = datalancamento;
    }

     /**
     * Define a categoria do filme.
     * @param categoria Categoria do filme
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

   
    

   
   
    
    
    
 
   
   
   
   
}
