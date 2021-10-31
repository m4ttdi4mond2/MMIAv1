package mmia;


import mmia.User;
import java.io.IOException;
import java.io.RandomAccessFile;


public class UserRecord extends User {
    
    private static final int strSize = 26;
    static final int SIZE = (2 * Integer.BYTES)+ (2 * (Character.BYTES * strSize));
    
    public UserRecord(){
        this("","",0,0);
        
    }
     public UserRecord(String email,String password, int highscore, int id){
        setId(id);
        setEmail(email);
        setPassword(password);
        setHighscore(highscore);   
    }
     
     private String readString(RandomAccessFile file) throws IOException{
         char[] s = new char[strSize];
         for (int i=0; i < s.length; i++){
             s[i] = file.readChar();
         }
         return new String(s).trim();
     }
     
     private void writeString(RandomAccessFile file, String s) throws IOException{
         StringBuffer buffer = null;
         if (s != null){
             buffer = new StringBuffer(s);
         }
         else {
             buffer = new StringBuffer(strSize);
         }
         buffer.setLength(strSize);
         file.writeChars(buffer.toString());
     }
     
     public boolean readFromFile(RandomAccessFile file){
         try {
             int id = file.readInt();
             System.out.println("id:"+id);
             if(id > 0){
                 setId(id);
                 setEmail(readString(file));
                 setPassword(readString(file));
                 setHighscore(file.readInt());
                 return true;
             }
             else{
                 return false;
             }
         } catch (Exception e) {
             System.err.println("error"+e);
             return false;
         }
     
     }
     
     public void writeToFile(RandomAccessFile file) throws IOException{
         file.writeInt(getId());
         writeString(file, getEmail());
         writeString(file, getPassword());
         file.writeInt(getHighscore());
         
     }
     
   
    
}
