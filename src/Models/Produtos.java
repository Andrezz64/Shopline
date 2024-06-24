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
import javax.persistence.Transient;

/**
 *
 * @author Andre.infra
 */
@Entity
@Table(name = "Produtos")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findById", query = "SELECT p FROM Produtos p WHERE p.id = :id"),
    @NamedQuery(name = "Produtos.findByCodigo", query = "SELECT p FROM Produtos p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Produtos.findByNome", query = "SELECT p FROM Produtos p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produtos.findByUnidade", query = "SELECT p FROM Produtos p WHERE p.unidade = :unidade"),
    @NamedQuery(name = "Produtos.findByPreco", query = "SELECT p FROM Produtos p WHERE p.preco = :preco"),
    @NamedQuery(name = "Produtos.findByQuantidadeDisponivel", query = "SELECT p FROM Produtos p WHERE p.quantidadeDisponivel = :quantidadeDisponivel"),
    @NamedQuery(name = "Produtos.findByDataUltimaVenda", query = "SELECT p FROM Produtos p WHERE p.dataUltimaVenda = :dataUltimaVenda"),
    @NamedQuery(name = "Produtos.findByVendaId", query = "SELECT p FROM Produtos p WHERE p.vendaId = :vendaId"),
    @NamedQuery(name = "Produtos.findByDadosItemVendaid", query = "SELECT p FROM Produtos p WHERE p.dadosItemVendaid = :dadosItemVendaid")})
public class Produtos implements Serializable {
    @Transient
    private int quantidade;
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "unidade")
    private Integer unidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
    private Double preco;
    @Column(name = "quantidade_disponivel")
    private Integer quantidadeDisponivel;
    @Column(name = "dataUltimaVenda")
    private String dataUltimaVenda;
    @Column(name = "venda_id")
    private Integer vendaId;
    @Column(name = "dadosItemVenda_id")
    private Integer dadosItemVendaid;

    public Produtos() {
       
    }

    public Produtos(Integer id) {
        this.id = id;
    }
    
    public void setQuantidade(int quantidade){
    this.quantidade = quantidade;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getUnidade() {
        return unidade;
    }

    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getDataUltimaVenda() {
        return dataUltimaVenda;
    }

    public void setDataUltimaVenda(String dataUltimaVenda) {
        this.dataUltimaVenda = dataUltimaVenda;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getDadosItemVendaid() {
        return dadosItemVendaid;
    }

    public void setDadosItemVendaid(Integer dadosItemVendaid) {
        this.dadosItemVendaid = dadosItemVendaid;
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
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + " | " + nome + " | " +" Valor: R$" + preco;
    }
    
}
