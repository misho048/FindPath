import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by Mišinko on 4.7.2018.
 */
public class MyTests {
    @Test
    public void noPath() throws FileNotFoundException {
        String path="testGrid1.txt";
        Find nPath=new Find(path);
        nPath.Run();
    }
    @Test
    public void noStartFinish() throws FileNotFoundException {
        String path="testGrid2.txt";
        Find nSFPath=new Find(path);
        nSFPath.Run();
    }
    @Test
    public void uglyMaze() throws FileNotFoundException {
        String path="testGrid3.txt";
        Find nSFPath=new Find(path);
        nSFPath.Run();
    }



}
