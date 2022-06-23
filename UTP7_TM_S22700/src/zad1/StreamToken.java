package zad1;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class StreamToken {
    StreamTokenizer streamTokenizer;
    int value;
    int currentToken;

    public StreamToken(Reader reader) throws IOException {
        streamTokenizer = new StreamTokenizer(reader);
        streamTokenizer.slashSlashComments(true);
        streamTokenizer.slashStarComments(true);
        this.value = 0;
        this.currentToken = streamTokenizer.nextToken();
    }

    public StreamTokenizer getStreamTokenizer() {
        return streamTokenizer;
    }

    public void setStreamTokenizer(StreamTokenizer streamTokenizer) {
        this.streamTokenizer = streamTokenizer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(int currentToken) {
        this.currentToken = currentToken;
    }
}
