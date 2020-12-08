import models.Animals;
import models.location;
import models.ranger;
import models.sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", location.all());
            model.put("animals", Animals.all());
            model.put("rangers", ranger.all());
            return new ModelAndView(model, "sightingForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String location = req.queryParams("locationName");
            int animalId = Integer.parseInt(req.queryParams("animalId"));
            String type = Animals.find(animalId).getType();
            sighting newSighting = new sighting(rangerName, location, animalId, type);
            newSighting.save();
            res.redirect("/sightings");
            return null;
        }, new HandlebarsTemplateEngine());

    }

}