package dbtest.dao;

import dbtest.emtity.User;
import dbtest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    public JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(User user) {
        String sql = "INSERT into user (name,email,age) values (?,?,?)";
        jdbcTemplate.update(sql, user.getName(),user.getEmail(),user.getAge());


    }

    @Override
    public User getById(int id) {
    String sql =  "SELECT * from user where id=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public void update(User user) {
        String sql ="update user set name = ?,email =?, age=? where id=?";
    jdbcTemplate.update(sql, user.getName(),user.getEmail(),user.getAge(),user.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE  from user where id=?";
    jdbcTemplate.update(sql,id);
    }
}
