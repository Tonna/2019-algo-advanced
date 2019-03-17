import java.io.*;

public class Template01 {

    //This is used for flexibility
    //For testing and submissions we  might use different output streams 
    private final PrintStream printStream;

    private StreamTokenizer streamTokenizer;

    Template01(PrintStream printStream) {
        this.printStream = printStream;
    }

    public static void main(String[] args) {
        try {
            new Template01(System.out).doStuff();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void doStuff() throws IOException {
        initReader();

        int inputLength = nextInt();

        printStream.println("test " + inputLength);

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
