
import java.io.FileNotFoundException;

/**
 * Created by Mišinko on 4.7.2018.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        final  String filePath="file/grid.txt";
        Find find= new Find(filePath);
        long startTime = System.currentTimeMillis();
        find.Run();
        long finishTime = System.currentTimeMillis();
        System.out.println("That took: " + (finishTime - startTime) + " ms");



    }


}
