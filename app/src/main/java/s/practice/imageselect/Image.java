package s.practice.imageselect;

/**
 * Created by dagou on 2017/10/17.
 */

public class Image {
   public String path;
    public  String name;
    public int position;
    public  boolean isSelected = false;

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
