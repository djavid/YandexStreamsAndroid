package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseRecyclerAdapter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationHistoryDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.HistoryFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryRecyclerAdapter extends BaseRecyclerAdapter<DonationHistoryDto,
        HistoryRecyclerAdapter.ViewHoler> {

    private final HistoryFragment.OnListFragmentInteractionListener mListener;

    public HistoryRecyclerAdapter(
            Context context, HistoryFragment.OnListFragmentInteractionListener mListener) {
        super(context);
        this.mListener = mListener;
    }

    @Override
    protected void bindSingleItem(ViewHoler viewHolder, DonationHistoryDto item) {
        viewHolder.history_stream_name.setText("Test");
        viewHolder.history_stream_amount.setText(item.getAmount());
        viewHolder.history_stream_date.setText(item.getDate().toString());
        GlideUtils.loadImageIntoView(context, viewHolder.history_stream_avatar,
                "https://www.yastream.win/api/images?id=90&type=logo", R.color.colorToolbarBackground);

        viewHolder.view.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onListFragmentInteraction(item);
            }
        });
    }

    @Override
    protected ViewHoler createVH(View view) {
        return new ViewHoler(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.recycler_item_history;
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.history_stream_avatar)
        ImageView history_stream_avatar;
        @BindView(R.id.history_stream_name)
        TextView history_stream_name;
        @BindView(R.id.history_stream_amount)
        TextView history_stream_amount;
        @BindView(R.id.history_stream_date)
        TextView history_stream_date;


        ViewHoler(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this.view);
        }
    }
}
