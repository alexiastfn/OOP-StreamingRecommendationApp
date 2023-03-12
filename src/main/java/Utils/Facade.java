package Utils;


import Models.Stream;
import Models.Streamer;
import Models.User;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Facade {

    public static ArrayList<User> getUsers(String fileName) {
        String fullPath = getFullPath(fileName);
        //String fullPath = "src/main/resources/input/" + fileName;
        ArrayList<User> users = new ArrayList<>();

        File file = new File(fullPath);

        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
            String line = "";
            bf.readLine();
            while ((line = bf.readLine()) != null) {
                line = line.replace("\"", "");
                String[] components = line.split(",");

                String[] ids = components[2].split(" ");
                ArrayList<Integer> streamIds = new ArrayList<>();
                for (String id : ids) {
                    streamIds.add(Integer.parseInt(id));
                }

                User user = new User(Integer.parseInt(components[0]), components[1], streamIds);
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static ArrayList<Stream> getStreams(String fileName) {
        String fullPath = getFullPath(fileName);
        //  String fullPath = "src/main/resources/input/" + fileName;
        ArrayList<Stream> streams = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fullPath)));
            bufferedReader.readLine();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("\"", "");
                String[] parts = line.split(",");

                String name = concatenate(7,parts.length,parts,",");

                Stream.StreamBuilder builder = new Stream.StreamBuilder(Integer.parseInt(parts[1]),name);
                builder.setStreamType(Integer.parseInt(parts[0]));
                builder.setGenre(Integer.parseInt(parts[2]));
                builder.setNumberOfListenings(Long.parseLong(parts[3]));
                builder.setStreamerId(Integer.parseInt(parts[4]));
                builder.setLength(Long.parseLong(parts[5]));
                builder.setDateAdded(Long.parseLong(parts[6]));

                Stream stream = builder.build();


                streams.add(stream);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return streams;
    }

    public static ArrayList<Streamer> getStreamers(String fileName) {
        ArrayList<Streamer> streamers = new ArrayList<>();
        String fullPath = getFullPath(fileName);
        // String fullPath = "src/main/resources/input/" + fileName;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fullPath)));
            bufferedReader.readLine();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("\"", "");
                String[] parts = line.split(",");

                Streamer streamer = new Streamer(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[0]));
                streamers.add(streamer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return streamers;
    }

    public static ArrayList<String> getCommands(String fileName) {
        ArrayList<String> commands = new ArrayList<>();
        String fullPath = getFullPath(fileName);
        // String fullPath = "src/main/resources/input/" + fileName;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fullPath)));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                commands.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return commands;
    }

    private static String getFullPath(String fileName) {

        String[] parts = fileName.split("/");
        String fullPath = "src/main/resources/" + parts[0] + "//" + parts[1];
        return fullPath;
    }

    public static LocalDateTime parseToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date,formatter).atStartOfDay();
    }

    public static String parseLength(Long length) {
        int secondsLeft = Integer.parseInt(length.toString()) % 3600 % 60;
        int minutes = (int) Math.floor(length % 3600 / 60);
        int hours = (int) Math.floor(length / 3600);

        String HH = ((hours < 10) ? "0" : "") + hours;
        String MM = ((minutes < 10) ? "0" : "") + minutes;
        String SS = ((secondsLeft < 10) ? "0" : "") + secondsLeft;

        return hours != 0 ? HH + ":" + MM + ":" + SS : MM + ":" + SS;
    }

    public static String parseDate(Long date) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC);
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();

        String Day = ((day < 10) ? "0" : "") + day;
        String Month = ((month < 10) ? "0" : "") + month;

        var d = Day + "-" + Month + "-" + localDateTime.getYear();

        return d;
    }

    public static Streamer getStreamerById(Integer id, ArrayList<Streamer> streamers) {
        for (Streamer i : streamers) {
            if (i.getId().equals(id)) {
                return i;
            }
        }

        return null;
    }

    public static Stream getStreamById(Integer id, ArrayList<Stream> streams) {
        for (Stream i : streams) {
            if (i.getId().equals(id)) {
                return i;
            }
        }

        return null;
    }

    public static Stream getStreamByStreamerID(Integer id, ArrayList<Stream> streams) {
        for (Stream i : streams) {
            if (i.getStreamerId().equals(id)) {
                return i;
            }
        }

        return null;
    }

    public static User getUserById(Integer id, ArrayList<User> users) {
        for (User i : users) {
            if (i.getId().equals(id)) {
                return i;
            }
        }

        return null;
    }

    public static JSONObject getStreamJSON(Stream stream, String streamerName) {
        JSONObject jsonObject = getJSONObject();

        jsonObject.put("id", stream.getId().toString());
        jsonObject.put("name", stream.getName());
        jsonObject.put("streamerName", streamerName);
        jsonObject.put("noOfListenings", stream.getNooflistenings().toString());
        jsonObject.put("length", parseLength(stream.getLength()));
        jsonObject.put("dateAdded", parseDate(stream.getDateAdded()));
        return jsonObject;
    }

    public static JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();

        try {
            Field changeMap = jsonObject.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);
            changeMap.set(jsonObject, new LinkedHashMap<>());
            changeMap.setAccessible(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonObject;
    }

    public static void printJSONObject(ArrayList<JSONObject> jsonObjects) {
        System.out.println( jsonObjects );
    }

    public static boolean isStreamerID(Integer id, ArrayList<User> users) {
        for (User s : users) {
            if (s.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public static String concatenate(int begin, int end, String[] words, String delimiter) {
        String word = "";
        for (int i = begin; i < end; i++) {
            if (i != begin) {
                word += delimiter + words[i];
            } else {
                word += words[i];
            }
        }

        return word;
    }

    public static ArrayList<Stream> getStreamsByStreamerIdAndStreamType(Integer streamerId, ArrayList<Stream> streams, Integer streamType){
        ArrayList<Stream> filteredStreams = new ArrayList<>();
        for(Stream s: streams){
            if (s.getStreamerId().equals(streamerId) && s.getStreamType().equals(streamType)){
                filteredStreams.add(s);
            }
        }

        return filteredStreams;
    }

    public static ArrayList<Stream> getStreamsByStreamerId(Integer streamerId, ArrayList<Stream> streams){
        ArrayList<Stream> filteredStreams = new ArrayList<>();
        for(Stream s: streams){
            if (s.getStreamerId().equals(streamerId)){
                filteredStreams.add(s);
            }
        }

        return filteredStreams;
    }

    public static Integer getStreamType(String streamType){
        switch (streamType){
            case "SONG":
                return 1;
            case "PODCAST":
                return 2;
            case "AUDIOBOOK":
                return 3;
        }
        return 0;
    }

    public static HashSet<Integer> getStreamerIdsByUser(User user,ArrayList<Stream> streams){
        HashSet<Integer> streamerIds = new HashSet<>();
        for (Integer streamId : user.getStreams()) {
            Stream stream = getStreamById(streamId, streams);
            streamerIds.add(stream.getStreamerId());
        }

        return  streamerIds;
    }

}
