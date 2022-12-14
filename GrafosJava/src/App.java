/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //Pegando diretorio do programa
        String meudir = System.getProperty("user.dir");
        meudir = meudir + "\\src\\";


        // ALTERE O NOME DO ARQUIVO AQUI -> |
                                        //  V
        String ArqConfig = meudir + "entrada.txt"; //Deixe o txt dentro da pasta src ou altere a variavel "meudir"

        //Criando grafo utilizando classe para ler o arquivo;
        Grafos test= Arquivo.Read(ArqConfig);

        Scanner menu = new Scanner (System.in);
        Scanner aux = new Scanner (System.in);
        int opcao = 0;
        int codigo = 0;
        int codigoAux = 0;

        //MENU
        while (opcao!=7) {            
            clear();
            System.out.print("##---------------------Menu-------------------##\n\n");
            System.out.print("|-------------------------------------------------|\n");
            System.out.print("| Opção 1 - Exibir matriz e nós                   |\n");
            System.out.print("| Opção 2 - Obter Nós vizinhos                    |\n");
            System.out.print("| Opção 3 - Obter todos os caminhos               |\n"); 
            System.out.print("| Opção 4 - Encontrar caminho minimo (Dijkstra)   |\n"); 
            System.out.print("| Opção 5 - Calcular Árvore geradora mínima (Prim)|\n");
            System.out.print("| Opção 6 - Calcular Fluxo Máximo (Ford-Fulkerson)|\n");//Deixando comentados para uso no futuro
            System.out.print("| Opção 7 - Sair                                  |\n");
            System.out.print("|-------------------------------------------------|\n");
            System.out.print("Digite uma opção: ");

            opcao = menu.nextInt();

            if (opcao == 7) {
                //Fechando os scanners
                menu.close();
                aux.close();
            }

            switch (opcao) {
                case 1://Exibir matriz e nós
                    clear();
                    test.printNodes();
                    System.out.println();
                    test.printMatriz();
                break;

                case 2://Obter Nós vizinhos
                    clear();
                    test.printNodes();
                    System.out.println(); //Prints para auxiliar o user
                    System.out.print("Digite o nó desejado: ");
                    codigo=aux.nextInt(); 
                    aux.nextLine();//lixo para coletar espaço
                    clear();
                    
                    test.vizinhos(codigo-1);
                break;

                case 3://Obter todos os caminhos
                    clear();
                    test.printNodes();
                    System.out.println();//Prints para auxiliar o user
                    System.out.print("Digite o nó desejado: ");
                    codigo=aux.nextInt(); 
                    aux.nextLine();//lixo para coletar espaço
                    clear();

                    //Printando node selecionado e seu codigo
                    System.out.println("Code: "+(codigo)+" Node: "+test.nodes.get(codigo-1));
                    System.out.println("\nCaminhos:");
                    
                    //Criando auxiliar para nao passsar a array como referencia
                    ArrayList<String> aux1=new ArrayList<String>(test.nodes); 
                    test.caminhos(codigo-1,aux1);//-1 Um no código para acertar a posicao da cidade 
                    System.out.println();
                break;

                case 4:
                    clear();
                    test.printNodes();
                    System.out.println(); //Prints para auxiliar o user
                    System.out.print("Digite o nó de origem desejado: ");
                    codigo=aux.nextInt(); 
                    aux.nextLine();//lixo para coletar espaço

                    System.out.println(); //Prints para auxiliar o user
                    System.out.print("Digite o nó de destino desejado: ");
                    codigoAux=aux.nextInt(); 
                    aux.nextLine();//lixo para coletar espaço
                    clear();

                    test.distanciaMinima(codigo-1,codigoAux-1);
                break;

                case 5:
                    clear();
                    test.primMST(test.matriz);
                    
                break;             
                
                case 6:
                    clear();
                     test.printNodes();
                    System.out.println(); //Prints para auxiliar o user
                    System.out.print("Digite o nó de origem desejado: ");
                    codigo=aux.nextInt(); 
                    aux.nextLine();//lixo para coletar espaço

                    System.out.println(); //Prints para auxiliar o user
                    System.out.print("Digite o nó de destino desejado: ");
                    codigoAux=aux.nextInt(); 
                    aux.nextLine();//lixo para coletar espaço
                    clear();
                    test.fordFulkerson(test.matriz, codigo-1,codigoAux-1);
                    
                break;
   
                case 7:
                    System.out.print("\nAté logo\n\n");
                break;

                default:
                    System.out.print("\nOpção Inválida!");
                break;
            }
            if(opcao!=7){
                System.out.print("\nPressione Enter para continuar...");
                aux.nextLine();
            }
        }
    }
    //Função para limpar tela
    public static void clear() throws IOException, InterruptedException{
        if (System.getProperty("os.name").contains("Windows")){//Pega o sistema operacional e utiliza o comando com base
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else{
            Runtime.getRuntime().exec("clear");
        }
    }
}