
import java.io.FileNotFoundException;

/**
 * Created by Mišinko on 4.7.2018.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //here we put filePath
        final  String filePath="file/grid.txt";
        Find find= new Find(filePath);

        //performance test
        long startTime = System.currentTimeMillis();
        find.Run();
        long finishTime = System.currentTimeMillis();
        System.out.println("That took: " + (finishTime - startTime) + " ms");



    }


}
