import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;

import static java.nio.file.Files.list;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestTemplate2 {

    private static final String TEST_CASES_PATH = "C:";
	    //Input test data directory here
            //"src/test/resources/hw01-task01/";

    private TaskTemplate2 task;
    private int[] input;
    private long[] output;
    private ArrayList<Long> result;
    private String caseName;


    public TestTemplate2(String caseName, int[] input, long[] output) {
        this.caseName = caseName;
        this.input = input;
        this.output = output;
    }

    @Parameters(name = "{index} - {0}")
    public static Iterable<Object[]> data() throws IOException {

        List<String> testCases = list(Paths.get(TEST_CASES_PATH))
                //magic to get file name without mask
                .map(p -> p.toString().split("/")[4].split("\\.")[0])
                .distinct().sorted().collect(toList());

        //System.out.println(testCases);

        Object[][] data = new Object[testCases.size()][];

        for (int i = 0; i < testCases.size(); i++) {
            Object[] arr = new Object[3];
            arr[0] = testCases.get(i);
            arr[1] =
                    read(testCases.get(i), ".in").stream().mapToInt(Long::intValue).toArray();
            arr[2] =
                    read(testCases.get(i), ".out").stream().mapToLong(Long::longValue).toArray();
            data[i] = arr;
        }

        return Arrays.asList(data);
    }

    private static ArrayList<Long> read(String file, String suffix) throws IOException {
        //FixMe this magic will fail if not intellij is used
        Scanner reader = new Scanner(Files.newBufferedReader(
                Paths.get(TEST_CASES_PATH, file + suffix)));

        ArrayList<Long> out = new ArrayList<>();

        while (reader.hasNext()) {
            out.add(reader.nextLong());
        }

        return out;

    }

    @Before
    public void init() throws FileNotFoundException {
        result = new ArrayList<>();
        //System.out is magic, don't know how it works, but it is not needed
        task = new TaskTemplate2(new PrintWriter(System.out) {
            @Override
            public void println(long l) {
                result.add(l);
            }
        });
    }

    private void writeTestDataToInputStream(int[] input) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            buf.append(input[i]).append(" ");
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
        printResult(out);
        return out;
    }

    //optional, may be useful for debug
    private void printResult(long[] result) {
        System.out.println("\nexpected");
        LongStream.of(output).forEach(l -> System.out.print(l + " "));
        System.out.println("\nactual");
        LongStream.of(result).forEach(l -> System.out.print(l + " "));
        System.out.println();
    }

    @Test
    public void test1() throws IOException {
        writeTestDataToInputStream(input);
        assertTimeout(java.time.Duration.ofMillis(250), () -> {
            task.doStuff();
        });
        assertArrayEquals(output, getResult());
    }
}
