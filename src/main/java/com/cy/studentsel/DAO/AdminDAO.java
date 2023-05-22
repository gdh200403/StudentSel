package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.AdminRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "AdminDAO")
public interface AdminDAO {
    List<AdminRecord> queryAllAdmin();

    public AdminRecord queryAdminByID(@Param("admin_id") String ID);

    public int updateAdmin(AdminRecord admin_Record);

    public int deleteAdmin(@Param("admin_id") String ID);

    public int addAdmin(AdminRecord admin_Record);
}
