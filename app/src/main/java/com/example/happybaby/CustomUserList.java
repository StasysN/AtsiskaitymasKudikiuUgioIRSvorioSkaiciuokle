package com.example.happybaby;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomUserList extends BaseAdapter {

    private Activity context;
    private ArrayList<User> users;
    private DBHelper dbHelper;

    private PopupWindow popup;

    public CustomUserList() {
    }

    public CustomUserList(Activity context, ArrayList<User> users, DBHelper dbHelper) {
        this.context = context;
        this.users = users;
        this.dbHelper = dbHelper;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static class ViewHolder {

        TextView textViewUsername, textViewPassword;
        Button editUser, deletUser;

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.user_item,null, true);

            holder.textViewUsername = row.findViewById(R.id.username1);
            holder.textViewPassword = row.findViewById(R.id.password1);
            holder.deletUser = row.findViewById(R.id.delet_button);

            row.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewUsername.setText(users.get(position).getUsername());
        holder.textViewPassword.setText(users.get(position).getPassword());

        final int positionPopup = position;

        holder.deletUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete(users.get(positionPopup));
                users = (ArrayList<User>) dbHelper.readAll();
                notifyDataSetChanged();
            }
        });
        return row;
    }

    private void editPopup(final int positionPopup){
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.edit_popup_admin, (ViewGroup) context.findViewById(R.id.admin));

        popup = new PopupWindow(layout, 600, 670, true);
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText username = layout.findViewById(R.id.admin_edit_username);
        final EditText password = layout.findViewById(R.id.admin_edit_password);
        Button saveUser = layout.findViewById(R.id.save_user);
        username.setText(users.get(positionPopup).getUsername());
        password.setText(users.get(positionPopup).getPassword());

        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getUsername = username.getText().toString();
                String getPassword = password.getText().toString();
                User user = users.get(positionPopup);
                user.setUsername(getUsername);
                user.setPassword(getPassword);

                dbHelper.update(user);
                users =(ArrayList<User>) dbHelper.readAll();
                notifyDataSetChanged();
                popup.dismiss();
            }
        });
    }

}
