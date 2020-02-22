package com.dao;


import ch.qos.logback.classic.db.names.TableName;
import com.pojo.Exam;
import com.pojo.Homework;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
@Repository
public class HomeworkDao {  //和数据库搞在一起

    Connection connection;
    ArrayList<Homework> homeworks;
    ArrayList<Homework> homeworks1;//为了导出某考核的所有作业信息所准备

    public HomeworkDao(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public HomeworkDao() {
        homeworks1 = new ArrayList<Homework>();
    }

    public void CreatHomeworkTable(int id)//新建作业表
    {
        String TableName = "Exam"+id+"_homework";
        if (!Judgement(TableName))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try {
                String sql = "create table " + TableName + "(ExamId int ,Id int,StudyNumber long,Name varchar(50),Target int,FilePath varchar(200),FileName varchar(200),Time timestamp)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally {
                Connection1.close(preparedStatement);
                connectionClose();
            }
        }

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

    public boolean Judgement(String TableName)//判断该考核所拥有的表是否存在
    {
        connectionOpen();
        ResultSet resultSet = null;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables("yeruicao", null, TableName, null);
            if (resultSet.next())              //如果
            {
                connectionClose();
                Connection1.close(resultSet);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connection1.close(resultSet);
        connectionClose();
        return false;
    }

    public boolean InsertOneHomework(Homework homework,Exam exam) //插入一条作业数据
    {
        int t=0;
        String TableName = "Exam"+exam.getId()+"_homework";
        if (Judgement(TableName))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try {
                if (!exam.Overtime(homework))
                    t=0;
                else {
                    t=1;
                    String sql = "insert into " + TableName + "(ExamId,Id,StudyNumber,Name,Target,FilePath,FileName,Time) values(?,?,?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, exam.getId());
                    preparedStatement.setInt(2, homework.getId());
                    preparedStatement.setLong(3, homework.getStudyNumber());
                    preparedStatement.setString(4, homework.getName());
                    preparedStatement.setInt(5, homework.getTarget());
                    preparedStatement.setString(6, homework.getFilePath());
                    preparedStatement.setString(7, homework.getFileName());
                    preparedStatement.setTimestamp(8, new java.sql.Timestamp(homework.getTime().getTime()));
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally
            {
                Connection1.close(preparedStatement);
                connectionClose();
            }
        }
        else {
            t=0;
        }
        if(t==0)
            return false;
        else
            return true;
    }
    public void AddAllHomeworkToExam (Exam exam) //把所有作业数据导入相应的考核数据库表
    {
        String TableName = "Exam"+exam.getId()+"_homework";
        if(Judgement(TableName))
        {
            for (int i = 0; i < homeworks.size(); i++)
            {
                InsertOneHomework(homeworks.get(i),exam);
            }
        }
    }

    public void DeleteOneHomework(int exam_id , int homework_id)//删除特定的作业数据
    {
        String TableName = "Exam"+exam_id+"_homework";
        if(Judgement(TableName))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "delete from " + TableName + " where Id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,homework_id);

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
            }
        }
    }


    public void AllHomeworkOut (int Exam_id) //导出某一考核所有作业数据至homeworks1
    {
        homeworks1.clear();        //清空Arraylist homework1中的内容
        String TableName = "Exam"+Exam_id+"_homework";
        if(Judgement(TableName))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                String sql = "select * from " + TableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    int Flag = 0;
                    int examid = resultSet.getInt("ExamId");
                    int id = resultSet.getInt("Id");
                    long study_number = resultSet.getLong("StudyNumber");
                    String name = resultSet.getString("Name");
                    int target = resultSet.getInt("Target");
                    String filepath = resultSet.getString("FilePath");
                    String filename=resultSet.getString("FileName");
                    java.util.Date time = new Date(resultSet.getTimestamp("Time").getTime());

                    homeworks1.add(new Homework(id,name,time,target,study_number,filename,examid,filepath));       //将每条作业信息都存入homework1中
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
    }

    public Homework GetOneHomewor (int id)//在homeworks1中获取某一特定的作业信息，通过作业id
    {
        Homework homework=new Homework();
        for(int i=0;i<homeworks1.size();i++)
        {
            if(id==homeworks1.get(i).getId())
                homework=homeworks1.get(i);
        }
        return homework;
    }

    public int Getnumber (int exam_id)
    {
        int a=0;
        Homework homework=new Homework();
        for(int i=0;i<homeworks1.size();i++)
        {
            homework=homeworks1.get(i);
            if (exam_id==homework.getExamId())
            {
                a++;
            }
        }
        return a;
    }

    public ArrayList<Homework> GetAllHomeworks ()
    {
        return homeworks1;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public ArrayList<Homework> getHomeworks1() {
        return homeworks1;
    }

    public void setHomeworks1(ArrayList<Homework> homeworks1) {
        this.homeworks1 = homeworks1;
    }
}