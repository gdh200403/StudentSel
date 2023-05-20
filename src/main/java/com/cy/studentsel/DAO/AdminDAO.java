package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.AdminRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "AdminDAO")
public interface AdminDAO {
    List<AdminRecord> queryAlladmin();

    public AdminRecord queryadminByID(@Param("admin_id") String ID);

    public int updateadmin(AdminRecord admin_Record);

    public int deleteadmin(@Param("admin_id") String ID);

    public int addadmin(AdminRecord admin_Record);
}
