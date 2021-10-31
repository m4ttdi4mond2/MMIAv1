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
public class User implements Comparable<User>{
    private String email;
    private String password;
    private int highscore;
    private int id;
    
    public User(){
        this("","",0,0);
    }
    public User(String email,String password, int highscore, int id){
        super();
        this.id = id;
        this.email= email;
        this.password = password;
        this.highscore = highscore;   
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public int getHighscore(){
        return this.highscore;
    }
    public int getId(){
        return this.id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setHighscore(int highscore){
        this.highscore = highscore;
    }
    public void setId(int id){
        this.id = id;
    }
    
    @Override
    public int compareTo(User record){
        int cmp = ((User)record).getHighscore();
        
        return cmp - this.highscore;
    }
    
}
