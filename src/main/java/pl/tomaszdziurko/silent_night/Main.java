package pl.tomaszdziurko.silent_night;

import java.io.IOException;

public class Main {

    public static int DEFAULT_VELOCITY = 70;
    public static int DEFAULT_TEMPO = 100;

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("say Merry Christmas");
        SilentNightPlayer player = new SilentNightPlayer(DEFAULT_TEMPO, DEFAULT_VELOCITY);
        player.start();
    }
}
