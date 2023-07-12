package archive;

import java.io.IOException;

public class Archiver {
    public static void main(String[] args) throws IOException {
        ViewArchive viewArchive = new ViewArchive();
        viewArchive.initFrameServer();
    }
}