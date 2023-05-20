package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.TCRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "TCDAO")
public interface TCDAO {
    List<TCRecord> queryAllTC();

    public TCRecord queryTCByID(@Param("TC_id") String ID);

    public int updateTC(TCRecord tcRecord);

    public int deleteTC(@Param("tc_id") String ID);

    public int addTC(TCRecord tcRecord);
}
