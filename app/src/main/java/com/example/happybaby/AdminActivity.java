package com.example.happybaby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private ArrayList<User> users;
    PopupWindow popup;
    private DBHelper dbHelper;
    Button addUser;
    ListView listView;

    CustomUserList customUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dbHelper = new DBHelper(getApplicationContext());
        listView = findViewById(R.id.list_user);
        addUser = findViewById(R.id.create_new_user);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPopup();
            }
        });
        users = (ArrayList<User>) dbHelper.readAll();
        customUserList = new CustomUserList(AdminActivity.this, users, dbHelper);
        listView.setAdapter(customUserList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdminActivity.this, "You selected: "
                                + users.get(position).getUsername() + " as user.",
                        Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void addPopup() {
        LayoutInflater layoutInflater = AdminActivity.this.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.edit_popup_admin,
                (ViewGroup) AdminActivity.this.findViewById(R.id.popup_admin));
        popup = new PopupWindow(layout, 700, 770, true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText username = layout.findViewById(R.id.admin_edit_username);
        final EditText password = layout.findViewById(R.id.admin_edit_password);
        Button save = layout.findViewById(R.id.save_user);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getusername = username.getText().toString();
                String getpassword = password.getText().toString();
                User user = new User (getusername, getpassword);
                dbHelper.create(user);

                if (customUserList == null) {
                    customUserList = new CustomUserList((Activity) getApplicationContext(),
                            users, dbHelper);
                    listView.setAdapter(customUserList);
                }
                ArrayList<User> users1 = (ArrayList<User>) dbHelper.readAll();
                customUserList.setUsers(users1);

                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                popup.dismiss();
            }
        });
    }
}