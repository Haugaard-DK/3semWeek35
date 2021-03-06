/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author Mathias
 */
@Path("animals")
public class AnimalsDemo {

    @Context
    private UriInfo context;
    
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static List<AnimalNoDB> animals = new ArrayList();

    /**
     * Creates a new instance of AnimalsDemo
     */
    public AnimalsDemo() {
        if(animals.isEmpty()){
            animals.add(new AnimalNoDB("Dog", "Vuf"));
            animals.add(new AnimalNoDB("Cat", "Miaw"));
            animals.add(new AnimalNoDB("Whale", "Whalesounds"));
            
        }
        
    }

    /**
     * Retrieves representation of an instance of rest.AnimalsDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
        return "Vuf.... (Message from a dog)";
        
    }
    
    @Path("animal_list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
       return "[Dog, Cat, Mouse, Bird]";
    }
    
    @Path("animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        String jsonString = GSON.toJson(animals);
        return jsonString;
        
    }

    /**
     * PUT method for updating or creating an instance of AnimalsDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
