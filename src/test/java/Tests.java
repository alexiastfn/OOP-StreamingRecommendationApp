import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    private static final String USERS_FILE = "users.csv";
    private static final String STREAMERS_FILE = "streamers.csv";
    private static final String STREAMS_FILE = "streams.csv";
    private static final String COMMANDS_FILE = "commands.txt";
    private static final String REFERENCE_FILE = "reference.txt";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testTheTest() {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));

        ProiectPOO.main(null);

        assert (outPrintStream.toString().trim()).equals("Nothing to read here");
        System.setOut(System.out);
    }


    @Test
    public void test1ListSongsFromArtists() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test1/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test2AddStream() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test2/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test3DeleteStream() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test3/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test4ListenToStream() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test4/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test5RecommendSongs() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test5/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test6SurpriseSongs() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs1/";
        String commandFolder = "test6/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test7ListPodcastsByPodcastAuthor() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test7/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test8ListAudiobooksByAuthor() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test8/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test9ListUsersMultipleStreams() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test9/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test10AddMultipleStreams() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test10/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test11RecommendStreamPodcast() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test11/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder);

        System.setOut(System.out);
    }

    @Test
    public void test12RecommendStreamAudiobook() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test12/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test13MultipleSurpriseStreamRecommendation() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test13/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test14AddAndRecommendPodcasts() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test14/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    @Test
    public void test15MixedActions() throws IOException {
        ByteArrayOutputStream outPrintStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPrintStream));
        String inputFolder = "inputs2/";
        String commandFolder = "test15/";

        ProiectPOO.main(getInputArgs(inputFolder, commandFolder));
        String output = outPrintStream.toString();

        assertJsonLineAreEqual(output, commandFolder, true);

        System.setOut(System.out);
    }

    private String[] getInputArgs(String inputFolder, String testFolder) {
        return new String[]{inputFolder + STREAMERS_FILE,
                            inputFolder + STREAMS_FILE,
                            inputFolder + USERS_FILE,
                            testFolder + COMMANDS_FILE};
    }

    private void assertJsonLineAreEqual(String actualOutput, String inputFolder)
            throws IOException {
        assertJsonLineAreEqual(actualOutput, inputFolder, false);
    }
    private void assertJsonLineAreEqual(String actualOutput, String inputFolder, boolean ignoreDate)
            throws IOException {

        BufferedReader
                expectedOutput =
                new BufferedReader(new FileReader(
                        System.getProperty("user.dir") + "/src/test/resources/" + inputFolder
                        + REFERENCE_FILE));
        BufferedReader actualOutputFile = new BufferedReader(new StringReader(actualOutput));
        String expectedJsonLine;
        String actualJsonLine;
        while ((expectedJsonLine = expectedOutput.readLine()) != null && (actualJsonLine = actualOutputFile.readLine()) !=null) {
            JsonNode jsonNodeActual = objectMapper.readTree(actualJsonLine.toLowerCase());
            JsonNode jsonNodeExpected = objectMapper.readTree(expectedJsonLine.toLowerCase());

            if (ignoreDate) {
                jsonNodeActual = ((ArrayNode) jsonNodeActual).remove(6);
                jsonNodeExpected = ((ArrayNode) jsonNodeExpected).remove(6);
            }
            assertEquals(jsonNodeExpected, jsonNodeActual);
        }
    }
}
