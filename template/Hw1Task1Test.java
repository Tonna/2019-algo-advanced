import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class Hw1Task1Test {

    static final String TEST_CASES_PATH = "src/test/resources/hw01-task01/";
    private Hw1Task1Try1 task;
    private int[] input;
    private long[] output;
    private ArrayList<Long> result;
    private String caseName;


    public Hw1Task1Test(String caseName, int[] input, long[] output) {
        this.caseName = caseName;
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters(name = "{index} - {0}")
    public static Iterable<Object[]> data() throws IOException {

        Path path = Paths.get(TEST_CASES_PATH);
        List<String> list = Files.list(path)
                .map(p -> p.toString().split("/")[4].split("\\.")[0])
                .distinct().sorted().collect(Collectors.toList());

        //System.out.println(list);

        Object[][] data = new Object[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            Object[] arr = new Object[3];
            arr[0] = list.get(i);
            arr[1] =
                    readValues(list.get(i), ".in").stream().mapToInt(Integer::intValue).toArray();
            arr[2] =
                    readValues(list.get(i), ".out").stream().mapToLong(Integer::longValue).toArray();
            data[i] = arr;
        }

        return Arrays.asList(data);
    }

    private static ArrayList<Integer> readValues(String file, String suffix) throws IOException {
        //FixMe this magic will fail if not intellij is used
        Scanner reader = new Scanner(Files.newBufferedReader(
                Paths.get(TEST_CASES_PATH + file + suffix)));

        ArrayList<Integer> out = new ArrayList<>();

        while (reader.hasNext()) {
            out.add(reader.nextInt());
        }

        return out;

    }

    @Before
    public void init() throws FileNotFoundException {
        result = new ArrayList<>();
        //System.out is magic, don't know how it works, but it is not needed
        PrintStream mockOutputStream = new PrintStream(System.out) {
            @Override
            public void println(long l) {
                result.add(l);
            }
        };
        task = new Hw1Task1Try1(mockOutputStream);
    }

    private void writeTestDataToInputStream(int[] input) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            buf.append(input[i]);
            buf.append("\n");
        }
        //System.out.println(buf);
        InputStream in = new ByteArrayInputStream(buf.toString().getBytes());
        System.setIn(in);
    }

    private long[] getResult() {
        long[] out = new long[result.size()];
        for (int i = 0; i < result.size(); i++) {
            out[i] = result.get(i);
        }
        //printResult(out);
        return out;
    }

    //optional, may be useful for debug
    private void printResult(long[] result) {
        System.out.println("expected");
        for (int k = 0; k < output.length; k++) {
            System.out.print(output[k] + " ");
        }
        System.out.println();
        System.out.println("actual");
        for (int k = 0; k < result.length; k++) {
            System.out.print(result[k] + " ");
        }
        System.out.println();


    }

    @Test
    public void test1() throws IOException {
        writeTestDataToInputStream(input);
        task.doStuff();
        assertArrayEquals(output, getResult());
    }

}