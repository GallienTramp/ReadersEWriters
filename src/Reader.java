import java.util.Random;

public class Reader implements Runnable{
    String lastWord;
    Random r = new Random();
    @Override
    public void run() 
    {
        try {
            ReadersEWritersSO.readWriteLock.readLock().lock();//implementacao 1
            //ReadersEWritersSO.lock.lock();//implementacao2
            for(int i = 0; i < 100; i++) {
                int p = r.nextInt(ReadersEWritersSO.txt.size());
                lastWord = ReadersEWritersSO.txt.get(p);
            }
            Thread.sleep(1);
        } catch (InterruptedException ex) {
                //Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ReadersEWritersSO.readWriteLock.readLock().unlock();//implementacao1
            //ReadersEWritersSO.lock.unlock();//implementacao 2
        }
    }
}
    
