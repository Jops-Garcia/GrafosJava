/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
import java.util.ArrayList;
public class Grafos {
    float [][]matriz;
    //Lista de nomes dos nos
    ArrayList<String> nodes;
    int tamanho;

    public Grafos(int tam) {
        //Para inicializar a matriz com o tamanho dito pelo arquivo
        this.tamanho = tam;
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

    //get the vertex with minimum distance which is not included in SPT
    public int getMinimumVertex(boolean [] mst, float [] key){
        float minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i <tamanho ; i++) {
            if(mst[i]==false && minKey>key[i]){
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }
   
    public void dijkstra_GetMinDistances(int sourceVertex){
        boolean[] spt = new boolean[tamanho];
        float [] distance = new float[tamanho];
        float INFINITY = Integer.MAX_VALUE;
    
        //Initialize all the distance to infinity
        for (int i = 0; i <tamanho ; i++) {
            distance[i] = INFINITY;
        }
    
        //start from the vertex 0
        distance[sourceVertex] = 0;
    
        //create SPT
        for (int i = 0; i <tamanho ; i++) {
    
            //get the vertex with the minimum distance
            int vertex_U = getMinimumVertex(spt, distance);
        
            //include this vertex in SPT
            spt[vertex_U] = true;
        
            //iterate through all the adjacent tamanho of above vertex and update the keys
            for (int vertex_V = 0; vertex_V <tamanho ; vertex_V++) {
                //check of the edge between vertex_U and vertex_V
                if(matriz[vertex_U][vertex_V]>0){
                    //check if this vertex 'vertex_V' already in spt and
                    // if distance[vertex_V]!=Infinity
                
                    if(spt[vertex_V]==false && matriz[vertex_U][vertex_V]!=INFINITY){
                        //check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                    
                        float newKey = matriz[vertex_U][vertex_V] + distance[vertex_U];
                        if(newKey<distance[vertex_V])
                        distance[vertex_V] = newKey;
                    }
                }
            }
        }
        //print shortest path tree
        printDijkstra(sourceVertex, distance);
    }
   
    public void printDijkstra(int sourceVertex, float [] key){
        System.out.println("Dijkstra Algorithm: (Adjacency matriz)");
        for (int i = 0; i <tamanho ; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + + i +
            " distance: " + key[i]);
        }
    }
}