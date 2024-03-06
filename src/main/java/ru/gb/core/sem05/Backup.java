package ru.gb.core.sem05;

import java.io.*;

public class Backup {
    public static void main(String[] args) throws IOException {
        File source = new File("./Folder");
        File destination = new File("./backup");

        if (source.exists()) {
            try {
                copyDirectory(source, destination);
            } catch (IOException e) {
                System.err.println("Произошла ошибка при копировании" + e.getMessage());
                throw e;
            }
        } else {
            System.out.println("Папка не существует.");
        }
        System.out.println("Резервное копирование завершено!");
    }

    /**
     * Копирует директорию, рекурсивно создавая вложенные директории и копируя в них файлы
     * @param source исходная директория
     * @param destination куда копировать
     * @throws IOException если произошла ошибка
     */

    private static void copyDirectory(File source, File destination) throws IOException {
        if (!destination.exists()) {
            destination.mkdir();
        }

        for (File file : source.listFiles()) {

                if (file.isDirectory()) {
                    copyDirectory(file, new File(destination, file.getName()));
                } else {
                    copyFile(file, new File(destination, file.getName()));
                }
            }
        }

    /**
     * Копирует файл побитово
     * @param source исходный файл
     * @param destination куда копировать
     * @throws IOException если произошла ошибка
     */
    private static void copyFile(File source, File destination) throws IOException {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
    }
}

