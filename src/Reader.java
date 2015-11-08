import java.util.Random;

public class Reader implements Runnable{
    String lastWord;
    Random r = new Random();
    @Override
    public void run() 
    {
        try {
            ReadersEWritersSO.lock.lock();
            for(int i = 0; i < 100; i++) {
                int p = r.nextInt(ReadersEWritersSO.txt.size());
                lastWord = ReadersEWritersSO.txt.get(p);
            }
            Thread.sleep(1);
        } catch (InterruptedException ex) {
                //Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            //ReadersEWritersSO.mutex.release();
            ReadersEWritersSO.lock.unlock(); 
        }
    }
}
    
