package models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static models.DB.sql2o;

public class sighting {
    private String location;
    private String name;
    private int animalId;
    private int id;
    private String type;
    private String age;
    private String health;
    public Timestamp timestamp_sighted;

    public sighting(String name,String location, int animalId){
        this.location = location;
        this.location= name;
        this.animalId = animalId;
    }

    public Timestamp getTimestamp_sighted(){return timestamp_sighted;}



    @Override
    public  boolean equals(Object otherSighting){
        if (this == otherSighting) return true;
        if(otherSighting == null || getClass() != otherSighting.getClass()) return false;
        sighting Sighting =(sighting)otherSighting;
        return Objects.equals(location,Sighting.location)&&
                Objects.equals(name,Sighting.name);
    }


    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (name,location,animalId,type,time_sighted,health,age) VALUES (:name,:location,:animalId,:type,now(),:health,age)";
            this.id = (int) con.createQuery(sql,true)
            .addParameter("name",this.name)
            .addParameter("location",this.location)
            .addParameter("animalId",animalId)
            .addParameter("type",type)
            .addParameter("age",age)
            .addParameter("health",health)
            .executeUpdate()
            .getKey();
        }
    }

    public static List<sighting> all(){
        String sql = "SELECT * sightings ORDER by id ";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(sighting.class);
        }

    }

    public static sighting find(int id){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM sightings where id = :id";
            sighting Sighting = (sighting) con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetch(sighting.class);
            return Sighting;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, name);
    }

    public String getRangerName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }


}


