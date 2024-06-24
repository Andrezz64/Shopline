package Models;

import Controllers.ProdutoController;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelComandas extends AbstractTableModel {

    private List<Comandas> Comandas;
    private String[] colunas = {"Cliente", "Código"};

    public TableModelComandas(List<Comandas> Comandas) {
        this.Comandas = Comandas;
    }

    @Override
    public int getRowCount() {
        return Comandas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Comandas Comanda = Comandas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return Comanda.getNomeCliente();
            case 1:
                return Comanda.getCodigo();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }



 
}
