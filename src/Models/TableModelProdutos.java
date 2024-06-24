package Models;

import Controllers.ProdutoController;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelProdutos extends AbstractTableModel {

    private List<Produtos> produtos;
    private String[] colunas = {"Código", "Nome", "Preço", "Quantidade Disponivel"};

    public TableModelProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produtos produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getCodigo();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getPreco();
            case 3:
                return produto.getQuantidadeDisponivel();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }



 
}
