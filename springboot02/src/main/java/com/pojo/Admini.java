package com.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admini {  //管理员账户
    private String UserName;
    private String Password;

    public boolean Judge (Admini admini)
    {
        if (UserName==admini.getUserName()&&Password==admini.getPassword())
        {
            return true;
        }
        else {
            return false;
        }
    }
}


