package Utilities;

import java.io.*;

public class Log {
    FileOutputStream file;
    File yourFile;
    public Log(String name){
        System.out.println(name);
        ClassLoader loader = getClass().getClassLoader();
        if (loader.getResource(name) == null) {
            System.out.println("Fichero does not exist.");
            System.exit(1);
        } else {

            try {

                yourFile = new File(loader.getResource(name).getFile());
                yourFile.createNewFile();
                this.file = new FileOutputStream(yourFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeLog(String message){
        message = message+"\n";
        try {
            this.file.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void closeLog(){
        try {
            this.file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
