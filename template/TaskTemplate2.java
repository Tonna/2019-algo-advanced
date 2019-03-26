import java.io.*;
import java.util.Arrays;

public class TaskTemplate2 {
    //This is used for flexibility
    //For testing and submissions we  might use different output streams
    private PrintWriter printWriter;
    private StreamTokenizer streamTokenizer;

    public TaskTemplate2(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public static void main(String[] args) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out))) {
            new TaskTemplate2(printWriter).doStuff();
            printWriter.flush();
        }
    }

    void doStuff() throws IOException {
        initReader();

	//read input
        //int n = nextInt();


	//initialize data structure

	//calculate stuff

	//output result
        printWriter.println("dummy");
        

    }

    private void initReader() {
        streamTokenizer =
                new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    }

    private int nextInt() throws IOException {
        streamTokenizer.nextToken();
        return (int) streamTokenizer.nval;
    }
}
