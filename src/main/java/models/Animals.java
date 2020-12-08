package models;

public class Animals {
    public String name;
    public  int id;
    public String type;


    public  static final String ANIMAL_TYPE="Common";


    public Animals(String name){
        if(name.equals("")){
            throw new  IllegalAccessException("Please enter an animal name.");
        }
    }



}
