package 西二3;

import java.sql.*;

public class connection
{
    /**
     * ȡ�����ݿ������
     * @return һ�����ݿ������
     */
    private static String URL="jdbc:mysql://127.0.0.1:3306/yeruicao?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    //连接数据库的URL
    private static String USER="root";
    //用户名
    private static String PASSWORD="web.qq20001127";
    //密码

    public static java.sql.Connection getConnection()
    {
        java.sql.Connection conn = null;
        try
        {
            //初始化驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("连接数据库成功!!!");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(PreparedStatement pstmt){
        if(pstmt != null){						//避免出现空指针异常
            try{
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
    public void addtoday (Weather weather)
    {
        String code[] = {"110000","120000","130100","140100","150100",
                "210100","220100","230100","310000","320100","330100","340100","350100",
                "360100","370100","410100","420100","430100","440100","450100","460100",
                "500100","510100","520100","530100","540100","610100","620100","630100",
                "640100","650100","710000","810000","820000"};
        TranslationJson2 trans = new TranslationJson2();
        API api = new API();
        String sql = "insert into today(province,city,adcode,weather,temperature,winddirection,windpower,humidity,reporttime) values(?,?,?,?,?,?,?,?,?)";
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;
        for (int i=0;i<34;i++)
        {
            trans.translate2(api.result(code[i],"base"),weather) ;
            try{
                conn = connection.getConnection() ;
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                pstmt .setString(1,weather .getProvince() );
                pstmt .setString(2,weather .getCity() );
                pstmt .setInt(3,weather .getAdcode() );
                pstmt .setString(4,weather .getWeather() );
                pstmt .setInt(5,weather .getTemperature() );
                pstmt .setString(6,weather .getWinddirection() );
                pstmt .setString(7,weather .getWindpower() );
                pstmt .setInt(8,weather .getHumidity() );
                pstmt .setString(9,weather .getReporttime());
                pstmt.executeUpdate();
            }catch(SQLException e) {
                e.printStackTrace();
            }
            finally{
                connection .close(conn);
                connection .close(pstmt );
            }
        }
        }
        public void addprediction1 (Weather weather )
        {
            String code[] = {"110000","120000","130100","140100","150100",
                    "210100","220100","230100","310000","320100","330100","340100","350100",
                    "360100","370100","410100","420100","430100","440100","450100","460100",
                    "500100","510100","520100","530100","540100","610100","620100","630100",
                    "640100","650100","710000","810000","820000"};
            Connection conn = null;				//和数据库取得连接
            PreparedStatement pstmt = null;
            TranslateJson trans = new TranslateJson();
            API api = new API();
            String sql = "insert into prediction1(adcode,province,city,reporttime) values (?,?,?,?)";
            for (int i=0;i<34;i++)
            {
                trans.translate(api.result(code[i],"all"),weather ) ;
                try{
                    conn = connection.getConnection() ;
                    pstmt = (PreparedStatement) conn.prepareStatement(sql);
                    pstmt .setInt(1,weather .getAdcode() );
                    pstmt .setString(2,weather .getProvince() );
                    pstmt .setString(3,weather .getCity() );
                    pstmt .setString(4,weather .getReporttime() );
                    pstmt.executeUpdate();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
                finally{
                    connection .close(conn);
                    connection .close(pstmt);
                }
            }

        }
    public void addprediction2 (Weather weather )
    {
        String code[] = {"110000","120000","130100","140100","150100",
                "210100","220100","230100","310000","320100","330100","340100","350100",
                "360100","370100","410100","420100","430100","440100","450100","460100",
                "500100","510100","520100","530100","540100","610100","620100","630100",
                "640100","650100","710000","810000","820000"};
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;
        TranslateJson trans = new TranslateJson();
        API api = new API();
        String sql = "insert into prediction2(adcode,date,week,dayweather,nightweather,daytemp,nighttemp,daywind,nightwind,daypower,nightpower) values (?,?,?,?,?,?,?,?,?,?,?)";
        for (int i=0;i<34;i++)
        {
            trans.translate(api.result(code[i],"all"),weather ) ;
            for (int j=0;j<4;j++) {
                try {
                    conn = connection.getConnection() ;
                    pstmt = (PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setInt(1, weather.getAdcode());
                    pstmt.setString(2, weather.getDate(j));
                    pstmt.setInt(3, weather.getWeek(j));
                    pstmt.setString(4, weather.getDayweather(j));
                    pstmt.setString(5, weather.getNightweather(j));
                    pstmt.setInt(6, weather.getDaytem(j));
                    pstmt.setInt(7, weather.getNighttem(j));
                    pstmt.setString(8, weather.getDaywind(j));
                    pstmt.setString(9, weather.getNightwind(j));
                    pstmt.setString(10, weather.getDaypower(j));
                    pstmt.setString(11, weather.getNightpower(j));
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                finally {
                    connection.close(conn);
                    connection.close(pstmt);
                }
            }
        }
    }


        public void add_table1 ()throws Exception
        {
            connection con = new connection() ;
            Class.forName("com.mysql.jdbc.Driver");
            //一开始必须填一个已经存在的数据库
            String url = "jdbc:mysql://127.0.0.1:3306/yeruicao?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "web.qq20001127");
            Statement stat = conn.createStatement();
            //打开创建的数据库
            stat.close();
            conn.close();
            conn = DriverManager.getConnection(url, "root", "web.qq20001127");
            stat = conn.createStatement();
            //创建表test
            stat.executeUpdate("create table today(province varchar(100),city varchar(100),adcode int,weather varchar(100),temperature int,winddirection varchar(100),windpower varchar(100),humidity int,reporttime varchar(100))");
        }
        public void add_table2 ()throws Exception
        {
            connection con = new connection() ;
            Class.forName("com.mysql.jdbc.Driver");
            //一开始必须填一个已经存在的数据库
            String url = "jdbc:mysql://127.0.0.1:3306/yeruicao?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "web.qq20001127");
            Statement stat = conn.createStatement();
            //打开创建的数据库
            stat.close();
            conn.close();
            conn = DriverManager.getConnection(url, "root", "web.qq20001127");
            stat = conn.createStatement();
            //创建表test
            stat.executeUpdate("create table prediction1(adcode int,province varchar(50),city varchar(40),reporttime varchar(40))");
            stat .executeUpdate("create table prediction2(adcode int,date varchar(40),week int,dayweather varchar(40),nightweather varchar(40),daytemp int,nighttemp int,daywind varchar(40),nightwind varchar(40),daypower varchar(40),nightpower varchar(40))");
        }
    public static void main(String args[]) throws Exception
    {
        Weather weather = new Weather() ;
        connection con = new connection() ;
        //con.add_table1() ;
        //con .addtoday(weather );
        //con.add_table2() ;
        con.addprediction1(weather) ;
        con.addprediction2(weather);
    }
}







