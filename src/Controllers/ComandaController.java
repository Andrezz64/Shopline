// Classe de controller para gerencia o banco de dados apartir das entitys
package Controllers;

import Models.Comandas;
import Models.Produtos;
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
public class ComandaController {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopLinePU");
    private final EntityManager em = emf.createEntityManager();

    // Procura por um produto com um ID especifico
    public Comandas findOne(int id) {
        em.getTransaction().begin();
        Comandas entity = em.find(Comandas.class, 1);
        em.getTransaction().commit();

        Integer IdComanda = entity.getIdComanda();
        String NomeCliente = entity.getNomeCliente();
        String Status = entity.getStatus();

        if (entity != null) {

            Comandas Comanda = new Comandas();
            Comanda.setIdComanda(IdComanda);
            Comanda.setNomeCliente(NomeCliente);
            Comanda.setStatus(Status);

            em.close();
            return Comanda;

        } else {
            System.out.println("Produto não encontrado.");
        }
        em.close();
        return null;

    }

    public int ultimoCódigo() {
        int id = 0;
        em.getTransaction().begin();
        TypedQuery<Integer> query = em.createQuery("SELECT MAX(e.codigo) FROM Comandas e", Integer.class);
        Integer Resultado = query.getSingleResult();
        if (Resultado != null) {
            id = Resultado;
        }
        if (em.getTransaction() != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        } else {
            em.close();
            emf.close();
        }

        return id;
    }

 public int findIdByCodigo(int codigo) {
         EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("shopLinePU");
          EntityManager em2 = emf2.createEntityManager();   
     try {
          
            em2.getTransaction().begin();
            String jpql = "SELECT p FROM Comandas p WHERE p.codigo = :codigo";
            TypedQuery<Comandas> query = em2.createQuery(jpql, Comandas.class);
            query.setParameter("codigo", codigo);
            
            List<Comandas> resultados = query.getResultList();
            em2.getTransaction().commit();
            em2.close();
            if (!resultados.isEmpty()) {
                Comandas comandaFinal = resultados.get(0);
                return comandaFinal.getCodigo();
            } else {
                return -1; // ou algum outro valor padrão para indicar que nenhum resultado foi encontrado
            }
        } catch (Exception e) {
            System.out.println(e); 
            throw e; // ou trate a exceção de alguma forma apropriada
        } 
    }

    public Comandas findByNomeCliente(int NomeCliente) {
        String jpql = "SELECT p FROM comandas p WHERE p.nome_cliente = :nome_cliente";
        Comandas ComandaFinal;
        em.getTransaction().begin();
        var query = em.createQuery(jpql, Comandas.class);
        query.setParameter("nome_cliente", NomeCliente);

        List<Comandas> resultados = query.getResultList();

        em.getTransaction().commit();
        ComandaFinal = resultados.get(0);

        return ComandaFinal;
    }
    
    

    // Lista todos os produtos da base
    public List<Comandas> findComadasAbertas() {

        em.getTransaction().begin();

        String jpql = "SELECT p FROM Comandas p WHERE p.status = 'aberto'"; // Consulta JPQL para selecionar todos os registros
        TypedQuery<Comandas> query = em.createQuery(jpql, Comandas.class);

        List<Comandas> Comandas = query.getResultList(); // Obtém todos os registros da tabela

        em.getTransaction().commit();
        em.close();
        emf.close();
        return Comandas;
    }

    // Cria uma nova entry de produto.
    public int createOne(String NomeCliente) {

        Comandas NovaComanda = new Comandas();
        NovaComanda.setNomeCliente(NomeCliente);
        NovaComanda.setStatus("aberto");
        NovaComanda.setCodigo(ultimoCódigo() + 1);
        em.getTransaction().begin();
        em.persist(NovaComanda);
        em.getTransaction().commit();
        em.close();

        return NovaComanda.getCodigo();
    }
    // exclui um produto pelo ID.

    public void deleteOne(int id) {
        em.getTransaction().begin();

        Comandas ComadnaDeleted = em.find(Comandas.class, id);
        if (ComadnaDeleted != null) {
            em.remove(ComadnaDeleted);
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
