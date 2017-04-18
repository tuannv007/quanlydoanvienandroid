package com.haui.tuannv.qldv.ui.monney.classes;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.Classes;
import com.haui.tuannv.qldv.databinding.ItemClassesBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 4/11/17.
 */

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ClassesViewHolder> {
    private List<Classes> mClassesList = new ArrayList<>();
    private LayoutInflater mInflater;
    private ClassesViewModel mViewModel;
    private ClassesFragment mFragment;

    public ClassesAdapter(ClassesViewModel viewModel, ClassesFragment fragment,
            List<Classes> classesList) {
        mClassesList = classesList;
        mViewModel = viewModel;
        mFragment = fragment;
    }

    @Override
    public ClassesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemClassesBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_classes, parent, false);
        binding.setViewmodel(mViewModel);
        binding.setFragment(mFragment);
        return new ClassesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ClassesViewHolder holder, int position) {
        Classes item = mClassesList.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mClassesList == null ? 0 : mClassesList.size();
    }

    public class ClassesViewHolder extends RecyclerView.ViewHolder {
        private ItemClassesBinding mBinding;

        public ClassesViewHolder(ItemClassesBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Classes item) {
            mBinding.setClasses(item);
            mBinding.executePendingBindings();
        }
    }
}
