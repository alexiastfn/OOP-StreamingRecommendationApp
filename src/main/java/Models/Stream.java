package Models;


public class Stream extends Base{
    private Integer streamType;
    private Integer streamGenre;
    private Long nooflistenings;
    private Long length;
    private Long dateAdded;
    private Integer streamerId;

    public Stream( Integer id,String name) {
        super(id,name);
    }

    public Stream(StreamBuilder builder){
        super(builder.getId(), builder.getName());
        this.dateAdded = builder.dateAdded;
        this.streamGenre = builder.streamGenre;
        this.length = builder.length;
        this.streamType = builder.streamType;
        this.nooflistenings = builder.nooflistenings;
        this.streamerId = builder.streamerId;
    }

    public Stream(Stream stream){
        super(stream);
        this.dateAdded = stream.dateAdded;
        this.streamGenre = stream.streamGenre;
        this.length = stream.length;
        this.streamType = stream.streamType;
        this.nooflistenings = stream.nooflistenings;
        this.streamerId = stream.streamerId;

    }

    public Integer getStreamType() {
        return streamType;
    }

    public Stream setStreamType(Integer streamType) {
        this.streamType = streamType;
        return this;
    }

    public Integer getStreamGenre() {
        return streamGenre;
    }

    public Stream setStreamGenre(Integer streamGenre) {
        this.streamGenre = streamGenre;
        return this;
    }

    public Long getNooflistenings() {
        if(nooflistenings != null)
            return nooflistenings;
        else
            return 0L;
    }

    public Stream setNooflistenings(Long nooflistenings) {
        this.nooflistenings = nooflistenings;
        return this;
    }

    public Integer getStreamerId() {
        return streamerId;
    }

    public Stream setStreamerId(Integer streamerId) {
        this.streamerId = streamerId;
        return this;
    }

    public Long getLength() {
        return length;
    }

    public Stream setLength(Long length) {
        this.length = length;
        return this;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public Stream setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    @Override
    public Stream clone(){
        return new Stream(this);
    }

    public static class StreamBuilder extends Base{
        private Integer streamType;
        private Integer streamGenre;
        private Long nooflistenings;
        private Long length;
        private Long dateAdded;
        private Integer streamerId;

        public StreamBuilder(Integer id, String name) {
            super(id, name);
        }

        public StreamBuilder(StreamBuilder streamBuilder){
            super(streamBuilder);
            this.dateAdded = streamBuilder.dateAdded;
            this.streamGenre = streamBuilder.streamGenre;
            this.length = streamBuilder.length;
            this.streamType = streamBuilder.streamType;
            this.nooflistenings = streamBuilder.nooflistenings;
            this.streamerId = streamBuilder.streamerId;
        }

        public StreamBuilder setLength(Long length){
            this.length = length;
            return this;
        }

        public StreamBuilder setStreamType(Integer streamType){
            this.streamType = streamType;
            return this;
        }

        public StreamBuilder setDateAdded(Long dateAdded){
            this.dateAdded = dateAdded;
            return this;
        }

        public StreamBuilder setStreamerId(Integer streamerId){
            this.streamerId = streamerId;
            return this;
        }

        public StreamBuilder setNumberOfListenings(Long nooflistenings){
            this.nooflistenings = nooflistenings;
            return this;
        }


        public StreamBuilder setGenre(Integer genre){
            this.streamGenre = genre;
            return this;
        }

        public Stream build(){
            return new Stream(this);
        }

        public StreamBuilder clone(){
            return new StreamBuilder(this);
        }
    }
}
