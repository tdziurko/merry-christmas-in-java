package pl.tomaszdziurko.silent_night;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import static pl.tomaszdziurko.silent_night.NoteType.EIGHTH_NOTE;
import static pl.tomaszdziurko.silent_night.NoteType.FULL_NOTE;
import static pl.tomaszdziurko.silent_night.NoteType.HALF_NOTE;
import static pl.tomaszdziurko.silent_night.NoteType.QUARTER_NOTE;
import static pl.tomaszdziurko.silent_night.NoteType.QUARTER_NOTE_AND_HALF;
import static pl.tomaszdziurko.silent_night.NoteType.THREE_QUARTERS_NOTE;

public class SilentNightPlayer {

    public static final int OCTAVE_LENGTH = 12;

    private final Synthesizer synthesizer;
    private final MidiChannel channel;
    private final int tempo;
    private final int velocity;

    public SilentNightPlayer(int tempo, int velocity) {

        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel[] channels = synthesizer.getChannels();
            channel = channels[0];
            this.tempo = tempo;
            this.velocity = velocity;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }

    private void close() {
        synthesizer.close();
    }

    public void start() {
        pause(FULL_NOTE);
        // First line
        playSound("G", QUARTER_NOTE_AND_HALF);
        playSound("A", EIGHTH_NOTE);
        playSound("G", QUARTER_NOTE);

        playSound("E", THREE_QUARTERS_NOTE);

        playSound("G", QUARTER_NOTE_AND_HALF);
        playSound("A", EIGHTH_NOTE);
        playSound("G", QUARTER_NOTE);

        playSound("E", THREE_QUARTERS_NOTE);

        playSound("D", HALF_NOTE, 2);
        playSound("D", QUARTER_NOTE, 2);

        playSound("H", HALF_NOTE);
        playSound("H", QUARTER_NOTE);

        playSound("C", HALF_NOTE, 2);
        playSound("C", QUARTER_NOTE, 2);

        playSound("G", THREE_QUARTERS_NOTE);

        playSound("A", HALF_NOTE);
        playSound("A", QUARTER_NOTE);

        // Second line
        playSound("C", QUARTER_NOTE_AND_HALF, 2);
        playSound("H", EIGHTH_NOTE);
        playSound("A", QUARTER_NOTE);

        playSound("G", QUARTER_NOTE_AND_HALF);
        playSound("A", EIGHTH_NOTE);
        playSound("G", QUARTER_NOTE);

        playSound("E", HALF_NOTE);
        playSound("E", QUARTER_NOTE);

        playSound("A", HALF_NOTE);
        playSound("A", QUARTER_NOTE);

        playSound("C", QUARTER_NOTE_AND_HALF, 2);
        playSound("H", EIGHTH_NOTE);
        playSound("A", QUARTER_NOTE);

        playSound("G", QUARTER_NOTE_AND_HALF);
        playSound("A", EIGHTH_NOTE);
        playSound("G", QUARTER_NOTE);

        playSound("E", HALF_NOTE);
        playSound("E", QUARTER_NOTE);

        playSound("D", HALF_NOTE, 2);
        playSound("D", QUARTER_NOTE, 2);

        // Third line
        playSound("F", QUARTER_NOTE_AND_HALF, 2);
        playSound("D", EIGHTH_NOTE, 2);
        playSound("H", QUARTER_NOTE);

        playSound("C", THREE_QUARTERS_NOTE, 2);

        playSound("E", HALF_NOTE, 2);
        pause(QUARTER_NOTE);

        playSound("C", QUARTER_NOTE, 2);
        playSound("G", QUARTER_NOTE);
        playSound("E", QUARTER_NOTE);

        playSound("G", QUARTER_NOTE_AND_HALF);
        playSound("F", EIGHTH_NOTE);
        playSound("D", QUARTER_NOTE);

        playSound("C", THREE_QUARTERS_NOTE);

        pause(FULL_NOTE);
        close();
    }

    private void playSound(String value, NoteType note) {
        playSound(value, note, 1);
    }

    private void playSound(String value, NoteType note, int octave) {
        int noteNumber = stringToNoteNumber(value, octave);
        channel.noteOn(noteNumber, velocity);
        sleep(tempo * note.length);
        channel.noteOff(noteNumber);
    }

    private void pause(NoteType note) {
        sleep(tempo * note.length);
    }

    private int stringToNoteNumber(String value, int octaveNumber) {
        int baseValue;
        switch (value.toUpperCase()) {
            case "C":
                baseValue = 60;
                break;
            case "D":
                baseValue = 62;
                break;
            case "E":
                baseValue = 64;
                break;
            case "F":
                baseValue = 65;
                break;
            case "G":
                baseValue = 67;
                break;
            case "A":
                baseValue = 69;
                break;
            case "H":
                baseValue = 71;
                break;
            default:
                throw new IllegalArgumentException("Not supported note value " + value);
        }
        return baseValue + (octaveNumber-1) * OCTAVE_LENGTH;
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
