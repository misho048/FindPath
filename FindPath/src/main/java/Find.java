import java.io.*;
import java.util.*;

/**
 * Created by Mišinko on 4.7.2018.
 */
class Find {
        //positionMap saves the positions of start and finish points
        //maze cointains whole maze
        //path is the file path to the grid file
        //road saving routes we taked (u u d r ....)
        private HashMap<String,Integer> positionMap = new HashMap<String, Integer>();
        private ArrayList<ArrayList<String>> maze = new ArrayList<ArrayList<String>>();
        private String path;
        private LinkedList<Character> road = new LinkedList<Character>();

        Find(String path){
        this.path=path;

    }

    void Run() throws FileNotFoundException {
        fileReader();
        getStartFinish();

        if (searchPath(positionMap.get("startLine"),positionMap.get("startColumn")))
            System.out.println("hurray there is a path :"+String.valueOf(road));
        else System.out.println("No path found :-(");

        printMatrix();

    }

    private void printMatrix(){
        // method for printing maze to the console
        // adding S to the started location so we can see where we started
        maze.get(positionMap.get("startLine")).set(positionMap.get("startColumn"),"S");
        for (ArrayList<String> iLine : maze) {
            System.out.println();

            for (String iColumn : iLine) {
                System.out.print(iColumn);

            }
        }
        System.out.println();

    }

    private void fileReader() throws FileNotFoundException {
            //this method will read the file and save it in the 2D arrayList (or arraylist of a String arraylist)
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        Scanner scan = new Scanner(file);

            // adding W (stands for Wall) around my maze to avoid ArrayIndexOutOfBoundsException
        String CurrentLine=scan.nextLine();
        maze.add(new ArrayList<String>());

            //W in the first line
        for (int i=0;i<CurrentLine.length()+2;i++)
            maze.get(0).add("W");

        maze.add(new ArrayList<String>(Arrays.asList(CurrentLine.split(""))));
        maze.get(1).add(0,"W");
        maze.get(1).add(maze.get(1).size(),"W");
        int index=2;

            //W at the start and in the end of the loaded line
        while (scan.hasNextLine()) {
            CurrentLine=scan.nextLine();
            maze.add(new ArrayList<String>(Arrays.asList(CurrentLine.split(""))));
            maze.get(index).add(0,"W");
            maze.get(index).add(maze.get(index).size(),"W");
            index++;
        }

            // W in the last line
        maze.add(new ArrayList<String>());
        for (int i = 0; i< maze.get(index-1).size(); i++)
            maze.get(maze.size()-1).add("W");

        }

    private void getStartFinish() {
        //this method is trying to found starting and finishing locations
        positionMap.put("startLine",-1);
        positionMap.put("finishLine", -1);

        for (int i = 0; i< maze.size(); i++) {
            if (maze.get(i).contains("S")) {
                positionMap.put("startLine", i);
                positionMap.put("startColumn", maze.get(i).indexOf("S"));
            }


            if (maze.get(i).contains("X")) {
                positionMap.put("finishLine", i);
                positionMap.put("finishColumn", maze.get(i).indexOf("X"));
            }
        }

        // if there is no Starting position in the maze, there will be default one at the [1,1}
        if (positionMap.get("startLine")==(-1)){

            positionMap.put("startLine",1);
            positionMap.put("startColumn",1);
            maze.get(1).set(1,"S");
        }
        // if there is no Finish position in the maze, there will be default one at the [size-2,size-2]

        if (positionMap.get("finishLine")==(-1)){

            positionMap.put("finishLine",maze.size()-2);
            positionMap.put("finishColumn",maze.get(maze.size()-1).size()-2);
            maze.get(maze.size()-2).set(maze.get(maze.size()-2).size()-2,"X");
        }



    }

    private boolean searchPath (int row, int column) {
        // method should recursivly iterate until there is full maze of 1 (visited) which means there is no path
        // or we found end (x)
        //
        // dr,dc stands for RowDifference nad ColumnDifference
        int dr,dc;

        //if we found the end
        if (maze.get(row).get(column).equals("X"))
            return true;

        //to avoid going in the circles
        if ((maze.get(row).get(column).equals(".")) || (maze.get(row).get(column).equals("S"))) {
            maze.get(row).set(column, "1");


            //down

            dr = +1;
            dc = 0;
            if (searchPath(row + dr, column + dc)) {
                road.add('d');
                return true;
            }

            //up

            dr = -1;
            dc = 0;
            if (searchPath(row + dr, column + dc)) {
                road.add('u');
                return true;
            }

            //right

            dr = 0;
            dc = 1;
            if (searchPath(row + dr, column + dc)) {
                road.add('r');
                return true;
            }
            //left

            dr = 0;
            dc = -1;
            if (searchPath(row + dr, column + dc)) {
                road.add('l');
                return true;
            }

        }
        return false;
    }

}

