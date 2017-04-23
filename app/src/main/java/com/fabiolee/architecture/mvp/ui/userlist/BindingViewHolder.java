package com.fabiolee.architecture.mvp.ui.userlist;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @author fabiolee
 */
public class BindingViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    BindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
