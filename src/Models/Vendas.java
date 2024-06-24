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
@Table(name = "Vendas")
@NamedQueries({
    @NamedQuery(name = "Vendas.findAll", query = "SELECT v FROM Vendas v"),
    @NamedQuery(name = "Vendas.findById", query = "SELECT v FROM Vendas v WHERE v.id = :id"),
    @NamedQuery(name = "Vendas.findByDataVenda", query = "SELECT v FROM Vendas v WHERE v.dataVenda = :dataVenda"),
    @NamedQuery(name = "Vendas.findByValorTotal", query = "SELECT v FROM Vendas v WHERE v.valorTotal = :valorTotal")})
public class Vendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dataVenda")
    private String dataVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTotal")
    private Double valorTotal;
    @Column(name = "metodoPagamento")
    private String metodoPagamento;
    
    public Vendas() {
    }
    
    
    
    public Vendas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String getMetodoPagamento(){
        return metodoPagamento;
    }
    
    public void setMetodoPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
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
        if (!(object instanceof Vendas)) {
            return false;
        }
        Vendas other = (Vendas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Vendas[ id=" + id + " ]";
    }
    
}
