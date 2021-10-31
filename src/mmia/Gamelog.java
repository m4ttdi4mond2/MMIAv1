package mmia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matt
 */
public class Gamelog implements Comparable<Gamelog> {
    private int GlID;
    private String Gldatetime;
    private String Glemail;
    private int Glscore;
    
    
    public Gamelog(){
    super();
}
    public Gamelog(int id, String dt, String email, int score){
        super();
        this.GlID = id;
        this.Gldatetime = dt;
        this.Glemail = email;
        this.Glscore = score;
    }
    
    public void setGlID(int id){
        this.GlID = id;
    }
    
   public void setGldatetime(String dt){
       this.Gldatetime = dt;
   }
   
   public void setGlemail(String email){
       this.Glemail = email;
   }
   
   public void setGlscore(int score){
       this.Glscore = score;
       
   }
   
   public int getGlID(){
    return this.GlID;
}
   
   public String getGldatetime(){
       return this.Gldatetime;
   }
   
   public String getGlemail(){
       return this.Glemail;
   }
   
   public int getGlscore(){
       return this.Glscore;
   }
   
   @Override
   public int compareTo(Gamelog record){
       int cmp = ((Gamelog) record).getGlID();
       
       return cmp - this.GlID;
   }
   
}


