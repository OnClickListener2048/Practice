package s.practice.newbag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import s.practice.R;
import s.practice.imageselect.Image;

/**
 *
 * @author dagou
 * @date 2017/9/29
 */

public class NormalAdpater extends RecyclerView.Adapter<NormalAdpater.VH> {
    private static final String TAG = "NormalAdpater";
    public static final String EXTRA_ALL_DATA = "all_data";
    public static final String EXTRA_CURRENT_POSITION = "current_item_position";
    public static final String EXTRA_BUNDLE = "extra_bundle";

    private ArrayList<Image> arrayList;
    private RequestManager requestManager;
    private int mImageResize;
    private OnImageSelectListener onImageSelectListener;
    private OnImageClickListener onImageClickListener;

    public NormalAdpater(ArrayList<Image> images, int imageResize) {
        this.arrayList = images;
        this.mImageResize = imageResize;
    }

    @Override
    public NormalAdpater.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder:  public NormalAdpater.VH onCreateViewHolder(ViewGroup parent, int viewType) {");
        requestManager = Glide.with(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new VH(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(final NormalAdpater.VH viewHolder, final int position) {
        final Image image = arrayList.get(position);
        image.position = position;
        if (image.isSelected) {
            viewHolder.radioButton.setText("已选中");
        } else {
            viewHolder.radioButton.setText("未选中");
        }
        requestManager.load(image.path).asBitmap().placeholder(R.mipmap.ic_launcher).override(mImageResize, mImageResize).thumbnail(0.1f).into(viewHolder.iv_image_item);
        viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.isSelected()) {
                    view.setSelected(false);
                    image.isSelected = false;
                    viewHolder.radioButton.setText("未选中");
                    if (onImageSelectListener != null) {
                        onImageSelectListener.onImageSelect(image);
                    }
                    Log.d(TAG, "onClick:image.isSelected"+image.isSelected);
                } else {
                    view.setSelected(true);
                    image.isSelected = true;
                    viewHolder.radioButton.setText("已选中");
                    if (onImageSelectListener != null) {
                        onImageSelectListener.onImageSelect(image);
                    }
                    Log.d(TAG, "onClick: image.isSelected"+image.isSelected);
                }
            }
        });

        viewHolder.iv_image_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClickListener.onImageClick(arrayList, image.position);
            }
        });

    }

    public void refresh(ArrayList<Image> allItemList) {
        arrayList = allItemList;
        notifyDataSetChanged();
    }

    public interface OnImageClickListener {
        void onImageClick(ArrayList<Image> allItemList, int position);
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }


    public interface OnImageSelectListener {
        void onImageSelect(Image image);
    }

    public void setOnImageSelectListener(OnImageSelectListener onImageSelectListener) {
        this.onImageSelectListener = onImageSelectListener;
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+arrayList.size());
        return arrayList.size();
    }

    public static class VH extends RecyclerView.ViewHolder {


        private final ImageView iv_image_item;
        private final TextView radioButton;

        public VH(View itemView) {
            super(itemView);
            iv_image_item = itemView.findViewById(R.id.iv_image_item);
            radioButton = itemView.findViewById(R.id.rb);
        }
    }
}
