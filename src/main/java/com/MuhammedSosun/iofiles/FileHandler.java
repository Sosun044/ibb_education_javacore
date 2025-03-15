package com.MuhammedSosun.iofiles;

import com.MuhammedSosun.Utils.SpecialColor;

import java.io.*;

public class FileHandler implements IFileHandlerInterface{

    private String filePath ="";

    public FileHandler() {
        filePath = "isnotfilename.txt";
    }


    @Override
    public void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()){
            try {
                if (file.createNewFile()){
                    System.out.println(SpecialColor.YELLOW + filePath + " oluşturuldu." + SpecialColor.RESET);
                }
            }catch (IOException e){
                System.out.println(SpecialColor.RED + "Dosya oluşturulurken hata oluştu!" + SpecialColor.RESET);
                e.printStackTrace();
            }
        }

    }

    @Override
    public synchronized void writeFile(String data) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void readFile(String data) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null){

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        if (filePath == null || filePath.isEmpty() || filePath.isBlank()){
            this.filePath="isnotfilename.txt";
        }
        this.filePath = filePath;
    }
}
