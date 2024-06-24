// Classe de controller para gerencia o banco de dados apartir das entitys
package Controllers;

import Models.Produtos;
import Models.Produtoscomanda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre.infra
 */
public class ProdutoController {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopLinePU");
    private final EntityManager em = emf.createEntityManager();

    // Procura por um produto com um ID especifico
    public Produtos findOne(int id) {
        em.getTransaction().begin();
        Produtos entity = em.find(Produtos.class, 1);
        em.getTransaction().commit();

        Integer codigo = entity.getCodigo();
        String nome = entity.getNome();
        Integer unidade = entity.getUnidade();
        Double preco = entity.getPreco();
        Integer quantidadeDisponivel = entity.getQuantidadeDisponivel();
        String dataUltimaVenda = entity.getDataUltimaVenda();

        if (entity != null) {

            Produtos produto = new Produtos();
            produto.setCodigo(codigo);
            produto.setNome(nome);
            produto.setUnidade(unidade);
            produto.setPreco(preco);
            produto.setQuantidadeDisponivel(quantidadeDisponivel);
            produto.setDataUltimaVenda(dataUltimaVenda);

            em.close();
            return produto;

        } else {
            System.out.println("Produto não encontrado.");
        }
        em.close();
        return null;

    }

    public int ultimoCódigo(){
        int id;
        em.getTransaction().begin();
        TypedQuery<Integer> query = em.createQuery("SELECT MAX(e.codigo) FROM Produtos e", Integer.class);
        id = query.getSingleResult();
        if(em.getTransaction() != null && em.getTransaction().isActive()){
        em.getTransaction().rollback();
        }
        else{
        em.close();
        emf.close();
        }


        
        return id;
    }
    
    public Produtos findProdutoByCodigo(int codigo){
       String jpql = "SELECT p FROM Produtos p WHERE p.codigo = :codigo";
        Produtos proutoFinal;
    em.getTransaction().begin();  
    var query = em.createQuery(jpql, Produtos.class);
    query.setParameter("codigo", codigo);

    List<Produtos> resultados = query.getResultList();

    em.getTransaction().commit();
    
    proutoFinal = resultados.get(0);
   
    return proutoFinal;
    }
    
        
    public Produtos findProdutoByNome(String nome){
       String jpql = "SELECT p FROM Produtos p WHERE p.nome LIKE :nome";
        Produtos proutoFinal;
    em.getTransaction().begin();  
    var query = em.createQuery(jpql, Produtos.class);
    query.setParameter("nome", "%"+nome+"%");

    List<Produtos> resultados = query.getResultList();

    em.getTransaction().commit();
    
    proutoFinal = resultados.get(0);
   
    return proutoFinal;
    }
    
        public void alterarEstoqueProdutoUnitario(Produtos Produto, int quantidadadeAtual){
        em.getTransaction().begin();
        Produto.setQuantidadeDisponivel(quantidadadeAtual);
            em.merge(Produto);
            em.getTransaction().commit();
            em.close();
    }
    
    public void diminiorEtoque(List<Produtos> listaDeProdutos) {
        for (Produtos produto : listaDeProdutos) {
            int quantidadeDispo = produto.getQuantidadeDisponivel();
            int quandidadeCarrihno = produto.getQuantidade();

            produto.setQuantidadeDisponivel(quantidadeDispo - quandidadeCarrihno);
            System.out.println("Produto" + produto.getNome() + "teve sua quantidade alterada para" + produto.getQuantidadeDisponivel());
            em.getTransaction().begin();
            Produtos produtoChanged = em.find(Produtos.class, produto.getId());
            if (produtoChanged != null) {

                produtoChanged.setCodigo(produto.getCodigo());
                produtoChanged.setNome(produto.getNome());
                produtoChanged.setUnidade(produto.getUnidade());
                produtoChanged.setPreco(produto.getPreco());
                produtoChanged.setQuantidadeDisponivel(produto.getQuantidadeDisponivel());
                produtoChanged.setDataUltimaVenda(produto.getDataUltimaVenda());
            }
            em.merge(produtoChanged);
            em.getTransaction().commit();
           
            
        }
         em.close();
    }

    // Lista todos os produtos da base
    public List<Produtos> findMany() {
       
        em.getTransaction().begin();

        String jpql = "SELECT p FROM Produtos p"; // Consulta JPQL para selecionar todos os registros
        TypedQuery<Produtos> query = em.createQuery(jpql, Produtos.class);

        List<Produtos> produtos = query.getResultList(); // Obtém todos os registros da tabela

        em.getTransaction().commit();
        em.close();
        emf.close();
        return produtos;
    }

    public  List<Produtos>  FindProdutosComanda(Integer id_comanda){
  
        List<Produtos> ListaDeProdutos = new ArrayList<>();
        em.getTransaction().begin();

        String jpql = "SELECT pc FROM Produtoscomanda pc WHERE pc.idComanda = :id_comanda"; // Consulta JPQL para selecionar todos os registros
        TypedQuery<Produtoscomanda> query = em.createQuery(jpql, Produtoscomanda.class);
        query.setParameter("id_comanda", id_comanda);
        List<Produtoscomanda> ProdutosComandaLista = query.getResultList(); // Obtém todos os registros da tabela

        em.getTransaction().commit();

             
        for(Produtoscomanda Produtos: ProdutosComandaLista ){
          Produtos ProdutoRow = findProdutoByCodigo(Produtos.getIdProduto());
          ProdutoRow.setQuantidade(Produtos.getQuantidade());
      
          ListaDeProdutos.add(ProdutoRow);
        }
        
        return ListaDeProdutos;
    

    }
    
    // Cria uma nova entry de produto.
    public void createOne(int codigo, String nome, int unidade, double preco, int quantidadeDisponivel, String dataUltimaVenda) {

        Produtos novoProduto = new Produtos();
        novoProduto.setCodigo(codigo);
        novoProduto.setNome(nome);
        novoProduto.setUnidade(unidade);
        novoProduto.setPreco(preco);
        novoProduto.setQuantidadeDisponivel(quantidadeDisponivel);
        novoProduto.setDataUltimaVenda(dataUltimaVenda);
        em.getTransaction().begin();
        em.persist(novoProduto);
        em.getTransaction().commit();
        em.close();
    }
    // exclui um produto pelo ID.

    public void deleteOne(int id) {
        em.getTransaction().begin();

        Produtos produtoDeleted = em.find(Produtos.class, id);
        if (produtoDeleted != null) {
            em.remove(produtoDeleted);
            em.getTransaction().commit();
            em.close();
        }

    }

    public void changeOne(int id, Produtos ProdutoData) {
        em.getTransaction().begin();
        Produtos produtoChanged = em.find(Produtos.class, id);

        if (produtoChanged != null) {

            produtoChanged.setCodigo(ProdutoData.getCodigo());
            produtoChanged.setNome(ProdutoData.getNome());
            produtoChanged.setUnidade(ProdutoData.getUnidade());
            produtoChanged.setPreco(ProdutoData.getPreco());
            produtoChanged.setQuantidadeDisponivel(ProdutoData.getQuantidadeDisponivel());
            produtoChanged.setDataUltimaVenda(ProdutoData.getDataUltimaVenda());
        }

        em.merge(produtoChanged);
        em.getTransaction().commit();
        em.close();
    }
}
