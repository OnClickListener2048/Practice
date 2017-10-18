package s.practice.imageselect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import s.practice.R;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewImageFragment extends Fragment {

    private static final String FRAG_IMAGE = "fragment_image";
    @InjectView(R.id.image_view)
    ImageViewTouch imageView;
    @InjectView(R.id.rb_preview_viewpager)
    RadioButton rbPreviewViewpager;
    @InjectView(R.id.tv_preview_viewpager_delete)
    TextView tvPreviewViewpagerDelete;


    public static PreviewImageFragment newInstance(Image image) {
        PreviewImageFragment previewImageFragment = new PreviewImageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAG_IMAGE, image);
        previewImageFragment.setArguments(bundle);
        return previewImageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview_item, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Image imageItem =  getArguments().getParcelable(FRAG_IMAGE);


        ImageViewTouch image = (ImageViewTouch) view.findViewById(R.id.image_view);
        image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

        Glide.with(getActivity()).load(imageItem.path).asBitmap().placeholder(R.mipmap.ic_launcher).into(image);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.image_view, R.id.rb_preview_viewpager, R.id.tv_preview_viewpager_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_view:
                break;
            case R.id.rb_preview_viewpager:
                break;
            case R.id.tv_preview_viewpager_delete:
                break;
        }
    }

    public void resetView() {
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view)).resetMatrix();
        }
    }
}
