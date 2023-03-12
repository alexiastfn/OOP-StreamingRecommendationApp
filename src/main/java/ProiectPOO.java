import Models.Stream;
import Models.Streamer;
import Models.User;
import Strategy.Context;
import Strategy.StrategyInterface;


import java.util.ArrayList;

import static Utils.Commands.*;
import static Utils.Facade.*;

public class ProiectPOO {
    public static  ArrayList<User> users;
    public static  ArrayList<Stream> streams;
    public static  ArrayList<Streamer> streamers;

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
            System.exit(0);
        }

        String streamersFile = args[0];
        String streamsFile = args[1];
        String usersFile = args[2];
        String commandsFile = args[3];

        users = getUsers(usersFile);
        streams = getStreams(streamsFile);
        streamers = getStreamers(streamersFile);
        ArrayList<String> commands = getCommands(commandsFile);


        for (String command : commands) {
            String[] parts = command.split(" ");

            switch (parts[1]) {
                case "LIST":
                    LIST(parts[0], streams,streamers,users);
                    break;
                case "ADD":
                    ADD(parts,streams);
                    break;
                case "DELETE":
                    DELETE(parts[0],parts[2] , streams);
                    break;
                case "LISTEN":
                    LISTEN(parts[0],parts[2], streams, users);
                    break;
                case "RECOMMEND":
                    RECOMMEND(parts, streams, users, streamers);
                    break;
                case "SURPRISE":
                    SURPRISE(parts,streams, users, streamers);
            }
        }

        Context context = new Context(new StrategyInterface() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        var a = context.executeStrategy(users.get(0));
//        System.out.println(context.executeStrategy(users.get(0)));
    }
}
