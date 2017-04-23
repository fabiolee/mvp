package com.fabiolee.architecture.mvp.ui.userlist;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fabiolee.architecture.mvp.BR;
import com.fabiolee.architecture.mvp.R;
import com.fabiolee.architecture.mvp.data.model.User;
import com.fabiolee.architecture.mvp.databinding.ItemUserBinding;
import com.fabiolee.repository.Repository;

import java.util.List;

/**
 * @author fabiolee
 */
public class UserListAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private List<User> userList;

    public UserListAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        User user = userList.get(position);
        holder.getBinding().setVariable(BR.user, user);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Repository.with(view.getContext()).loadImage(url, view);
    }
}
