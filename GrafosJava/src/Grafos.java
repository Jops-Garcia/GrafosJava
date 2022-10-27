/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
import java.util.ArrayList;
public class Grafos {
    float [][]matriz;
    //lista de nomes dos nos
    ArrayList<String> nodes;


public Grafos(int tamanho) {
    //para inicializar a matriz com o tamanho dito pelo arquivo
    this.matriz=new float[tamanho][tamanho];
    this.nodes=new ArrayList<String>();
}

public void insert(int lin,int col, float value){
    //Insere na matriz
    this.matriz[lin][col]=value;
}

public void insertNode(String name){
    //insere nome do nó na array
    this.nodes.add(name);
}

public void vizinhos(int cidade){

    System.out.println("Code: "+(cidade+1)+" Node: "+this.nodes.get(cidade));
    System.out.println("\nVizinhos:");
      for (int c = 1; c < this.matriz[cidade].length; c++)
      {
        if (this.matriz[cidade][c]!=0.0){
            System.out.println("Code: "+(c+1)+" Node: "+this.nodes.get(c)+" Paths: "+this.matriz[cidade][c]);
        }
      }
      System.out.println();
}

public void caminhos(){

}

public void printMatriz(){
    String line = new String();
    for (int l = 0; l < this.matriz.length; l++)
    {
        line ="";
      for (int c = 0; c < this.matriz[0].length; c++)
      {

        line+=";"+matriz[l][c];
      }
      //formatando
      System.out.println(line.substring(1));
    }
}

public void printNodes(){

    for(int i=0;i<this.nodes.size();i++){
        //pegando o codigo do nó
        System.out.println("Code: "+(i+1)+" Node: "+this.nodes.get(i));
    }
}
}
