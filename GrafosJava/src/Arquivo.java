/**
 *
 * @author João Pedro Garcia & Rodrigo Couto Rodrigues
 */
//import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.ArrayList;

public class Arquivo {
    public static Grafos Read(String Caminho){ //ArrayList<Student> alunos) {
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
                    for (int c = 0; c < tamanho; c++)
                    {
                        test.insert(l,c, Float.parseFloat(linhaSplit[c].replace(',', '.')));
                    }
                }

                arq.close();
            } catch (IOException ex) {
                System.out.println("Erro: Não leu o arquivo"); //adicionando comentario
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado");
        }
        return test;
    }
    public static boolean Write(String caminho, String texto) {
        try {
            //remover da pasta após o uso para testes pois irá append em no mesmo arquivo
            FileWriter arq = new FileWriter(caminho,true);
            arq.write(texto + "\n");
            arq.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
