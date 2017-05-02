
package projetobluepixel;

import java.util.List;



public class Carrinho {
    
    String data;
    private List<Produto> itens;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
   
}
