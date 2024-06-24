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
@Table(name = "Produtos_comanda")
@NamedQueries({
    @NamedQuery(name = "Produtoscomanda.findAll", query = "SELECT p FROM Produtoscomanda p"),
    @NamedQuery(name = "Produtoscomanda.findById", query = "SELECT p FROM Produtoscomanda p WHERE p.id = :id"),
    @NamedQuery(name = "Produtoscomanda.findByIdComanda", query = "SELECT p FROM Produtoscomanda p WHERE p.idComanda = :idComanda"),
    @NamedQuery(name = "Produtoscomanda.findByIdProduto", query = "SELECT p FROM Produtoscomanda p WHERE p.idProduto = :idProduto")})
public class Produtoscomanda implements Serializable {

    @Column(name = "quantidade")
    private Integer quantidade;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_comanda")
    private int idComanda;
    @Basic(optional = false)
    @Column(name = "id_produto")
    private int idProduto;

    public Produtoscomanda() {
    }

    public Produtoscomanda(Integer id) {
        this.id = id;
    }

    public Produtoscomanda(Integer id, int idComanda, int idProduto) {
        this.id = id;
        this.idComanda = idComanda;
        this.idProduto = idProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtoscomanda)) {
            return false;
        }
        Produtoscomanda other = (Produtoscomanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Produtoscomanda[ id=" + id + " ]";
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
}
