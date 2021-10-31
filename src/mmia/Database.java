package mmia;


import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;


public class Database {
    RandomAccessFile file;
    
    public Database(String fileString) throws IOException {
        file = new RandomAccessFile(fileString, "rw");
    }
    
    public void close() throws IOException{
        if (file !=null){
            file.close();
        }
    }
    
    public UserRecord getRecord(int id) throws IOException{
        UserRecord record = new UserRecord();
        if (id< 1) {
            throw new IllegalArgumentException("Incorrect ID");
        }
        else {
            System.out.println("success");
        }
        
        file.seek((id - 1)* UserRecord.SIZE);
        if(record.readFromFile(file) == true){
            return record;
        }else{
            return null;
        }
        
    }
    
    public void insertRecord(UserRecord record) throws IOException{
        if(getRecord(record.getId())== null){
            file.seek((record.getId()-1)* UserRecord.SIZE);
            record.writeToFile(file);
        }else{
            System.out.println("Record already exist");
        }
    }
    
    public void updateRecord(UserRecord record) throws IOException{
        if(getRecord(record.getId()) != null){
            
            file.seek((record.getId()-1)* UserRecord.SIZE);
            record.writeToFile(file);
        }else{
            System.out.println("cannot update: does not exist");
        }
    }
    
    public void showAllRecords(){
        UserRecord record = new UserRecord();
        try {
            file.seek(0);
            while (true){
                if (record.readFromFile(file) == true){
                    System.out.println(record.getEmail());
                    file.seek(record.getId()* UserRecord.SIZE);
                } else break;
               
            }
        } catch (Exception e) {
        }
        
    }

  public UserRecord getRecordByUSPS(String us, String ps) throws IOException {
      UserRecord record = new UserRecord();
      
      try {
          file.seek(0);
          while (true){
              record = new UserRecord ();
              if(record.readFromFile(file) == true){
                  if(record.getEmail().equals(us) && record.getPassword().equals(ps)){
                      return record;
                  }
                  file.seek(record.getId() * UserRecord.SIZE);
              } else {
                  break;
              }
              
          }
          } catch (EOFException e){
          }
     return null;
  }
  
  public int getLastId(){
      UserRecord record = new UserRecord();
      int id = -1;
      try {
          file.seek(0);
          while(true){
              if (record.readFromFile(file) == true){
                  file.seek(record.getId() * UserRecord.SIZE);
                  id = record.getId();
              } else {
                  break;
              }
          }
      } catch (Exception e) {
      }
      return id;
  }
  
  public ArrayList<User> getAllRecordByScore() {
      ArrayList<User> arrList = new ArrayList<User>();
      UserRecord record = new UserRecord();
      
      try {
          file.seek(0);
          while (true){
              record = new UserRecord();
              if (record.readFromFile(file) == true) {
                  arrList.add(record);
                  file.seek(record.getId() * UserRecord.SIZE);
              } else{
                  break;
              }
          
      }
          Collections.sort(arrList);
      } catch (Exception e) {
          
      }
      return arrList;
  }
      
      
      
}
