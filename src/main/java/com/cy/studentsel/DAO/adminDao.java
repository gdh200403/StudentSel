package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.adminRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "adminDao")
public interface adminDao {
    List<adminRecord> queryAlladmin();

    public adminRecord queryadminByID(@Param("admin_id") String ID);

    public int updateadmin(adminRecord admin_Record);

    public int deleteadmin(@Param("admin_id") String ID);

    public int addadmin(adminRecord admin_Record);
}
