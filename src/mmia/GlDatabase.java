package mmia;


import mmia.Gamelog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matt
 */
public class GlDatabase {
    
    public void writeToFile(List<Gamelog> list, String file){
        Formatter outFile = null;
        try {
            outFile = new Formatter(file);
            for (Gamelog log : list){
                outFile.format("%d,%s,%s,%d\n", log.getGlID(), log.getGldatetime(), log.getGlemail(), log.getGlscore());
            }
        } catch (Exception e) {
        } finally {
            if(outFile != null){
                outFile.close();
            }
        }
        
    }
    
    public List<Gamelog> readFromFile(String file){
        List<Gamelog> glst = new ArrayList<>();
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(file));
            while (inFile.hasNextLine()){
                String line = inFile.nextLine();
                String[] arr = line.split(",");
                Gamelog log = new Gamelog();
                log.setGlID(Integer.parseInt(arr[0]));
                log.setGldatetime(arr[1]);
                log.setGlemail(arr[2]);
                log.setGlscore(Integer.parseInt(arr[3]));
                glst.add(log);
            }
        } catch (Exception e) {
        } finally{
            if (inFile != null){
                inFile.close();
            }
        }
        return glst;
    }
    
    public void appendToFile(int score, String file, String name){
        List<Gamelog> oldData = new ArrayList<>();
        oldData = readFromFile(file);
        Gamelog log = new Gamelog();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Date date = new Date();
        log.setGlID(oldData.get(oldData.size()-1).getGlID()+1);
        log.setGldatetime(formatter.format(date));
        log.setGlemail(name);
        log.setGlscore(score);
        oldData.add(log);
        writeToFile(oldData, file);
        
    }
}
