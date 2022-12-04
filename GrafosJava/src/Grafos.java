/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
import java.util.ArrayList;
import java.util.LinkedList;
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

    public void caminhos(int cidade,ArrayList<String> cidades){
        //A array serve para saber os nós que já foram percorridos
        
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

    //Pega o vertice com a distância mínima que não está inclusa no SPT
    public int caminhoMinVertice(boolean [] mst, float [] key){
        //Pega o maior float possível
        float minKey = Float.MAX_VALUE;
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
        float INFINITO = Float.MAX_VALUE;
        //Coloca todas as distâncias como "Infinito" (maior float)
       
        for (int i = 0; i <tamanho ; i++) {
            distancia[i] = INFINITO;
        }
        //Inicia o vertice de origem como 0
        distancia[origem] = 0;
        //Cria SPT
        for (int i = 0; i <tamanho ; i++) {
            //Pega o vertice com a menor distância
            int linha = caminhoMinVertice(spt, distancia);
            //Inclui esse vertice no SPT
            spt[linha] = true;
            //Percorre por todos os vertices e atualiza a Key
            for (int coluna = 0; coluna <tamanho ; coluna++) {
                if(matriz[linha][coluna]>0){            
                    if(spt[coluna]==false && matriz[linha][coluna]!=INFINITO){
                        //Verifica se a distância precisa atualizar ou não, verifica se a distância da cidade de origem é menor que a distância atual, caso seja true, atualiza
                        float newKey = matriz[linha][coluna] + distancia[linha];
                        if(newKey<distancia[coluna])
                        distancia[coluna] = newKey;
                    }
                }
            }
        }
        //Printa menor distância
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
    

    //----------------------------------------------------------------------------------------------------------------------------------------
    //Algoritmo de Prim 
    
    //Funcao para achar o vertice com a key
    //MST = Minimum Spanning Tree (ARVORE GERADORA MINIMA)
    int minKey(float key[], Boolean mstSet[])
    {
        // Inicializa com o maior e menor valor possivel
        float min = Float.MAX_VALUE;
        int min_index = -1;
 
        for (int v = 0; v < tamanho; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    //Função para printar a MST

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
 
        //Vertices incluidos na MST
        Boolean mstSet[] = new Boolean[tamanho];
 
        //Inicializando todos os valores como infinito
        for (int i = 0; i < tamanho; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        //Inclui o primeiro vertice no mst
        key[0] = 0;
        mst[0] = -1; // Primeiro nó inserido e como raiz
 
        // A arvore geradora minima vai ter o mesmo tamanho que o grafo, entao..
        for (int count = 0; count < tamanho - 1; count++) {
            //Pega o valor minimo do vertice pelo mstSet ainda nao incluido
            int u = minKey(key, mstSet);
 
            //Adiciona o vertice selecionado como ja visitado
            mstSet[u] = true;
 

            //Atualiza o valor e o mst dos vertices adjacentes do escolhido, só considera os que não estao incluidos na mstSet
            for (int v = 0; v < tamanho; v++)
 
                // grafo[u][v] nao é zero só para os vertices adjacentes 
                // mstSet[v] é falso para vertices nao inseridos na MST
                // Só atualiza key se grafo[u][v] é menor que key[v]
                if (grafo[u][v] != 0 && mstSet[v] == false && grafo[u][v] < key[v]) {
                    mst[v] = u;
                    key[v] = grafo[u][v];
                }
        }
 
        // Printa a MST
        printMST(mst, grafo);
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    //Algoritmo de Ford-Fulkerson 

    boolean bfs(float grafo2[][], int predecessor, int destino, int caminho[])
    {
        //Cria um array e marca todos os vertices como nao visitado
        boolean visitado[] = new boolean[tamanho];
        for (int i = 0; i < tamanho; ++i)
        {
            visitado[i] = false;
        }
        //Cria uma fila, coloca o vertice predecessor e marca como visitado

        LinkedList<Integer> fila = new LinkedList<Integer>();
        fila.add(predecessor);
        visitado[predecessor] = true;

        caminho[predecessor] = -1;
 
        // loop para fazer busca em largura
        while (fila.size() != 0) {
            int u = fila.poll();
            for (int v = 0; v < tamanho; v++) {
                if (visitado[v] == false
                    && grafo2[u][v] > 0) {
                    //Se encontrar, retornar true, seta ele no caminho
                    if (v == destino) {
                        caminho[v] = u;
                        return true;
                    }
                    fila.add(v);
                    caminho[v] = u;
                    visitado[v] = true;
                }
            }
        }
        //Se não encontrar retorna false
        return false;
    }
 
    //Função para printar o fluxo maximo dado uma origem e destino no grafo
    void fordFulkerson(float grafo[][], int predecessor, int destino)
    {
        int u, v;

        // Cria um segundo grafo para armazenar a quantidade que de fluxo que sobra do nas arestas do original
        //Ou seja, o grafo2[i][j] é a capacidade de fluxo que sobrou de i para j 

        float grafo2[][] = new float[tamanho][tamanho];
 
        for (u = 0; u < tamanho; u++)
            for (v = 0; v < tamanho; v++)
            {
                grafo2[u][v] = grafo[u][v];
            }
            // Essa array vai ser preenchida pelo busca em largura colocando os caminhos
            int caminho[] = new int[tamanho];
    
            float fluxoMaximo = 0; // Inicilmente sem fluxo maximo
    

            while (bfs(grafo2, predecessor, destino, caminho)) {

                //Acha as capacidades das arestas junto ocm o caminho completado pela busca em largura, 
                //Ou seja acha o fluxo maximo através dos caminhos encontrados
                float fluxoCaminho = Float.MAX_VALUE;
                for (v = destino; v != predecessor; v = caminho[v]) {
                    u = caminho[v];
                    fluxoCaminho= Math.min(fluxoCaminho, grafo2[u][v]);
                }
    
                //Atualiza o fluxo que sobrou das arestas
                for (v = destino; v != predecessor; v = caminho[v]) 
                {
                    u = caminho[v];
                    grafo2[u][v] -= fluxoCaminho;
                    grafo2[v][u] += fluxoCaminho;
                }
    
                //Adiciona o fluxo do caminho no fluxo maximo
                fluxoMaximo += fluxoCaminho;
        }
 
        // Printa o fluxo máximo
        System.out.println("O Fluxo máximo de "+this.nodes.get(predecessor)+"("+(predecessor+1)+")"+" e "+this.nodes.get(destino)+"("+(destino+1)+")"+" é: " + fluxoMaximo);
    }
}