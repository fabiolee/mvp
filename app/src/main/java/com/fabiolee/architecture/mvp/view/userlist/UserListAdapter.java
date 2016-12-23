package com.fabiolee.architecture.mvp.view.userlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabiolee.architecture.mvp.R;
import com.fabiolee.architecture.mvp.model.bean.UserBean;

import java.util.List;

/**
 * @author fabiolee
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    private List<UserBean> userList;

    public UserListAdapter(List<UserBean> userList) {
        this.userList = userList;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_list_item, parent, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {
        UserBean user = userList.get(position);
        holder.loginTextView.setText(user.login);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<UserBean> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
}
