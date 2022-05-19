package za.co.wethinkcode.robotworlds;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SoundPlayer implements Runnable {
    private final String fileName;

    public SoundPlayer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            URL url = SoundPlayer.class.getResource(fileName);
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            Thread.sleep(1000);
            clip.close();
        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | InterruptedException
                 | IOException ignored) {
        }
    }
}
