/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String meudir = System.getProperty("user.dir");
        meudir = meudir + "\\src\\";


        // ALTERE O NOME DO ARQUIVO AQUI -> |
                                        //  V
        String ArqConfig = meudir + "entrada.txt"; //deixe o txt dentro da pasta src ou altere a variavel "meudir"
        Grafos test= Arquivo.Read(ArqConfig);

        test.printNodes();
        Scanner menu = new Scanner (System.in);
        Scanner aux = new Scanner (System.in);
        int opcao = 0;
        int codigo = 0;

        //MENU
        while (opcao!=4) {            
            clear();
            System.out.print("##---------------------Menu-------------------##\n\n");
            System.out.print("|------------------------------------------------|\n");
            System.out.print("| Opção 1 - Exibir matriz e nós                  |\n");
            System.out.print("| Opção 2 - Obter Nós vizinhos                   |\n");
            System.out.print("| Opção 3 - Obter todos os caminhos              |\n"); 
            //System.out.print("| Opção 4 -                                      |\n"); //deixando comentados para uso no futuro
            System.out.print("| Opção 4 - Sair                                 |\n");
            System.out.print("|------------------------------------------------|\n");
            System.out.print("Digite uma opção: ");

            opcao = menu.nextInt();

            if (opcao == 4) {
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
                System.out.println();
                System.out.print("Digite o nó desejado: ");
                codigo=aux.nextInt(); 
                aux.nextLine();//lixo
                clear();
                
                test.vizinhos(codigo-1);
                break;

            case 3://Obter todos os caminhos
                clear();
                test.printNodes();
                System.out.println();
                System.out.print("Digite o nó desejado: ");
                codigo=aux.nextInt(); 
                aux.nextLine();//lixo
                clear();

                System.out.println("Code: "+(codigo)+" Node: "+test.nodes.get(codigo-1));
                System.out.println("\nCaminhos:");
                
                ArrayList<String> aux1=new ArrayList<String>(test.nodes); 
                test.caminhos(codigo-1,aux1);
                System.out.println();
                break;

             //deixando comentarios para uso no futuro
            // case 4:
                
            //     break;
            case 4:
                System.out.print("\nAté logo\n\n");
                break;
            default:
                System.out.print("\nOpção Inválida!");
                break;
            }
            if(opcao!=4){
            System.out.print("\nPressione Enter para continuar...");
            aux.nextLine();
            }
        }
    }
    public static void clear() throws IOException, InterruptedException{
        if (System.getProperty("os.name").contains("Windows")){
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else{
            Runtime.getRuntime().exec("clear");
        }
    }
}