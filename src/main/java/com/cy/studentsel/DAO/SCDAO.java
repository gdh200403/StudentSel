package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.SCRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "SCDAO")
public interface SCDAO {
    List<SCRecord> queryAllSC();

    public SCRecord querySCByID(@Param("sc_id") String ID);

    public int updateSC(SCRecord scRecord);

    public int deleteSC(@Param("sc_id") String ID);

    public int addSC(SCRecord scRecord);
}
