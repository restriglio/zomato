package com.androcid.zomato.view.fragment.profile;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androcid.zomato.R;
import com.androcid.zomato.model.DinelineItem;
import com.androcid.zomato.retro.DinelineResponse;
import com.androcid.zomato.retro.RetroInterface;
import com.androcid.zomato.util.Constant;
import com.androcid.zomato.view.adapter.profile.ProfileDinelineAdapter;
import com.androcid.zomato.view.appbarlayout.SmoothAppBarLayout;
import com.androcid.zomato.view.appbarlayout.base.ObservableFragment;
import com.androcid.zomato.view.appbarlayout.base.Utils;
import com.androcid.zomato.view.recyclerview.SimpleRecyclerViewAdapter;
import com.androcid.zomato.view.recyclerview.holder.HeaderHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Androcid on 30-12-2016.
 */

public class ProfileDinelineFragment extends Fragment implements ObservableFragment {

    protected static final String ARG_HEADER_LAYOUT = "ARG_HEADER_LAYOUT";

    public static Fragment newInstance(String user_id) {
        return newInstance(R.layout.item_header_view_pager_parallax_spacing, user_id);
    }

    public static Fragment newInstance(@LayoutRes int headerLayout, String user_id) {
        ProfileDinelineFragment fragment = new ProfileDinelineFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_HEADER_LAYOUT, headerLayout);
        bundle.putString(Constant.USER_ID, user_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    String user_id;
    List<DinelineItem> dinelineItems;
    ProfileDinelineAdapter profileDinelineAdapter;
    RecyclerView recyclerView;

    private int mHeaderLayout;

    @Override
    public View getScrollTarget() {
        return recyclerView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_dineline, container, false);
        mHeaderLayout = getArguments().getInt(ARG_HEADER_LAYOUT);
        user_id = getArguments().getString(Constant.USER_ID);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean onOffsetChanged(SmoothAppBarLayout smoothAppBarLayout, View target, int verticalOffset) {
        return Utils.syncOffset(smoothAppBarLayout, target, verticalOffset, getScrollTarget());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dinelineItems = new ArrayList<>();
        profileDinelineAdapter = new ProfileDinelineAdapter(getActivity(), dinelineItems);
        profileDinelineAdapter.setClickListener(new ProfileDinelineAdapter.ClickListener() {
            @Override
            public void onItemClickListener(View v, int pos) {

            }
        });

        RecyclerView.Adapter adapter = new SimpleRecyclerViewAdapter(profileDinelineAdapter) {
            @Override
            public RecyclerView.ViewHolder onCreateFooterViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public RecyclerView.ViewHolder onCreateHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                return new HeaderHolder(layoutInflater, viewGroup, mHeaderLayout);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        getUserReviews();

    }

    private void getUserReviews() {

        RetroInterface.getZomatoRestApi().getUserDineline(
                user_id,
                new Callback<DinelineResponse>() {
                    @Override
                    public void success(DinelineResponse dinelineResponse, Response response) {

                        if (dinelineResponse != null) {
                            dinelineItems = dinelineResponse.getItems();
                            profileDinelineAdapter.refresh(dinelineItems);
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );


    }

}
