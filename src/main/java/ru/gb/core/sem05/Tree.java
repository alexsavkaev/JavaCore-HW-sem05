package ru.gb.core.sem05;

import java.io.File;

public class Tree {
    public static void main(String[] args) {

        print(new File("."),"", true);

    }
    static void print(File file,String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        }else{
            System.out.print("├─");
            indent += "│  ";
        }
        System.out.println(file.getName());
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        int subdirTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
            {
                subdirTotal++;
            }
        }
        int subdirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
            {
                print(files[i],indent, subdirTotal==++subdirCounter);

            }else {
                System.out.print(indent);
                    System.out.print("└─");
                System.out.println(files[i].getName());
            }
        }
    }
}
