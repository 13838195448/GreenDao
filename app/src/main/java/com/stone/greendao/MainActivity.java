package com.stone.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stone.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserDao mUserDao;
    private User mUser;

    private TextView tv_content;
    private Button btn_add, btn_del, btn_edit, btn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找控件
        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);
        btn_edit = findViewById(R.id.btn_edit);
        btn_query = findViewById(R.id.btn_query);
        tv_content = findViewById(R.id.tv_content);

        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();


        //增加
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser = new User(null, "杨洋");
                //                                mUser = new User((long) 1, "tom1");
                //                                mUser = new User((long) 2, "tom2");
                mUserDao.insert(mUser);
            }
        });

        //删除
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id=(long)5;
                mUserDao.deleteByKey(id);
//                mUserDao.update(mUser);
                System.out.println("执行了-----");
            }
        });

        //改

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id=(long)0;
                System.out.println("id===="+id);
                mUser = new User(id, "都是");
                mUserDao.update(mUser);
                System.out.println("执行了====");
            }
        });

        //查询

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = mUserDao.loadAll();
                String userName = "";
                for (int i = 0; i < users.size(); i++) {
                    userName += users.get(i).getName() + ",";
                }
                tv_content.setText(userName);
                System.out.println("执行了——++++");
            }
        });
    }
}
