package org.j2os.project.controller;

import org.j2os.project.common.ExceptionWrapper;
import org.j2os.project.entity.Person;
import org.j2os.project.service.PersonService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
@Path("/person")

public class PersonController {
    @Path("/save")
    @Produces("application/json")
    @GET
    public String save(@QueryParam("Name") String name, @QueryParam("Family") String family) {
        Person person=new Person(name,family);
        try{
        PersonService.getInstance().save(person);
       // return "s";
               return PersonService.getInstance().findOne(person);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return ExceptionWrapper.getMassage(e);
        }
    }

    @Path("/update")
    @Produces("application/json")
    @GET
    public String update(@QueryParam("id") String id,@QueryParam("Name") String name, @QueryParam("Family") String family) {
        Person person = new Person(Long.parseLong(id),name,family);
        try{
        PersonService.getInstance().update(person);
        return PersonService.getInstance().findOne(person);}
        catch (Exception e){
            return ExceptionWrapper.getMassage(e);
        }
    }
}
