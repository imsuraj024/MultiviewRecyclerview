package com.suraj.fragmentrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.suraj.fragmentrecyclerview.databinding.ButtonTypeBinding;
import com.suraj.fragmentrecyclerview.databinding.ImageTypeBinding;
import com.suraj.fragmentrecyclerview.databinding.ViewPagerTypeBinding;
import com.suraj.fragmentrecyclerview.databinding.ViewPagerTypeFragmentsBinding;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<Data> dataSet;
    FragmentManager fragmentManager;

    public RecyclerAdapter(ArrayList<Data> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }

    public static class ButtonTypeViewHolder extends RecyclerView.ViewHolder {

        ButtonTypeBinding buttonTypeBinding;

        public ButtonTypeViewHolder(@NonNull ButtonTypeBinding buttonTypeBinding) {
            super(buttonTypeBinding.getRoot());
            this.buttonTypeBinding = buttonTypeBinding;
        }
    }

    public static class ImageTypeViewHolder  extends RecyclerView.ViewHolder {

        ImageTypeBinding imageTypeBinding;

        public ImageTypeViewHolder(@NonNull ImageTypeBinding imageTypeBinding) {
            super(imageTypeBinding.getRoot());
            this.imageTypeBinding = imageTypeBinding;
        }
    }

    public static class ViewPagerTypeViewHolder extends RecyclerView.ViewHolder {

        ViewPagerTypeBinding viewPagerTypeBinding;

        public ViewPagerTypeViewHolder(@NonNull ViewPagerTypeBinding viewPagerTypeBinding) {
            super(viewPagerTypeBinding.getRoot());
            this.viewPagerTypeBinding = viewPagerTypeBinding;
        }
    }

    public static class ViewPagerFragTypeViewHolder extends RecyclerView.ViewHolder {

        ViewPagerTypeFragmentsBinding viewPagerTypeFragmentsBinding;

        public ViewPagerFragTypeViewHolder(@NonNull ViewPagerTypeFragmentsBinding viewPagerTypeFragmentsBinding) {
            super(viewPagerTypeFragmentsBinding.getRoot());
            this.viewPagerTypeFragmentsBinding = viewPagerTypeFragmentsBinding;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case Data.VIEW_PAGER:
                ViewPagerTypeFragmentsBinding viewPagerTypeFragmentsBinding = ViewPagerTypeFragmentsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new ViewPagerFragTypeViewHolder(viewPagerTypeFragmentsBinding);

            case Data.IMAGE_TYPE:
                ImageTypeBinding imageTypeBinding = ImageTypeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new ImageTypeViewHolder(imageTypeBinding);

            case Data.AUDIO_TYPE:
                ButtonTypeBinding buttonTypeBinding = ButtonTypeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new ButtonTypeViewHolder(buttonTypeBinding);

            case Data.SLIDER_TYPE:
                ViewPagerTypeBinding viewPagerTypeBinding = ViewPagerTypeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new ViewPagerTypeViewHolder(viewPagerTypeBinding);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data object = dataSet.get(position);
        if (object != null){
            switch (object.type){
                case Data.VIEW_PAGER:
                    ((ViewPagerFragTypeViewHolder) holder).viewPagerTypeFragmentsBinding.type.setText(object.text);
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(((FragmentActivity)context).getSupportFragmentManager());
                    viewPagerAdapter.add(new FirstFragment(), "Page 1");
                    viewPagerAdapter.add(new SecondFragment(), "Page 2");
                    ((ViewPagerFragTypeViewHolder) holder).viewPagerTypeFragmentsBinding.vpSlider.setAdapter(viewPagerAdapter);
                    break;
                case Data.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).imageTypeBinding.type.setText(object.text);
                    ((ImageTypeViewHolder) holder).imageTypeBinding.img.setImageResource(object.data);
                    break;
                case Data.AUDIO_TYPE:
                    ((ButtonTypeViewHolder) holder).buttonTypeBinding.type.setText(object.text);
                    ((ButtonTypeViewHolder) holder).buttonTypeBinding.btn.setOnClickListener(view -> Toast.makeText(context, "You clicked!!!", Toast.LENGTH_SHORT).show());
                    break;
                case Data.SLIDER_TYPE:
                    ((ViewPagerTypeViewHolder) holder).viewPagerTypeBinding.type.setText(object.text);

                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type){
            case 0:
                return Data.VIEW_PAGER;
            case 1:
                return Data.IMAGE_TYPE;
            case 2:
                return Data.AUDIO_TYPE;
            case 3:
                return Data.SLIDER_TYPE;
            default:
                return -1;
        }
    }
}
