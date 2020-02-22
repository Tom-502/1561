package com.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection1 {

        private static String URL = "jdbc:mysql://127.0.0.1:3306/yeruicao?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
        private static String USER = "root";
        private static String PASSWORD = "web.qq20001127";
        private static String DRIVER = "com.mysql.cj.jdbc.Driver";
        public Connection1()
        {
        }

        public static java.sql.Connection getConnection()
        {
            java.sql.Connection connection = null;
            try
            {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            catch (ClassNotFoundException cnfe)
            {
                cnfe.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return connection;
        }

        public static void close(PreparedStatement preparedStatement)
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }

        public static void close(java.sql.Connection connection)
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public static void close(ResultSet resultSet)
        {
            if (resultSet != null)
            {
                try
                {
                    resultSet.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
}
