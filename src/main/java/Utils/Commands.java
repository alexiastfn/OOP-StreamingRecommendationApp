package Utils;

import Models.Stream;
import Models.Streamer;
import Models.User;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import static Utils.Facade.*;

public class Commands {

    public static void LIST(String idStr, ArrayList<Stream> streams, ArrayList<Streamer> streamers, ArrayList<User> users) {
        Integer id = Integer.parseInt(idStr);
        boolean isUserId = isStreamerID(id, users);
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();

        if (isUserId) {
            User user = getUserById(id, users);

            for (Integer streamId : user.getStreams()) {
                Stream stream = getStreamById(streamId, streams);
                Streamer streamer = getStreamerById(stream.getStreamerId(), streamers);

                JSONObject jsonObject = getStreamJSON(stream, streamer.getName());
                jsonObjects.add(jsonObject);
            }

        } else {
            for(int i = streams.size()-1;i>=0;i--){
                Stream s = streams.get(i);
                if (s.getStreamerId().equals(id)){
                    Streamer streamer = getStreamerById(s.getStreamerId(), streamers);
                    JSONObject jsonObject = getStreamJSON(s, streamer.getName());
                    jsonObjects.add(jsonObject);
                }
            }
        }
        printJSONObject(jsonObjects);
    }

    public static void ADD(String[] parts, ArrayList<Stream> streams) {

        Stream.StreamBuilder builder = new Stream.StreamBuilder(Integer.parseInt(parts[3]),concatenate(6, parts.length, parts, " "));
        builder.setStreamerId(Integer.parseInt(parts[0]));
        builder.setStreamType(Integer.parseInt(parts[2]));
        builder.setGenre(Integer.parseInt(parts[4]));
        builder.setLength(Long.valueOf(parts[5]));
        builder.setDateAdded(Long.valueOf(LocalDateTime.now().getSecond()));

        Stream stream = builder.build();

        streams.add(stream);
    }

    public static void DELETE(String textStreamerId, String textStreamId, ArrayList<Stream> streams) {
        Integer streamerId = Integer.parseInt(textStreamerId);
        Integer streamId = Integer.parseInt(textStreamId);

        Stream stream = null;
        for (Stream s : streams) {
            if (s.getStreamerId().equals(streamerId) && s.getId().equals(streamId)) {
                stream = s;
                break;
            }
        }

        streams.remove(stream);
    }

    public static void LISTEN(String textUserId, String textStreamId, ArrayList<Stream> streams, ArrayList<User> users) {
        Stream stream = getStreamById(Integer.parseInt(textStreamId), streams);
        User user = getUserById(Integer.parseInt(textUserId), users);

        stream.setNooflistenings(stream.getNooflistenings() + 1);

        ArrayList<Integer> listenStreams = user.getStreams();
        listenStreams.add(stream.getId());
        user.setStreams(listenStreams);
    }

    public static void RECOMMEND(String[] words, ArrayList<Stream> streams, ArrayList<User> users, ArrayList<Streamer> streamers) {
        User user = getUserById(Integer.parseInt(words[0]), users);
        Integer streamType = getStreamType(words[2]);

        HashSet<Integer> streamerIds = getStreamerIdsByUser(user,streams);

        ArrayList<Stream> allStreams = new ArrayList<>();
        for (Integer streamerId : streamerIds) {
            ArrayList<Stream> customStreams = getStreamsByStreamerIdAndStreamType(streamerId, streams, streamType);
            allStreams.addAll(customStreams);
        }

        Collections.sort(allStreams, new Comparator<Stream>() {
            @Override
            public int compare(Stream u1, Stream u2) {
                return (int) (u2.getNooflistenings() - u1.getNooflistenings());
            }
        });

        if (allStreams.size() == 0){
            return;
        }

        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i< allStreams.size()-1; i++) {
            if ( i > 4 ){
                break;
            }
            Stream stream = allStreams.get(i);
            Streamer streamer = getStreamerById(stream.getStreamerId(), streamers);
            jsonObjects.add(getStreamJSON(stream, streamer.getName()));
        }
        printJSONObject(jsonObjects);
    }

    public static void SURPRISE(String[] words, ArrayList<Stream> streams, ArrayList<User> users, ArrayList<Streamer> streamers){
        User user = getUserById(Integer.parseInt(words[0]),users);
        ArrayList<Streamer> notLisentStreamers = new ArrayList<>();

        HashSet<Integer> streamerIds = getStreamerIdsByUser(user, streams);

        for(Streamer streamer: streamers){
            if (!streamerIds.contains( streamer.getId())){
                notLisentStreamers.add(streamer);
            }
        }

        ArrayList<Stream> allStreams = new ArrayList<>();
        for (Streamer streamer : notLisentStreamers) {
            ArrayList<Stream> customStreams = getStreamsByStreamerIdAndStreamType(streamer.getId(), streams, getStreamType(words[2]));
            allStreams.addAll(customStreams);
        }

        Collections.sort(allStreams, new Comparator<Stream>() {
            @Override
            public int compare(Stream u1, Stream u2) {
                String d1 = parseDate(u1.getDateAdded());
                String d2 = parseDate(u2.getDateAdded());

                String[] w1 = d1.split("-");
                String[] w2= d2.split("-");

                if (d1.equals(d2)){
                    return (int)( u2.getNooflistenings() -u1.getNooflistenings());
                }

                return (int) (u2.getDateAdded() - u1.getDateAdded()) ;
            }
        });

        var a=2;
        int nr =0;

        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for(Stream stream: allStreams){
            if (allStreams.indexOf(stream) > 2){
                break;
            }
            Streamer streamer = getStreamerById(stream.getStreamerId(),streamers);
            jsonObjects.add(getStreamJSON(stream,streamer.getName()));
        }

        printJSONObject(jsonObjects);
    }
}
