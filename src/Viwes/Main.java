/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Viwes;

import Controllers.ComandaController;
import Controllers.ItemVendaController;
import Controllers.ProdutoController;
import Controllers.VendasController;
import Models.Comandas;
import Models.DadosItemVenda;
import Models.Produtos;
import Models.TableModel;
import Models.TableModelComandas;
import Models.TableModelComandasFechadas;
import Models.Vendas;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Andre.infra
 */
public class Main extends javax.swing.JFrame {

    private static List<Produtos> listaDeProdutos = new ArrayList<>();
    private static ComandaController ComandaController = new ComandaController();
    
    private static List<Comandas> ListaComandas = ComandaController.findComadasAbertas();
    private static List<Comandas> ListaComandasFechadas = ComandaController.findComadasFechadas();
    
    private static TableModel modelo = new TableModel(listaDeProdutos);
    private static TableModelComandas ModeloComanda = new TableModelComandas(ListaComandas);
    private static TableModelComandasFechadas ModeloComandaFechada = new TableModelComandasFechadas(ListaComandasFechadas);
    /**
     * Creates new form Main
     */
    public Main() {

        initComponents();
        setLocationRelativeTo(null);
        //TableModel modelo = new TableModel(listaDeProdutos);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Arial", Font.PLAIN, 18));
        tabela.setShowVerticalLines(true);
        tabela.setShowHorizontalLines(true);
        tabela.setGridColor(Color.gray);
        // Adicione a tabela a um JScrollPane (opcional)
        jScrollPane1.setViewportView(tabela);

        // Definindo modelo da tabela de comandas
        TabelaComanda.setModel(ModeloComanda);
        TabelaComanda.setFont(new Font("Arial", Font.PLAIN, 18));
        TabelaComanda.setShowVerticalLines(true);
        TabelaComanda.setShowHorizontalLines(true);
        TabelaComanda.setGridColor(Color.gray);
        
        TabelaComandaFechada.setModel(ModeloComandaFechada);
        TabelaComandaFechada.setFont(new Font("Arial", Font.PLAIN, 18));
        TabelaComandaFechada.setShowVerticalLines(true);
        TabelaComandaFechada.setShowHorizontalLines(true);
        TabelaComandaFechada.setGridColor(Color.gray);
        /*     TabelaComanda.getSelectionModel().addListSelectionListener(new ListSelectionListener(){;
        @Override
        public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
        int selectedRow = TabelaComanda.getSelectedRow();
        if (selectedRow != -1) { // -1 significa que nenhuma linha está selecionada
        // Aqui você pode chamar sua função, por exemplo:
        System.out.println("Row " + selectedRow + " selected.");
        }
        }
        }
        });*/

        TabelaComanda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar se foi um clique duplo
                    int row = TabelaComanda.getSelectedRow(); // Obter a linha selecionada
                    int column = TabelaComanda.getSelectedColumn(); // Obter a coluna selecionada
                    Object value = TabelaComanda.getValueAt(row, 1); // Obter o valor da célula
                    Object NomeComanda = TabelaComanda.getValueAt(row, 0);
                    Object DataAbertura = TabelaComanda.getValueAt(row, 2);
                    int IdComanda = Integer.parseInt("" + value);

                    EditarComanda comanda = new EditarComanda(IdComanda, "" + NomeComanda, ""+DataAbertura);
                    comanda.setVisible(rootPaneCheckingEnabled);
                    comanda.setLocationRelativeTo(jTabbedPane2);
                    comanda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    comanda.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            int resposta = JOptionPane.showConfirmDialog(comanda, "Você desja cancelar ?\nCaso cancele os items não serão adicioanados a comanda e voltarão para o estoque", "Atenção!", JOptionPane.OK_CANCEL_OPTION);

                            if (resposta == JOptionPane.YES_OPTION) {
                                comanda.CancelarInserção();
                                comanda.dispose();
                            } else {
                                comanda.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            }
                        }
                    });
                }
            }
        });
        
                TabelaComandaFechada.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar se foi um clique duplo
                    int row = TabelaComandaFechada.getSelectedRow(); // Obter a linha selecionada
                    int column = TabelaComandaFechada.getSelectedColumn(); // Obter a coluna selecionada
                    Object value = TabelaComandaFechada.getValueAt(row, 1); // Obter o valor da célula
                    Object NomeComanda = TabelaComandaFechada.getValueAt(row, 0);
                    Object DataAbertura = TabelaComandaFechada.getValueAt(row, 2);
                    int IdComanda = Integer.parseInt("" + value);

                    ComandaFechadaProdutos comanda = new ComandaFechadaProdutos(IdComanda, "" + NomeComanda, ""+DataAbertura);
                    comanda.setVisible(rootPaneCheckingEnabled);
                    comanda.setLocationRelativeTo(jTabbedPane2);
                    comanda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                }
            }
        });
        jScrollPane2.setViewportView(TabelaComanda);
    }
    
    public static void removerItemTabelaComandas(Comandas comanda) {
        int controle = 0;
        for(Comandas comandaBusca : ListaComandas){
            if(comandaBusca.getCodigo() == comanda.getCodigo()){
            ListaComandas.remove(controle);
            break;
            }
        }
        
        ModeloComanda.fireTableDataChanged();
       
    }
    
    public static void removerItemTabelaComandasFechadas(Comandas comanda) {
        ListaComandas.remove(comanda);
        ModeloComanda.fireTableDataChanged();
    }
   
    
    public static void adicionarItemTabelaComandas(Comandas comanda) {
        ListaComandas.add(comanda);
        ModeloComanda.fireTableDataChanged();
    }
    
     public static void adicionarItemTabelaComandasFechadas(Comandas comanda){
        ListaComandasFechadas.add(comanda);
        ModeloComandaFechada.fireTableDataChanged();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        choice1 = new java.awt.Choice();
        jPanel1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalCarrinho = new javax.swing.JLabel();
        botaoFinalizarVenda = new javax.swing.JButton();
        botaoAdicionarProduto = new javax.swing.JButton();
        formePagamento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaComanda = new javax.swing.JTable();
        TituloComanda = new javax.swing.JLabel();
        CriarComandaBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaComandaFechada = new javax.swing.JTable();
        ComandaFechadaTitle = new javax.swing.JLabel();
        atualizarTabelaComandasFechadasBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shopline PDV");
        setMinimumSize(new java.awt.Dimension(1200, 710));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        Title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Title.setText("ShopLine PDV");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 204, 0));
        jLabel7.setText("$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Title)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Carrinho");

        tabela.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabela.setShowGrid(true);
        jScrollPane1.setViewportView(tabela);

        jButton4.setText("Consultar Produto");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Venda");

        jButton5.setText("Consultar Registro de Vendas");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("Total:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Historico:");

        totalCarrinho.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        totalCarrinho.setForeground(new java.awt.Color(102, 255, 102));
        totalCarrinho.setText("R$0");

        botaoFinalizarVenda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botaoFinalizarVenda.setText("Finalizar Venda");
        botaoFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarVendaActionPerformed(evt);
            }
        });

        botaoAdicionarProduto.setText("Adicionar produto ao carrinho");
        botaoAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarProdutoActionPerformed(evt);
            }
        });

        formePagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Cartão de crédito", "PIX" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Forma de pagamento");

        jButton2.setText("Deletar Produto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Alterar Produto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(totalCarrinho)
                        .addGap(12, 12, 12))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAdicionarProduto)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(formePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venda Única", jPanel2);

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        TabelaComanda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TabelaComanda);

        TituloComanda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TituloComanda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloComanda.setText("Comandas em aberto");

        CriarComandaBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CriarComandaBtn.setText("Abrir uma nova comanda");
        CriarComandaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CriarComandaBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Clique duas vezes na comanda para editar ou encerrar ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TituloComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CriarComandaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(TituloComanda)
                .addGap(53, 53, 53)
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(CriarComandaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jTabbedPane2.addTab("Comandas Abertas", jPanel6);

        TabelaComandaFechada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(TabelaComandaFechada);

        ComandaFechadaTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ComandaFechadaTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ComandaFechadaTitle.setText("Comandas Fechadas");

        atualizarTabelaComandasFechadasBtn.setText("Atualizar Tabela");
        atualizarTabelaComandasFechadasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarTabelaComandasFechadasBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComandaFechadaTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(atualizarTabelaComandasFechadasBtn)
                .addGap(128, 128, 128))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(ComandaFechadaTitle)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarTabelaComandasFechadasBtn))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Comandas Fechadas", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Comandas", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1099, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configurações", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botaoAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarProdutoActionPerformed

        AdicionarProduto add = new AdicionarProduto();
        add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_botaoAdicionarProdutoActionPerformed
    
   
    
    private void botaoFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizarVendaActionPerformed
        double valorTotal = valorTotalCarrinho();
        if (valorTotal == 0) {
            JOptionPane.showMessageDialog(this, "Parece que seu carrinho está vazio, adicione produtos e tente novamente", "Ops Carrinho vazio", JOptionPane.ERROR_MESSAGE);
        } else {
            ItemVendaController dados = new ItemVendaController();

            String FormaPagamento = (String) formePagamento.getSelectedItem();
            dados.finalizarCompra(listaDeProdutos, valorTotal, FormaPagamento);
            ProdutoController PController = new ProdutoController();
            JOptionPane.showMessageDialog(this, "Sua compra no valor de R$" + valorTotal + " Foi realizada com sucesso!", "Compra efetivada!", JOptionPane.DEFAULT_OPTION);
            PController.diminiorEtoque(listaDeProdutos);
            listaDeProdutos.clear();
            totalCarrinho.setText("R$0");
            modelo.fireTableDataChanged();
        }
    }//GEN-LAST:event_botaoFinalizarVendaActionPerformed

    private void CriarComandaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CriarComandaBtnActionPerformed
        NovaComanda comanda = new NovaComanda();
        comanda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        comanda.setLocationRelativeTo(this);
        comanda.setVisible(true);
        comanda.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(comanda, "Você desja cancelar ?\nCaso cancele os items não serão adicioanados a comanda e voltarão para o estoque", "Atenção!", JOptionPane.OK_CANCEL_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    comanda.CancelarInserção();
                    comanda.dispose();
                } else {
                    comanda.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }//GEN-LAST:event_CriarComandaBtnActionPerformed

    private void atualizarTabelaComandasFechadasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarTabelaComandasFechadasBtnActionPerformed
        // TODO add your handling code here:
        ModeloComandaFechada.fireTableDataChanged();
    }//GEN-LAST:event_atualizarTabelaComandasFechadasBtnActionPerformed

    private static boolean existeNoCarrinho(Produtos produto) {
        for (Produtos produtoLista : listaDeProdutos) {
            if (produtoLista.getCodigo().equals(produto.getCodigo())) {
                System.out.println("Item já está no carrinho");
                return true;
            }
        }
        return false;
    }

    private static double valorTotalCarrinho() {
        double valorTotal = 0;

        for (Produtos produtoLista : listaDeProdutos) {
            double valor;
            int quantidade;
            valor = produtoLista.getPreco();
            quantidade = produtoLista.getQuantidade();
            valorTotal += valor * quantidade;
        }

        return valorTotal;
    }

    public static void adicionarProduto(Produtos produto) {
        if (!existeNoCarrinho(produto)) {

            listaDeProdutos.add(produto);
            modelo.fireTableDataChanged();
            totalCarrinho.setText(String.valueOf("R$" + valorTotalCarrinho()));
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ComandaFechadaTitle;
    private javax.swing.JButton CriarComandaBtn;
    private javax.swing.JTable TabelaComanda;
    private javax.swing.JTable TabelaComandaFechada;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel TituloComanda;
    private javax.swing.JButton atualizarTabelaComandasFechadasBtn;
    private javax.swing.JButton botaoAdicionarProduto;
    private javax.swing.JButton botaoFinalizarVenda;
    private java.awt.Choice choice1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox<String> formePagamento;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tabela;
    private static javax.swing.JLabel totalCarrinho;
    // End of variables declaration//GEN-END:variables
}
