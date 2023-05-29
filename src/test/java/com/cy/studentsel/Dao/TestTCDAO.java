package com.cy.studentsel.Dao;

import com.cy.studentsel.DAO.TCDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author leaf-fulture
 * @date 2023/5/27 21:20
 */
@SpringBootTest
public class TestTCDAO {
    @Autowired
    @Qualifier("TCDAO")
    private TCDAO tcDAO;
    @Test
    public void testQueryAllCourse(){
        System.out.println(tcDAO.queryAllTC());
    }
}
