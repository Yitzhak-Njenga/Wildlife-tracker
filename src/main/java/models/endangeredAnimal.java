package models;

import org.sql2o.Connection;

import java.util.List;

public class endangeredAnimal {
    private String health;
    private String age;
    private String type;

    public static final String ANIMAL_TYPE = "Endangered";

    private String name;
    private int id;

    public endangeredAnimal(String name, String health, String age) {

        this.health = health;
        this.age = age;
        this.name= name;

        setType(ANIMAL_TYPE);
    }


    @Override
    public boolean equals(Object otherAnimal) {
        if (this == otherAnimal) return true;
        if (otherAnimal == null || getClass() != otherAnimal.getClass()) return false;
        endangeredAnimal animal = (endangeredAnimal) otherAnimal;
        return (this.name.equals(animal.name));
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type,health,age) VALUES (:name,:type,:health,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
            setId(id);


        }
    }

    public static List<endangeredAnimal> getAll() {
        String sql = "SELECT * FROM animals WHERE type = 'Endangered' ORDER BY id ASC";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(endangeredAnimal.class);
        }
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getAnimalType() {
        return ANIMAL_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getHealth() {
        return health;
    }

}