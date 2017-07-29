package net.azurewebsites.streambeta.yandexstreamsandroid.core.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tetawex on 29.07.2017.
 * Recycler adapter template v2
 */


public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected Context context;
    protected LayoutInflater inflater;
    private List<T> data;

    public BaseRecyclerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<T>();
        this.context = context;
    }

    protected abstract void bindSingleItem(VH viewHolder, T item);
    protected abstract VH createVH(View view);
    protected abstract int getLayoutId();

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(getLayoutId(), parent, false);
        return createVH(view);
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        bindSingleItem(viewHolder, data.get(position));
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void appendData(List<T> data) {
        this.data.addAll(data);
    }

    public void setDataWithNotify(List<T> data) {
        setData(data);
        notifyDataSetChanged();
    }

    public void appendDataWithNotify(List<T> data) {
        appendData(data);
        notifyDataSetChanged();
    }
    public void clear(){
        data.clear();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
