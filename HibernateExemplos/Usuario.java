/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exercicio;

import dao.HDAO;
import dao.IDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import util.TipoUsuario;

/**
 *
 * @author Usuario
 */
@Entity
public class Usuario implements Serializable{
    
  @Id
  @GeneratedValue
  private int idUser;
  private String nome;
  private String login;
  private String senha;
  @Enumerated(EnumType.STRING)
  private TipoUsuario tipo = TipoUsuario.OPERADOR;

  @Temporal(javax.persistence.TemporalType.DATE)
  private Date dataNascimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_endereco",insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Endereco endereco;

     public void setEndereco(Endereco endereco) {

        this.endereco = endereco;
        endereco.addUsuario(this);
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="usuario_telefone", schema="exerciciolp4",
    joinColumns=@JoinColumn(name="idUser"),
    inverseJoinColumns=@JoinColumn(name="id_telefone"))
    private List<Telefone> telefone = new ArrayList<Telefone>();

      public void addTelefone(Telefone telefone) {
       
        this.telefone.add(telefone);
        //telefone.addUsuario(this);
    }

    public List<Telefone> getListTelefone() {
        return this.telefone;
    }

  @Transient
  private boolean autenticado = false;

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
    public Date getDataNascimento()
    {
        return this.dataNascimento;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean auntenticado) {
        this.autenticado = auntenticado;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {

        this.tipo = tipo;
    }
//-----------------------------------------------

     public boolean getGravar()
      {
       IDAO dao = new HDAO();

       List<Usuario> x = new ArrayList();
       x = (List<Usuario>) dao.buscar("login", this.getLogin(), false, this);
       if(x.isEmpty())
       {
       boolean teste = dao.inserir(this);
       return teste;}else
       { return false;}
     }
     public boolean getValidar()
     {
         IDAO dao = new HDAO();

       List<Usuario> x = new ArrayList();
       x = (List<Usuario>) dao.buscar("login", this.getLogin(), false, this);
       if(x.isEmpty())
       {
       return false;}
       else
       {
          Usuario y = (Usuario) x.get(0);

          System.out.println(y.getLogin());
          if(y.getSenha().equals(this.getSenha()))
          {
              this.setNome(y.getNome());
             // this.setTipo(y.getTipo().toString());
              this.setTipo(y.getTipo());

              this.setAutenticado(true);
              return true; }
          else
          { return false;  }

       }
     }
}
