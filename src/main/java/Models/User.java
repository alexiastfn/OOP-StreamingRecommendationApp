package Models;

import Strategy.StrategyInterface;

import java.util.ArrayList;

public class User extends Base implements StrategyInterface {
    private ArrayList<Integer> streams;

    public User(Integer id, String name, ArrayList<Integer> streams) {
        super(id,name);
        this.streams = streams;
    }

    public User(User user){
        super(user);
        this.streams = user.streams;
    }

    public ArrayList<Integer> getStreams() {
        return streams;
    }

    public User setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
        return this;
    }

    @Override
    public String toString(){
        return this.getId() + " " + this.getName() + " " + this.streams.toString();
    }

    @Override
    public User clone() {
        return new User(this);
    }
}
