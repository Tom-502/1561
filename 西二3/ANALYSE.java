package 西二3;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class ANALYSE {
    public static void main(String[] args) {
        //待解析的json字符串
        String jsonString = "{'name':'卢本伟','age':24,'Hero':{'name':'Fizz','Position':'Mid','charactor':'killer'},'nickNames':['五五开','芦苇','white'],'Honors':[{'year':2011,'name':'TGA总决赛冠军'},{'year':2013,'name':'S3全球总决赛中国区冠军'},{'year':2013,'name':'S3全球总决赛亚军'}]}";

        try {
            //因为json字符串是大括号包围，所以用JSONObject解析
            JSONObject json = new JSONObject(jsonString);

            /*
             * 普通元素，根据类型直接获取
             */
            String name = json.getString("name");
            int age = json.getInt("age");
            System.out.println("姓名：" + name);
            System.out.println("年龄：" + age);
            System.out.println("————————————————————————————————");

            /*
             * 属性大括号包括，先获取JSONObject对象
             */
            JSONObject hero = json.getJSONObject("Hero");
            String hero_name = hero.getString("name");
            String hero_position = hero.getString("Position");
            String hero_charactor = hero.getString("charactor");
            System.out.println("擅长英雄：");
            System.out.println("英雄名：" + hero_name);
            System.out.println("位置：" + hero_position);
            System.out.println("英雄定位：" + hero_charactor);
            System.out.println("————————————————————————————————");
            /*
             * 属性被中括号包括，获取JSONArray对象,遍历即可
             */
            System.out.println("外号：");
            JSONArray nickNames = json.getJSONArray("nickNames");
            for (Object nickName : nickNames) {
                System.out.println(nickName);
            }
            System.out.println("————————————————————————————————");
            /*
             * 属性中既有中括号包括，又嵌套了大括号，一层层获取即可
             */
            JSONArray Honors = json.getJSONArray("Honors");
            System.out.println("所获荣誉：");
            for (int i = 0; i < Honors.length(); i++) {
                JSONObject honor = Honors.getJSONObject(i);
                int honor_year = honor.getInt("year");
                String honor_name = honor.getString("name");
                System.out.println(honor_year + " : " + honor_name);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}