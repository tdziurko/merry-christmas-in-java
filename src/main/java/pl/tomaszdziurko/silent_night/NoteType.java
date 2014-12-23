package pl.tomaszdziurko.silent_night;

public enum NoteType {

    FULL_NOTE(16),
    THREE_QUARTERS_NOTE(12),
    HALF_NOTE(8),
    QUARTER_NOTE_AND_HALF(6),
    QUARTER_NOTE(4),
    EIGHTH_NOTE(2),
    SIXTEENTH_NOTE(1);

    int length;

    private NoteType(int length) {
        this.length = length;
    }
}
