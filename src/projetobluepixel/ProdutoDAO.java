
package projetobluepixel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
                
    public void cadastrarProduto(String nome, String foto, String descricao, /*String preco,*/ String data){

        conn = Connect.ConnectDB();
                
        String query = "INSERT INTO produto (nm_produto, ds_foto_produto, ds_produto, dt_adicao) VALUES (?,?,?,?)";
        
        try {
            
            pst = conn.prepareStatement(query);
            
            pst.setString(1, nome);
            pst.setString(2, foto);
            pst.setString(3, descricao);
            //pst.setString(4, preco);
            pst.setString(4, data);
            
            pst.execute();
            
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
        
}
