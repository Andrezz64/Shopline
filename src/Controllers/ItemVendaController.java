// Classe de controller para gerencia o banco de dados apartir das entitys
package Controllers;

import Models.DadosItemVenda;
import Models.Produtos;
import Models.Vendas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre.infra
 */
public class ItemVendaController {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopLinePU");
    private final EntityManager em = emf.createEntityManager();

    // Procura por um produto com um ID especifico
    public DadosItemVenda findOne(int id) {
        em.getTransaction().begin();
        DadosItemVenda entity = em.find(DadosItemVenda.class, 1);
        em.getTransaction().commit();

        int codigo = entity.getCodigo();
        int numeroItem = entity.getNumeroItem();
        int quantidadeVendida = entity.getQuantVend();
        double preco = entity.getPreco();
        int totalItem = entity.getTotalItem();
        int VendaId = entity.getVendaId();

        if (entity != null) {

            DadosItemVenda dadosVenda = new DadosItemVenda();
            dadosVenda.setCodigo(codigo);
            dadosVenda.setNumeroItem(numeroItem);
            dadosVenda.setQuantVend(VendaId);
            dadosVenda.setPreco(preco);
            dadosVenda.setTotalItem(totalItem);
            dadosVenda.setVendaId(VendaId);

            em.close();
            return dadosVenda;

        } else {
            System.out.println("Venda não encontrado.");
        }
        em.close();
        return null;

    }

    // Lista todos os DadosItemVenda da base
    public List<DadosItemVenda> findMany() {

        em.getTransaction().begin();

        String jpql = "SELECT p FROM DadosItemVenda p"; // Consulta JPQL para selecionar todos os registros
        TypedQuery<DadosItemVenda> query = em.createQuery(jpql, DadosItemVenda.class);

        List<DadosItemVenda> DadosItemVenda = query.getResultList(); // Obtém todos os registros da tabela

        em.getTransaction().commit();
        em.close();
        emf.close();
        return DadosItemVenda;
    }
    
    public boolean finalizarCompra(List<Produtos> listaDeProdutos, double valorTotal, String metodoPagamento){
        int id;
        int control = 0;
        
      VendasController vendasControl = new VendasController();
        
       try{
        id = vendasControl.createOne(valorTotal, metodoPagamento);
     
        for(Produtos produto : listaDeProdutos){
           EntityManager entity = emf.createEntityManager();
            entity.getTransaction().begin();
         
            
            int quantidade = produto.getQuantidade();
            double preco = produto.getPreco();
            int totalItem = (int) (quantidade * preco);
            int codigo = produto.getCodigo();
            
            
            DadosItemVenda dadosVenda = new DadosItemVenda();
            dadosVenda.setCodigo(codigo);
            dadosVenda.setNumeroItem(control);
            dadosVenda.setQuantVend(quantidade);
            dadosVenda.setPreco(preco);
            dadosVenda.setTotalItem(totalItem);
            dadosVenda.setVendaId(id);
            
            
            entity.persist(dadosVenda);
            entity.getTransaction().commit();
            
            
             
            control +=1;
        }
       em.close();
       return true;
       }
       catch(Exception e){
        e.printStackTrace();
        return false;
       }
    }
    
    
    // Cria uma nova entry de produto.
    public void createOne(int codigo, int numeroItem, int VendaId, double preco, int totalItem) {

        DadosItemVenda dadosVenda = new DadosItemVenda();
        dadosVenda.setCodigo(codigo);
        dadosVenda.setNumeroItem(numeroItem);
        dadosVenda.setQuantVend(VendaId);
        dadosVenda.setPreco(preco);
        dadosVenda.setTotalItem(totalItem);
        dadosVenda.setVendaId(VendaId);
        em.getTransaction().begin();
        em.persist(dadosVenda);
        em.getTransaction().commit();
        em.close();
    }
    // exclui um produto pelo ID.

    public void deleteOne(int id) {
        em.getTransaction().begin();

        DadosItemVenda vendasDeleted = em.find(DadosItemVenda.class, id);
        if (vendasDeleted != null) {
            em.remove(vendasDeleted);
            em.getTransaction().commit();
            em.close();
        }

    }

    public void changeOne(int id, DadosItemVenda vendasData) {
        em.getTransaction().begin();
        DadosItemVenda vendasChanged = em.find(DadosItemVenda.class, id);

        if (vendasChanged != null) {

            vendasChanged.setCodigo(vendasData.getCodigo());
            vendasChanged.setPreco(vendasData.getPreco());
            vendasChanged.setNumeroItem(vendasData.getNumeroItem());
            vendasChanged.setTotalItem(vendasData.getTotalItem());
            vendasChanged.setVendaId(vendasData.getVendaId());
            vendasChanged.setQuantVend(vendasData.getQuantVend());

        }

        em.merge(vendasChanged);
        em.getTransaction().commit();
        em.close();
    }
}
