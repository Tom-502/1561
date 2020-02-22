package com.dao;

import com.pojo.Exam;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class ExamDao {//考核和数据库关联

    ArrayList <Exam> exams;
    Connection connection;
    ArrayList <Exam> exams1;

    public ExamDao() {
        exams1 = new ArrayList<Exam>();
    } //无参构造
    public ExamDao(ArrayList<Exam> exams) {
        this.exams = exams;
    }//有参构造

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Exam> getExams1() {
        return exams1;
    }

    public void setExams1(ArrayList<Exam> exams1) {
        this.exams1 = exams1;
    }

    public void connectionClose()
    {
        Connection1.close(connection);
    } //数据库连接关闭

    public Connection connectionOpen() //数据库连接打开
    {
        connection=Connection1.getConnection();
        return connection;
    }

    public boolean Judgement(String TableName) //判断考核表是否存在
    {
        connectionOpen();
        ResultSet resultSet=null;
        try
        {
            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getTables("yeruicao", null, TableName, null);
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

    public void createExamTable() //新建考核表
    {
        if(!Judgement("Exams"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "create table Exams(Id int,ExamName varchar(50),ExamContent longtext,Deadline timestamp)";
                //执行语句
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

    public boolean AddOneExam (Exam exam) //加入一条考核信息至考核表中
    {
        if(Judgement("Exams"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "insert into Exams(Id,ExamName,ExamContent,Deadline) values(?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, exam.getId());
                preparedStatement.setString(2, exam.getExamName());
                preparedStatement.setString(3, exam.getExamContent());
                preparedStatement.setTimestamp(4, new java.sql.Timestamp(exam.getDeadline().getTime()));

                preparedStatement.executeUpdate();

                HomeworkDao homeworkDao = new HomeworkDao();           //插入一条考核信息后，创建一个该考核的作业表
                homeworkDao.CreatHomeworkTable(exam.getId());
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

    public boolean UpdateOneExam  (Exam exam) //修改某条特定考核信息
    {
        if(Judgement("Exams"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "update Exams set ExamName=?,ExamContent=?,Deadline=? where Id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, exam.getExamName());
                preparedStatement.setString(2,exam.getExamContent());
                preparedStatement.setTimestamp(3, new java.sql.Timestamp(exam.getDeadline().getTime()));
                preparedStatement.setInt(4,exam.getId());
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

    public void DeleteOneExam (int id) //删除某条特定的考核信息通过考核Id
    {
        if(Judgement("Exams"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            try
            {
                String sql = "delete from Exams where Id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();

                sql = "drop table "+"Exam"+id+"-homework";
                preparedStatement = connection.prepareStatement(sql);
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

    public void AddAllExams () //把所有考核导入考核表
    {
        if(Judgement("Exams"))
        {
            for (int i = 0; i < exams.size(); i++)
            {
                AddOneExam(exams.get(i));
            }
        }
    }

    public ArrayList<Exam> AllExamsOut () //把所有考核数据导入exam1中
    {
        exams1.clear();
        if(Judgement("Exams"))
        {
            connectionOpen();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                String sql = "select * from Exams";
                preparedStatement = connection.prepareStatement(sql);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("Id");
                    String assName = resultSet.getString("ExamName");
                    String content = resultSet.getString("ExamContent");
                    java.util.Date date = new Date(resultSet.getTimestamp("Deadline").getTime());

                    exams1.add(new Exam(id, assName, content, date));   //将每条考核信息都存入exam1中
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
        return exams1;
    }

    public Exam GetOneExam(int exam_id) //把所有考核数据导入exam1中后通过Id查找考核
    {
        Exam exam=new Exam();
        for(int i=0;i<exams1.size();i++)
        {
            if(exam_id==exams1.get(i).getId())
                exam=exams1.get(i);
        }
        return exam;
    }

    public ArrayList<Exam> GetAllExams ()
    {
        return exams1;
    }
}
