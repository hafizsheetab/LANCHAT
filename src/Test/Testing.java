package Test;

import GUI.Storage;

public class Testing {
    public static void main(String[] args) {
        Storage.storeObject("khanki","stringtest");
        String string = (String) Storage.getObject("stringtest");
        System.out.println(string);
        }

}
