package Logger.controllers;

import Logger.Interfaces.File;

public class LogFile implements File {
    private StringBuilder sb;

    public LogFile() {
        this.sb = new StringBuilder();
    }

    @Override
    public void write(String text) {
        this.sb.append(text);
    }

    @Override
    public int getSize() {
        int sum = 0;
        for (int i = 0; i < this.sb.length(); i++) {
            char symbol = this.sb.charAt(i);
            if(Character.isAlphabetic(symbol)){
                sum += symbol;
            }
        }
        return sum;
    }
}
