

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class ReadersEWritersSO {
    
    public static List<String> txt; //Texto do arquivo
    static ArrayList<Integer> roulette; //Posicoes em que serao inseridos os objetos
    static Thread[] rw; // vetor de threads
    static Semaphore mutex;
    public static void main(String[] args) {
        loadText();
        mutex = new Semaphore(1);
        
        for(int i = 0; i < 100; i++)
        {
            long media=0;
            int test = 50;//50 testes para cada i
            while(test>0){
                readersWritersBirth(i);//Popula o vetor de threads numa ordem aleatoria
                
                long inicialTime = System.currentTimeMillis();//recebe o tempo inicial
                for(Thread t : rw)//Inicia a execucao das threads
                    t.start();
                try {
                    for(Thread t : rw)
                        t.join();//Espera todas terminarem para continuar o codigo
                } catch (InterruptedException ex) {
                    System.out.println("Erro na thread");
                }
                test--;//Contador
                media+=(System.currentTimeMillis() - inicialTime);//o tempo gasto eh somado
            }
            //Imprime o tempo medio dos 50 testes para i leitores e 100-i escritores
            System.out.println(i + " leitores e " + (100-i) + " escritores. Tempo medio: " + (long)media/50 + "ms");
            
        }
    }
    
    
    //Popula o vetor de threads com os leitores e os escritores. qt = quantidade de leitores, 100-qt = quantidade de escritores
    public static void readersWritersBirth(int qt)
    {       
        rw = new Thread[100];
        bingo();
        Iterator r = roulette.iterator();
        for(int i =0; i < qt; i++)
           rw[(int)r.next()] = new Thread(new Reader());
        for(int i = qt; i < rw.length; i++)
           rw[(int)r.next()] = new Thread(new Writer());     
    }
    
    //sorteia as posicoes em que seram inseridos os leitores e escritores
    public static void bingo()
    {   roulette = new ArrayList();
        for(int i =0; i <100; i++)
            roulette.add(i);
        
        Collections.shuffle(roulette);
    }
    
    //Carrega o texto na lista ligada txt
    public static void loadText()
    {   txt = new LinkedList();
        try {
            Scanner sc = new Scanner(new File("bd.txt"));
            
            while(sc.hasNext())
                txt.add(sc.nextLine());
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("O arquivo não foi encontrado.");
        }
        
    }
    
}
