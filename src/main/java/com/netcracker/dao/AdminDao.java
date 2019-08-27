package com.netcracker.dao;

import com.netcracker.dto.Admin;
import com.netcracker.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Admin getAdminByUsernameAndPassword(Admin admin){
        Object[] objects = new Object[]{admin.getUsername(),admin.getPassword()};
        int i = jdbcTemplate.queryForObject(Constant.getAdminByUsernameAndPassword,objects, Integer.class);
        if(i == 1){
            return admin;
        }
        else {
            return null;
        }
    }
}
