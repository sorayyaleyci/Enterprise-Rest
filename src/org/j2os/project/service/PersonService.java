package org.j2os.project.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.j2os.project.entity.Person;
import org.j2os.project.repository.PersonDA;

public class PersonService {
    private static PersonService personService= new PersonService();

    public PersonService() {
    }
    public static PersonService getInstance(){
        return personService;
    }
    public void save(Person person) throws Exception{
        try(PersonDA personDA=new PersonDA()){
            personDA.insert(person);
            personDA.commit();
            System.out.println("ps.save");
        }
    }
    public void update(Person person) throws Exception{
        try(PersonDA personDA=new PersonDA()){
            personDA.update(person);
            personDA.commit();
        }
    }
    public void remove(Person person) throws Exception{
        try(PersonDA personDA=new PersonDA()){
            personDA.delete(person);
            personDA.commit();
        }
    }
    public String findAll() throws Exception{
        try(PersonDA personDA=new PersonDA()){
            return personDA.selectAll();
        }
    }
    public String findOne(Person person) throws Exception{
        try(PersonDA personDA=new PersonDA()){

            return personDA.selectOne(person);
        }
    }


}
