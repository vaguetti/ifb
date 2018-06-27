package exercicio;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id_endereco")
    private int id_endereco;
    private String logradouro;
    private int numero;
    private String complemento;
    private String cep;
    private String bairro;

    @OneToMany(mappedBy="endereco", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public List<Usuario> getListUsuario() {
        return this.usuarios;
    } 

    public int getId() {
        return this.id_endereco;
    }

    public void setId(int id) {
        this.id_endereco = id;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
