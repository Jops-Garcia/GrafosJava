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

    //PEGA O VERTICE COM A DISTANCIA MINIMA QUE NAO ESTA INCLUSA NO SPT
    public int caminhoMinVertice(boolean [] mst, float [] key){
        //pega o maior inteiro possivel (mesmo sendo float o inteiro já é um numero bem grande)
        float minKey = Integer.MAX_VALUE;
        int vertice = -1;
        for (int i = 0; i <tamanho ; i++) {
            if(mst[i]==false && minKey>key[i]){
                minKey = key[i];
                vertice = i;
            }
        }
        return vertice;
    }
   
    public void distanciaMinima(int origem , int destino){
        boolean[] spt = new boolean[tamanho];
        float [] distancia = new float[tamanho];
        float INFINITO = Integer.MAX_VALUE;
        //COLOCA TODAS AS DISTANCIAS COMO "INFINITO" (maior inteiro)
        for (int i = 0; i <tamanho ; i++) {
            distancia[i] = INFINITO;
        }
        //INICIA OS VERTICE DE ORIGEM COMO 0
        distancia[origem] = 0;
        //CRIA SPT
        for (int i = 0; i <tamanho ; i++) {
            //PEGA O VERTICE COM A MENOR DISTANCIA
            int linha = caminhoMinVertice(spt, distancia);
            //INCLUI ESSE VERTICE NO SPT
            spt[linha] = true;
            //PERCORRE POR TODOS OS VERTICES E ATUALIZA A KEY
            for (int coluna = 0; coluna <tamanho ; coluna++) {
                if(matriz[linha][coluna]>0){            
                    if(spt[coluna]==false && matriz[linha][coluna]!=INFINITO){
                        //VERIFICA SE A DISTANCIA PRECISA ATUALIZAR OU NAO, VE SE O A DISTANCIA DA CIDADE DE ORIGEM É MENOR QUE A DISTANCIA ATUAL E SE SIM ATUALIZA
                        float newKey = matriz[linha][coluna] + distancia[linha];
                        if(newKey<distancia[coluna])
                        distancia[coluna] = newKey;
                    }
                }
            }
        }
        //PRINTA A MENOR DISTANCIA
        printDijkstra(origem, distancia,destino);
    }
   
    public void printDijkstra(int origem, float [] key,int destino){
        System.out.println("Algoritmo de Dijkstra: ");
        for (int i = 0; i <tamanho ; i++) {
            if (i==destino){
                System.out.println("Origem: " + (origem+1) + " para destino " + + (i+1) +
                " distancia: " + key[i]);
            }
        }
    }

    //Prim +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    //Funcao para achar o vertice com a key
    //MST = Minimum Spanning Tree (ARRVORE GERADORA MINIMA)
    int minKey(float key[], Boolean mstSet[])
    {
        // inicializa com o maior e menor valor possivel
        float min = Float.MAX_VALUE;
        int min_index = -1;
 
        for (int v = 0; v < tamanho; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    //Funcao para printar a MST

    void printMST(int mst[], float grafo[][])
    {
        System.out.println("ORIGEM\t          DESTINO\tPESO\n");
        float pesoTotal =0;
        for (int i = 1; i < tamanho; i++){
            System.out.println(this.nodes.get(mst[i])+ "(" + (mst[i]+1) +")     \t"  +this.nodes.get(i)+"(" + (i+1) +")\t" + grafo[i][mst[i]]);
            pesoTotal = pesoTotal+grafo[i][mst[i]];      
        }
        System.out.println("\nPeso total: " + pesoTotal);
    }
 
    //Funcao para construir e printar a MST de um grafo
    void primMST( float grafo[][])
    {
        //Arrray para guardar a MST
        int mst[] = new int[tamanho];
 
        //Valores para pegar o peso minimo nas arestas
        float key[] = new float[tamanho];
 
        //vertices incluidos na MST
        Boolean mstSet[] = new Boolean[tamanho];
 
        //inicializando todos os valores como infinito
        for (int i = 0; i < tamanho; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        //Inclui o primeiro vertice no mst
        key[0] = 0;
        mst[0] = -1; // primeiro nó inserido e como raiz
 
        // A arvore geradora minima vai ter o mesmo tamanho que o grafo, entao..
        for (int count = 0; count < tamanho - 1; count++) {
            //pega o valor minimo do vertice pelo mstSet ainda nao incluido
            int u = minKey(key, mstSet);
 
            //adiciona o vertice selecionado como ja visitado
            mstSet[u] = true;
 

            //Atualiza o valor e o mst dos vertices adjacentes do escolhido, só considera os que não estao incluidos na mstSet
            for (int v = 0; v < tamanho; v++)
 
                // grafo[u][v] nao é zero só para os vertices adjacentes 
                // mstSet[v] é falso para vertices nao inseridos na MST
                // só atualiza key se grafo[u][v] é menor que key[v]
                if (grafo[u][v] != 0 && mstSet[v] == false && grafo[u][v] < key[v]) {
                    mst[v] = u;
                    key[v] = grafo[u][v];
                }
        }
 
        // printa a MST
        printMST(mst, grafo);
    }
}