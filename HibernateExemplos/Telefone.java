package exercicio;

import java.io.Serializable;
import util.TipoTelefone;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Telefone implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id_telefone")
    private int id;
    private String ddd;
    private String numero;
    private TipoTelefone tipo;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="usuario_telefone", schema="exerciciolp4",
    joinColumns=@JoinColumn(name="id_telefone"),
    inverseJoinColumns=@JoinColumn(name="idUser"))
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public List<Usuario> getListUsuario() {
        return this.usuarios;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDdd() {
        return this.ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
}
