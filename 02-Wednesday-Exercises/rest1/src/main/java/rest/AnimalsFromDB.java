package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Mathias
 */
@Path("animals_db")
public class AnimalsFromDB {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalsFromDB
     */
    public AnimalsFromDB() {
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("id") int id){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.id = :id", Animal.class);
            query.setParameter("id", id);
            List<Animal> animal = query.getResultList();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
    
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
            query.setParameter("type", type);
            List<Animal> animal = query.getResultList();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
    
    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByRandomId(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Animal> count = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animalid = count.getResultList();
            int ids = animalid.size();
            Random rand = new Random();
            int n = rand.nextInt(ids) + 1;
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.id = :id", Animal.class);
            query.setParameter("id", n);
            List<Animal> animal = query.getResultList();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }
}