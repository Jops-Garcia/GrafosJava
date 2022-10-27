/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
//import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {
    public static Grafos Read(String Caminho){ //ArrayList<Student> alunos) {
        //criando grafo e adicionando os dados
        Grafos test = new Grafos(0);
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";
            try {
                
                int tamanho;
                tamanho = Integer.parseInt(lerArq.readLine());
                test = new Grafos(tamanho);
                String[] linhaSplit;
                for(int i = 0; i < tamanho;i++)
                {
                    //lendo o arquivo e adicionando na array de nomes
                    linha = lerArq.readLine();
                    linhaSplit = linha.split(";");
                    test.insertNode(linhaSplit[1]);
                }
                for (int l = 0; l < tamanho; l++)
                {
                        linha = lerArq.readLine();
                        linhaSplit = linha.split(";");
                        //adicionando dados na matriz
                        for (int c = 0; c < tamanho; c++)
                        {
                            //adicionando dados na matriz, substituindo a , por .
                            test.insert(l,c, Float.parseFloat(linhaSplit[c].replace(',', '.')));
                        }
                }

                arq.close();
            } catch (IOException ex) {
                System.out.println("Erro: Não leu o arquivo"); //caso de erro
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado");
        }
        return test;
    }
}
