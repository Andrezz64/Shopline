/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Andre.infra
 */
@Entity
@Table(name = "DadosItemVenda")
@NamedQueries({
    @NamedQuery(name = "DadosItemVenda.findAll", query = "SELECT d FROM DadosItemVenda d"),
    @NamedQuery(name = "DadosItemVenda.findById", query = "SELECT d FROM DadosItemVenda d WHERE d.id = :id"),
    @NamedQuery(name = "DadosItemVenda.findByNumeroItem", query = "SELECT d FROM DadosItemVenda d WHERE d.numeroItem = :numeroItem"),
    @NamedQuery(name = "DadosItemVenda.findByCodigo", query = "SELECT d FROM DadosItemVenda d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DadosItemVenda.findByQuantVend", query = "SELECT d FROM DadosItemVenda d WHERE d.quantVend = :quantVend"),
    @NamedQuery(name = "DadosItemVenda.findByPreco", query = "SELECT d FROM DadosItemVenda d WHERE d.preco = :preco"),
    @NamedQuery(name = "DadosItemVenda.findByTotalItem", query = "SELECT d FROM DadosItemVenda d WHERE d.totalItem = :totalItem"),
    @NamedQuery(name = "DadosItemVenda.findByVendaId", query = "SELECT d FROM DadosItemVenda d WHERE d.vendaId = :vendaId")})
public class DadosItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numeroItem")
    private Integer numeroItem;
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "quant_Vend")
    private Integer quantVend;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
    private Double preco;
    @Column(name = "totalItem")
    private Integer totalItem;
    @Column(name = "venda_id")
    private Integer vendaId;

    public DadosItemVenda() {
    }

    public DadosItemVenda(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantVend() {
        return quantVend;
    }

    public void setQuantVend(Integer quantVend) {
        this.quantVend = quantVend;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
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
        if (!(object instanceof DadosItemVenda)) {
            return false;
        }
        DadosItemVenda other = (DadosItemVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.DadosItemVenda[ id=" + id + " ]";
    }
    
}
