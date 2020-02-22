package com.dao;


import com.pojo.Admini;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class AdminiDao { //将管理员与数据库连接

    ArrayList<Admini> adminis;
    ArrayList<Admini> adminis1;
    Connection connection;

    public AdminiDao (){
        adminis = new ArrayList<Admini>();
    }

    public AdminiDao(ArrayList<Admini> adminis) {
        this.adminis = adminis;
    }

    public ArrayList<Admini> getAdminis() {
        return adminis;
    }

    public void setAdminis(ArrayList<Admini> adminis) {
        this.adminis = adminis;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection connectionOpen()//打开数据库连接
    {
        connection = Connection1.getConnection();
        return connection;
    }

    public void connectionClose() //关闭数据库连接
    {
        Connection1.close(connection);
    }

    public boolean tableJudgment(String tableName) //判断此管理员表是否存在
    {
        connectionOpen();     //连接数据库
        ResultSet resultSet=null;
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables("yeruicao", null, tableName, null);
            if (resultSet.next())              //判断表是否已经存在
            {
                connectionClose();      //关闭连接
                Connection1.close(resultSet);
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        Connection1.close(resultSet);
        connectionClose();      //关闭连接
        return false;
    }

    public void createAdminiTable() //创建管理员表
    {
        if(!tableJudgment("Adminis"))       //判断表是否存在
        {
            connectionOpen();     //连接数据库
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "create table Adminis(UserName varchar(50),password varchar(50))";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)        //异常处理
            {
                e.printStackTrace();
            }
            finally
            {
                Connection1.close(preparedStatement);    //关闭连接
                connectionClose();
            }
        }
    }

    public boolean AddOneAdmini (Admini admini)  //插入一条管理员信息
    {
        if(tableJudgment("Adminis"))       //判断表是否存在
        {
            connectionOpen();     //连接数据库
            PreparedStatement preparedStatement = null;
            try
            {
                //sql语句
                String sql = "insert into Adminis(UserName,password) values(?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, admini.getUserName());
                preparedStatement.setString(2,admini.getPassword());

                preparedStatement.executeUpdate();      //执行语句

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                connectionClose();      //关闭连接
                Connection1.close(preparedStatement);
                return true;
            }
        }
        return false;
    }

    public void AddAllAdminis ()//把所有管理员信息导入数据库
    {
        if(tableJudgment("Adminis"))       //判断表是否存在
        {
            for (int i = 0; i < adminis.size(); i++)        //遍历作业列表
            {
                AddOneAdmini(adminis.get(i));             //插入作业数据
            }
        }
    }

    public void AllAdminisOut () //把所有管理员信息导出至adminis1
    {
        adminis1.clear();          //清空Arraylist中的内容
        if (tableJudgment("Adminis"))        //判断表是否存在
        {
            connectionOpen();     //连接数据库
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                //sql语句
                String sql = "select * from Adminis";
                preparedStatement = connection.prepareStatement(sql);

                resultSet = preparedStatement.executeQuery(); //执行语句并将结果返回ResultSet
                while (resultSet.next())//遍历
                {
                    String username = resultSet.getString("用户名");
                    String password = resultSet.getString("密码");

                    adminis1.add(new Admini(username, password));   //将每条考核信息都存入adminis1中
                }
                Connection1.close(resultSet);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                connectionClose();      //关闭连接
                Connection1.close(preparedStatement);
            }
        }
    }
    public ArrayList<Admini>  GetAllAdminis ()
    {
        return adminis1;
    }

}
