package com.haui.tuannv.qldv.ui.revenue;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.haui.tuannv.qldv.R;
import com.haui.tuannv.qldv.data.local.model.Payment;
import com.haui.tuannv.qldv.databinding.ItemRevenueBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 4/14/17.
 */

public class RevenueAdapter extends RecyclerView.Adapter<RevenueAdapter.RevenueViewHolder> {
    private List<Payment> mPaymentList = new ArrayList<>();
    private LayoutInflater mInflater;

    public RevenueAdapter(List<Payment> paymentList) {
        mPaymentList = paymentList;
    }

    @Override
    public RevenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemRevenueBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_revenue, parent, false);
        return new RevenueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RevenueViewHolder holder, int position) {
        Payment item = mPaymentList.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mPaymentList != null ? mPaymentList.size() : 0;
    }

    public class RevenueViewHolder extends RecyclerView.ViewHolder {
        private ItemRevenueBinding mBinding;

        public RevenueViewHolder(ItemRevenueBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Payment item) {
            mBinding.setPayment(item);
            mBinding.executePendingBindings();
        }
    }
}
