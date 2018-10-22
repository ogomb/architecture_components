package com.example.ogomb.androidarchitecturecomponents.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ogomb.androidarchitecturecomponents.R;
import com.example.ogomb.androidarchitecturecomponents.UserProfileViewModel;
import com.example.ogomb.androidarchitecturecomponents.database.entity.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class UserProfileFragment extends Fragment {
    // FOR DATA
    public static final String UID_KEY = "uid";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserProfileViewModel viewModel;

    // FOR DESIGN
    @BindView(R.id.fragment_user_profile_image)
    ImageView imageView;
    @BindView(R.id.fragment_user_profile_username)
    TextView username;
    @BindView(R.id.fragment_user_profile_company)
    TextView company;
    @BindView(R.id.fragment_user_profile_website)
    TextView website;

    public UserProfileFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        String userLogin = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel.class);
        viewModel.init(userLogin);
        viewModel.getUser().observe(this, user -> updateUI(user));
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable User user){
        if (user != null){
            Glide.with(this).load(user.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(imageView);
            this.username.setText(user.getName());
            this.company.setText(user.getCompany());
            this.website.setText(user.getBlog());
        }
    }
}
