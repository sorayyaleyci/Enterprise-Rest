package org.j2os.project.repository;


import org.j2os.project.common.JDBC;
import org.j2os.project.common.NotFindException;
import org.j2os.project.entity.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PersonDA implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private long count=1;
    public PersonDA() throws Exception {
        connection= JDBC.getConnection();
    }
    public void insert(Person person)throws Exception{
        preparedStatement = connection.prepareStatement("select person_seq.nextval id from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getLong("id"));
        preparedStatement=connection.prepareStatement("insert into person(id,name,family) values (?,?,?)");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.setString(2,person.getName());
        preparedStatement.setString(3,person.getFamily());
        preparedStatement.executeUpdate();
        System.out.println("save");



    }
    public void update(Person person)throws Exception{
        preparedStatement=connection.prepareStatement("update person set name=? , family=? where id=?");
        preparedStatement.setLong(3, person.getId());
        preparedStatement.setString(1,person.getName());
        preparedStatement.setString(2,person.getFamily());
        preparedStatement.executeUpdate();
    }
    public void delete(Person person)throws Exception{
        preparedStatement=connection.prepareStatement("delete person where id=?");
        preparedStatement.setLong(1,person.getId());
        preparedStatement.executeUpdate();
    }
    public String selectAll()throws Exception{
        preparedStatement=connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",resultSet.getString("id"));
            jsonObject.put("nama",resultSet.getString("name"));
            jsonObject.put("family",resultSet.getString("family"));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
    public String selectOne(Person person)throws Exception{
        preparedStatement=connection.prepareStatement("select * from person where id=?");
        preparedStatement.setLong(1,person.getId());
        ResultSet resultSet  = preparedStatement.executeQuery();
        if (resultSet.next()){
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("id",resultSet.getString("id"));
            jsonObject.put("name",resultSet.getString("name"));
            jsonObject.put("family",resultSet.getString("family"));
            return jsonObject.toJSONString();

        }
        throw new NotFindException();

    }
    public void commit()throws Exception{
        connection.commit();
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
