
import java.util.Random;


public class Writer implements Runnable {
    Random r = new Random();
    @Override
    public void run() {
        try {
            ReadersEWritersSO.mutex.acquire();
            for(int i = 0; i < 100; i++)
            {
                int p = r.nextInt(ReadersEWritersSO.txt.size());
                ReadersEWritersSO.txt.set(p, "MODIFICADO");
            }
                Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally
        {
            ReadersEWritersSO.mutex.release();
        }
    }
    
}
