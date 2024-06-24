/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "comandas")
@NamedQueries({
    @NamedQuery(name = "Comandas.findAll", query = "SELECT c FROM Comandas c"),
    @NamedQuery(name = "Comandas.findByIdComanda", query = "SELECT c FROM Comandas c WHERE c.idComanda = :idComanda"),
    @NamedQuery(name = "Comandas.findByNomeCliente", query = "SELECT c FROM Comandas c WHERE c.nomeCliente = :nomeCliente"),
    @NamedQuery(name = "Comandas.findByStatus", query = "SELECT c FROM Comandas c WHERE c.status = :status")})
public class Comandas implements Serializable {

    @Column(name = "codigo")
    private Integer codigo;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_comanda")
    private Integer idComanda;
    @Basic(optional = false)
    @Column(name = "nome_cliente")
    private String nomeCliente;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public Comandas() {
    }

    public Comandas(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public Comandas(Integer idComanda, String nomeCliente, String status) {
        this.idComanda = idComanda;
        this.nomeCliente = nomeCliente;
        this.status = status;
    }

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComanda != null ? idComanda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comandas)) {
            return false;
        }
        Comandas other = (Comandas) object;
        if ((this.idComanda == null && other.idComanda != null) || (this.idComanda != null && !this.idComanda.equals(other.idComanda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Comandas[ idComanda=" + idComanda + " ]";
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


}
