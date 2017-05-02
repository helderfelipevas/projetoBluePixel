
package projetobluepixel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import projetobluepixel.Principal;



public class ProjetoBluePixel {

    private final String USER_AGENT = "Chrome/52.0.2743.116";
    
    public static void main(String[] args) throws Exception {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        ProjetoBluePixel pro = new ProjetoBluePixel();
                
        pro.Consumo();
        
        
    }

    public List Consumo() throws Exception{
                       
        List itens = new ArrayList();

        ProjetoBluePixel http = new ProjetoBluePixel();
        String chamadaWS;
        
        chamadaWS = "http://bpixel.com.br/teste/itens.json";
        String json = http.sendGet(chamadaWS);
                
//      System.out.println(json);
        
        //cria os objetos
               
        JSONObject jsonObj = new JSONObject(json);
        System.out.println(jsonObj.getString("data"));
        String data = jsonObj.getString("data");
        
//----------------------------------------------------------------
      
        //Captura os valores do array
            
            JSONArray jarr= (JSONArray) jsonObj.get("itens");
            
            
            for(int i = 0; i<jarr.length();i++){
                 
                JSONObject obj = (JSONObject) jarr.get(i);
                
                String nome = obj.getString("nome");
                String foto = obj.getString("foto");
                String descricao = obj.getString("descricao");
                //String[] textoSeparado = desc.split(" Preço: ");
                //String descricao = textoSeparado[0];
                //String preco = textoSeparado[1].replace(".", "");

                
                Carrinho car = new Carrinho();
                
                
           
                itens.add(nome);
                itens.add(foto);
                itens.add(descricao);
                //itens.add(preco);
               
                
                car.setData(data);
                car.setItens(itens);
                
                
                //System.out.println(itens.get(0));
                //System.out.println(nome);
                //System.out.println(foto);
                //System.out.println(descricao);
                //System.out.println(preco);
                //System.out.println("----" + car.getItens());
                
            }
            return itens;
    }
    
    //Consome WS
    
    private String sendGet(String url) throws Exception {
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("GET");
        
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String inputLine;
        
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
       return response.toString(); 
    }
    
    //Pegar hora do sistema
    public String getDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
}
    
    //HEADSORT
    
    public void heapsort(String[] v) {
        constroiMaximaOrdem(v);
        int n = v.length;

        for (int i = v.length - 1; i > 0; i--) {
            swap(v, i, 0);
            ordenaDescendo(v, 0, --n);
        }
    }

    private static void constroiMaximaOrdem(String[] v) {
        for (int i = v.length / 2 - 1; i >= 0; i--)
            ordenaDescendo(v, i, v.length);
    }

    private static void ordenaDescendo(String[] vetor, int pos, int tamanhoDoVetor) {
        // obtém as posições dos filhos
        int max = 2 * pos + 1, right = max + 1;
        if (max < tamanhoDoVetor) {
            // verifica qual dos dois filhos possuem o maior valor
            if ((right < tamanhoDoVetor) && (vetor[max].compareTo(vetor[right])) < 0)
                max = right;
            // verifica se o filho com o mair valor tem um valor maior que o pai
            if (vetor[max].compareTo(vetor[pos]) > 0) {
                swap(vetor, max, pos);
                ordenaDescendo(vetor, max, tamanhoDoVetor);
            }
        }
    }

    // faz a troca de dos valores
    public static void swap(String[] v, int j, int aposJ) {
        String aux = v[j];
        v[j] = v[aposJ];
        v[aposJ] = aux;
    }
    
}
