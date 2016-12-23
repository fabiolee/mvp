package com.fabiolee.architecture.mvp.view.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fabiolee.architecture.mvp.R;

/**
 * @author fabiolee
 */
public class UserListViewHolder extends RecyclerView.ViewHolder {
    public TextView loginTextView;

    UserListViewHolder(View itemView) {
        super(itemView);
        loginTextView = (TextView) itemView.findViewById(R.id.tv_login);
    }
}
