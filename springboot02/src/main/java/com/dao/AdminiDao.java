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

    public boolean Cannotlogin(Admini admini){
        AllAdminisOut();
        int tag=0;
        for (int i =0 ;i<GetAllAdminis().size() ;i++)
        {
            if (admini.equals(GetAllAdminis().get(i)))
            {
                tag=1;
            }
        }
        if (tag==1)
        {
            return true;
        }
        else {
            return false;
        }
    }


    public AdminiDao (){
        adminis1 = new ArrayList<Admini>();
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
        connectionOpen();
        ResultSet resultSet=null;
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables("yeruicao", null, tableName, null);
            if (resultSet.next())
            {
                connectionClose();
                Connection1.close(resultSet);
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        Connection1.close(resultSet);
        connectionClose();
        return false;
    }

    public void createAdminiTable() //创建管理员表
    {
        if(!tableJudgment("Adminis"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "create table Adminis(UserName varchar(50),password varchar(50))";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                Connection1.close(preparedStatement);
                connectionClose();
            }
        }
    }

    public boolean AddOneAdmini (Admini admini)  //插入一条管理员信息
    {
        if(tableJudgment("Adminis"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "insert into Adminis(UserName,password) values(?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, admini.getUserName());
                preparedStatement.setString(2,admini.getPassword());

                preparedStatement.executeUpdate();

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                connectionClose();
                Connection1.close(preparedStatement);
                return true;
            }
        }
        return false;
    }

    public void AddAllAdminis ()//把所有管理员信息导入数据库
    {
        if(tableJudgment("Adminis"))
        {
            for (int i = 0; i < adminis.size(); i++)
            {
                AddOneAdmini(adminis.get(i));
            }
        }
    }

    public boolean AllAdminisOut () //把所有管理员信息导出至adminis1
    {
        adminis1.clear();          //清空Arraylist中的内容
        if (tableJudgment("Adminis"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                String sql = "select * from Adminis";
                preparedStatement = connection.prepareStatement(sql);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())//遍历
                {
                    String username = resultSet.getString("UserName");
                    String password = resultSet.getString("password");

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
                connectionClose();
                Connection1.close(preparedStatement);
            }
        }
        return false;
    }
    public ArrayList<Admini>  GetAllAdminis ()
    {
        return adminis1;
    }

}
