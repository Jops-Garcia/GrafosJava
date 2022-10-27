/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
import java.util.ArrayList;
public class Grafos {
    float [][]matriz;
    //Lista de nomes dos nos
    ArrayList<String> nodes;


public Grafos(int tamanho) {
    //Para inicializar a matriz com o tamanho dito pelo arquivo
    this.matriz=new float[tamanho][tamanho];
    this.nodes=new ArrayList<String>();
}

public void insert(int lin,int col, float value){
    //Insere na matriz
    this.matriz[lin][col]=value;
}

public void insertNode(String name){
    //Insere nome do nó na array
    this.nodes.add(name);
}

public void vizinhos(int cidade){
    //Printa a cidade selecionada com o codigo
    System.out.println("Code: "+(cidade+1)+" Node: "+this.nodes.get(cidade));
    System.out.println("\nVizinhos:");
    //Percorre a linha da matriz
      for (int c = 0; c < this.matriz[cidade].length; c++)
      {
        //Verifica se o os caminhos sao nulos
        if (this.matriz[cidade][c]!=0.0){
            //Printa as cidades vizinhas
            System.out.println("Code: "+(c+1)+" Node: "+this.nodes.get(c)+" Paths: "+this.matriz[cidade][c]);
        }
      }
      System.out.println();
}

public void caminhos(int cidade,ArrayList<String> cidades){//A array serve para saber os nós que já foram percorridos
    
    // if (cidades.size()<0){
    //     return;
    // }
    //Removendo a atual
    cidades.remove(nodes.get(cidade));
    
    //Percorrer a linha da matriz
    for (int c = 0; c < matriz[cidade].length; c++)
    {
        //Verificando se o caminho nao existe
        if (this.matriz[cidade][c]!=0.0){
            if (cidades.contains(nodes.get(c))){// Verifica se contem o nó na array
                System.out.println("Code: "+(c+1)+" Node: "+nodes.get(c));
                caminhos(c,cidades);//Chama recursão
            }
        }
    }
}

public void printMatriz(){
    //Cria string para ser preenchida
    String line = new String();
    for (int l = 0; l < this.matriz.length; l++)
    {
        line ="";
      for (int c = 0; c < this.matriz[0].length; c++)
      {
        //Adiciona o elemento na string para print
        line+=";"+matriz[l][c];
      }
      //Formatando e printando
      System.out.println(line.substring(1));
    }
}

public void printNodes(){

    for(int i=0;i<this.nodes.size();i++){
        //Pegando o codigo do nó, seu nome e printando
        System.out.println("Code: "+(i+1)+" Node: "+this.nodes.get(i));
    }
}
}
