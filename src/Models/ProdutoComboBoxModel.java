package Models;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoComboBoxModel extends AbstractListModel<Produtos> implements ComboBoxModel<Produtos> {

    private List<Produtos> produtos;
    private Produtos selectedProduto;

    public ProdutoComboBoxModel(List<Produtos> produtos) {
        this.produtos = produtos;
        if (!produtos.isEmpty()) {
            selectedProduto = produtos.get(0);
        }

    }

    @Override
    public int getSize() {
        return produtos.size();
    }

    @Override
    public Produtos getElementAt(int index) {
        return produtos.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedProduto = (Produtos) anItem;
        fireContentsChanged(this, -1, -1);
    }

    @Override
    public Object getSelectedItem() {
        return selectedProduto;
    }
}
