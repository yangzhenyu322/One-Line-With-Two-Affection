import java.io.BufferedInputStream;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

/**
 * @author Brandon B. Lin
 */
public class MP3Player {

    public MP3Player(String filename) {
        this.filename = filename;
    }

    public void play() {
        try {
            BufferedInputStream buffer = new BufferedInputStream(
                    new FileInputStream(filename));
            player = new Player(buffer);
            player.play();
        } catch (Exception e) {
        }

    }

    private String filename;
    private Player player;

}
