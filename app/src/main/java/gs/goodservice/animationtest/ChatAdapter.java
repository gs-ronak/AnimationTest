package gs.goodservice.animationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ronakmundra on 19/07/15.
 */
public class ChatAdapter extends BaseAdapter {

    Context context;
    List<String> values;

    private boolean shouldAnimate = false;
    Animation animation;

    ChatAdapter(Context context, List<String> values){

        this.context = context;
        this.values = values;
        animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = convertView;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.simple_list_item, null);
        }

        TextView tv = (TextView)v.findViewById(R.id.text1);
        tv.setText(values.get(position));

        //animate only the last view when getView is called through notifyDataSetChanged()
        //getView() can be called from variety of reasons but we only want to show animation when a item is added( notifyDataSetChanged)
        if(position == getCount() -1 && shouldAnimate) {
            v.startAnimation(animation);
            shouldAnimate = false;
        }
        return v;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        shouldAnimate = true; //it is called when an item is added to listview and we want animation on last item when item is added.
    }

}
