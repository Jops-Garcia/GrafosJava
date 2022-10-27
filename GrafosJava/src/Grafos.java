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

public void insert(int lin,int col, int value){
    this.matriz[lin][col]=value;
}

public void insertNode(String name){
    this.nodes.add(name);
}

public void vizinhos(int cidade){

}

public void caminhos(){

}

public void printNodes(){
    for(int i=0;i<this.nodes.size();i++){
        System.out.println(this.nodes.get(i));
    }
}
}
