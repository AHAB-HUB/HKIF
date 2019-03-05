package com.klexy.myprojecttestrun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/*public class GalleryFragment extends Fragment {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ImageView imageView;

    View view;
    private int[] myImage = {R.drawable.photo4,
            R.drawable.photo5,R.drawable.photo6,R.drawable.photo3};


    private RecyclerView recyclerView;



    private RecyclerView.LayoutManager layoutManager;

    private RecyclerAdapter adapter;

    int currIndex = 0;


    public GalleryFragment(){

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView = getActivity().findViewById(R.id.recycler_image);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(myImage);


    *//*    LinearLayout layout = getActivity().findViewById(R.id.imagePager);
        for(int i =0;i < myImage.length;i++){

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.getLocationInWindow(myImage);



            layout.addView(view);

        }
*//*
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_gallery,container,false);


    }
}*/
