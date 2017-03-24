package com.haui.tuannv.qldv.util;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by tuanbg
 * <></>
 */
public class DataBindingUtils {
    @BindingAdapter({"bind:imageResource"})
    public static void loadImage(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter({"bind:imageDrawable"})
    public static void loadImageDrawable(ImageView view, int source) {
        Glide.with(view.getContext()).load(source).asBitmap().centerCrop()
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(view.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }


    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"},
            requireAll = false)
    public static void bindSpinnerData(Spinner spinner, int selectedPosition,
                                       final InverseBindingListener newTextAttrChanged) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (selectedPosition != -1) {
            spinner.setSelection(selectedPosition, true);
        }
    }


    @BindingAdapter({"bind:adapterViewPager"})
    public static void setViewPagerAdapter(ViewPager view, FragmentPagerAdapter adapter) {
        view.setAdapter(adapter);
    }


    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view,
                                        LayoutManageUtil.LayoutManagerFactory layout) {
        view.setLayoutManager(layout.create(view));
    }

    @BindingAdapter({"bind:colorSwipeLayout"})
    public static void setColorSwipeLayout(SwipeRefreshLayout view, int color) {
        view.setColorSchemeColors(color);
    }

    @BindingAdapter({"bind:refreshSwipeLayout"})
    public static void setColorSwipeLayout(SwipeRefreshLayout view, boolean isReFresh) {
        view.setRefreshing(isReFresh);
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue",
            event = "bind:selectedValueAttrChanged")
    public static int captureSelectedValue(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }

    @BindingAdapter({"bind:showPassword"})
    public static void setShowImage(EditText view, boolean isShow) {
        view.setTransformationMethod(isShow ? null : new PasswordTransformationMethod());
    }


    @BindingAdapter({"bind:bindImage", "bind:bindError"})
    public static void bindImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).asBitmap().error(error).placeholder(error)
                .centerCrop()
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(view.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }


    @BindingAdapter({"bind:bindAdapter"})
    public static void bindAdapterRecycler(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setNestedScrollingEnabled(false);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:setErrorEditText"})
    public static void setError(final EditText editText, final String msg) {
        if (TextUtils.isEmpty(editText.getText())) editText.setError(msg);
    }

    @BindingAdapter({"bind:setErrorEmail"})
    public static void setErrorEmail(final EditText editText, final String msg) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches()) {
            editText.setError(msg);
        }
    }

    @BindingAdapter(value =
            {"bind:imageUrl", "bind:placeholder", "bind:error"}, requireAll = false)
    public static void setImageUrl(
            ImageView view, String path, Drawable placeholder, Drawable error) {
        if (TextUtils.isEmpty(path)) return;
        Glide.with(view.getContext())
                .load(path)
                .placeholder(placeholder)
                .error(error)
                .into(view);
    }
}
