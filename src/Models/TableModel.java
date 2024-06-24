package Models;

import Controllers.ProdutoController;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private List<Produtos> produtos;
    private String[] colunas = {"Código", "Produto", "Quantidade", "Preço"};

    public TableModel(List<Produtos> produtos) {
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
                return produto.getQuantidade();
            case 3:

                return produto.getPreco();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Produtos produto = produtos.get(rowIndex);
        ProdutoController PController = new ProdutoController();
        int objectInt = (Integer) value;
        switch (columnIndex) {
            case 0 -> {
            }
            case 1 -> {
            }
            case 2 -> {
            }
            case 3 -> produto.setQuantidade(5);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permitir a edição em todas as células
        return true;
    }

 
}
