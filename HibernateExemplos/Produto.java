/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercicio;

import dao.HDAO;
import dao.IDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Usuario
 */
@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue
    private int idProduto;
    private String nome;
    private String descricao;
    private int quantidade;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

//--------------------------------------------------------
     public boolean getGravar()
      {
       IDAO dao = new HDAO();

       boolean teste = dao.inserir(this);
       return teste;
     }

     public List<Produto> getListar()
     {
          IDAO dao = new HDAO();
          List<Produto> produtos = new ArrayList();
          produtos = dao.buscarTodos(this);
          return produtos;
     }

        public boolean getBuscaProduto()
     {
          IDAO dao = new HDAO();
          Produto produto = new Produto();
          produto = (Produto) dao.buscar(this, this.getIdProduto());
          if(produto == null)
          { return false;}
          else
          {
          this.idProduto = produto.getIdProduto();
          this.nome = produto.getNome();
          this.descricao = produto.getDescricao();
          this.quantidade = produto.getQuantidade();
          return true;
         }
   
     }

}
