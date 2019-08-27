package com.netcracker.services;

import com.netcracker.dao.AdminDao;
import com.netcracker.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    @Autowired
    private AdminDao adminDao;

    public Admin getAdminByUsernameAndPassword(Admin admin){
        return adminDao.getAdminByUsernameAndPassword(admin);
    }
}
