package com.pushpinder.exception;

public class SlotNotOccupiedException extends Exception{
    public SlotNotOccupiedException() {
        super("Slot is not occupied");
    }
}
