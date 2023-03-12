package Models;

import Strategy.StrategyInterface;

public class Streamer extends Base implements StrategyInterface {
    private Integer streamerType;

    public Streamer(Integer id, String name, Integer streamerType) {
        super(id,name);
        this.streamerType = streamerType;
    }

    public Streamer(Streamer streamer){
        super(streamer);
        this.streamerType = streamer.streamerType;
    }

    public Integer getStreamerType() {
        return streamerType;
    }

    public Streamer setStreamerType(Integer streamerType) {
        this.streamerType = streamerType;
        return this;
    }

    @Override
    public String toString(){
        return this.getId() + " " + this.getName() + " " +this.streamerType;
    }

    public Streamer clone(){
        return new Streamer(this);
    }


}
