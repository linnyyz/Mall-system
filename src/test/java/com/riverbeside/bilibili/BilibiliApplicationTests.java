package com.riverbeside.bilibili;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BilibiliApplicationTests {

    @Autowired(required = false)
    private DataSource dataSource;

    @Test
    void contextLoads() {

    }

    /**
     * HikariProxyConnection@962784388 wrapping com.mysql.cj.jdbc.ConnectionImpl@2cc75b25
     */
    @Test
    void getConnection() {
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}
