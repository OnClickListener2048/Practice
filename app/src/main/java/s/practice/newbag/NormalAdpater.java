package s.practice.newbag;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import s.practice.R;
import s.practice.imageselect.Image;
import s.practice.imageselect.ResizeImageView;
import s.practice.imageselect.model;

/**
 * Created by dagou on 2017/9/29.
 */

public class NormalAdpater extends RecyclerView.Adapter<NormalAdpater.VH> {
    private static final String TAG = "NormalAdpater";

    private List<model.ResultsBean> arrayList;
    private RequestManager requestManager;
    private int mImageResize;

    public NormalAdpater(List<model.ResultsBean> objectModels, int imageResize) {
        this.arrayList = objectModels;
        this.mImageResize = imageResize;
    }

    @Override
    public NormalAdpater.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        requestManager = Glide.with(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(NormalAdpater.VH viewHolder, int position) {
        model.ResultsBean resultsBean = arrayList.get(position);
        final Image image = new Image();
        image.name = resultsBean.get_id();
        image.path = resultsBean.getUrl();
        requestManager.load(resultsBean.getUrl()).asBitmap().placeholder(R.mipmap.ic_launcher).override(mImageResize, mImageResize).thumbnail(0.1f).into(viewHolder.iv_image_item);
        viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                    image.isSelected = true;
                } else {
                    image.isSelected = false;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class VH extends RecyclerView.ViewHolder {


        private final ImageView iv_image_item;
        private final RadioButton radioButton;

        public VH(View itemView) {
            super(itemView);
            iv_image_item = itemView.findViewById(R.id.iv_image_item);
            radioButton = itemView.findViewById(R.id.rb);
        }
    }
}
