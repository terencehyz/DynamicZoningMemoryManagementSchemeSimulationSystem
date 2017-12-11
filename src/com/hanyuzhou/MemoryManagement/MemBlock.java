package com.hanyuzhou.MemoryManagement;

public class MemBlock {
    private int startAddress;
    private int size;
    private int serial;
    private int Fserial;
    private String name;

    public int getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFserial() {
        return Fserial;
    }

    public void setFserial(int fserial) {
        Fserial = fserial;
    }
}
