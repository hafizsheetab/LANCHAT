package Test;

import Server.Database;
import Server.Storage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Testing {
    public static void main(String[] args) {
        Storage.storeObject("khanki","stringtest");
        String string = (String) Storage.getObject("stringtest");
        System.out.println(string);
        }

}
