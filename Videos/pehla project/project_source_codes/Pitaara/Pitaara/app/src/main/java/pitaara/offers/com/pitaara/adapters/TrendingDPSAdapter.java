package pitaara.offers.com.pitaara.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;
import pitaara.offers.com.pitaara.R;
import pitaara.offers.com.pitaara.ui.widget.twowayview.SpannableGridLayoutManager;
import pitaara.offers.com.pitaara.ui.widget.twowayview.StaggeredGridLayoutManager;
import pitaara.offers.com.pitaara.ui.widget.twowayview.TwoWayLayoutManager;
import pitaara.offers.com.pitaara.ui.widget.twowayview.TwoWayView;

/**
 * Created by piro on 9/1/16.
 */
public class TrendingDPSAdapter extends RecyclerView.Adapter<TrendingDPSAdapter.SimpleViewHolder1> {
    private static final int COUNT = 100;

    private final Context mContext;
    private final TwoWayView mRecyclerView;
    private final List<Integer> mItems;
    private final int mLayoutId;
    private int mCurrentItemId = 0;


    ImageView imageView;

    int count=0;
    int i;

    public static class SimpleViewHolder1 extends RecyclerView.ViewHolder {
        public final CardViewNative cardViewNative;

        public SimpleViewHolder1(View view) {
            super(view);
            cardViewNative = (CardViewNative) view.findViewById(R.id.carddemo_recyclerview_tdp);
        }
    }

    public TrendingDPSAdapter(Context context, TwoWayView recyclerView, int layoutId) {
        mContext = context;
        mItems = new ArrayList<Integer>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            addItem(i);
        }

        mRecyclerView = recyclerView;

        mLayoutId = layoutId;
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.trendingdp_item, parent, false);
        return new SimpleViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder1 holder, int position) {
/*
        holder.cardRecyclerView.setText(mItems.get(position).toString());
*/
        Card card = new Card(mContext);

        CardHeader cardHeader = new CardHeader(mContext);
        cardHeader.setTitle(mItems.get(position).toString());

        card.addCardHeader(cardHeader);
        card.setBackgroundResource(mContext.getResources().getDrawable(R.drawable.zomato));
        holder.cardViewNative.setCard(card);


        boolean isVertical = (mRecyclerView.getOrientation() == TwoWayLayoutManager.Orientation.VERTICAL);
        final View itemView = holder.itemView;

        final int itemId = mItems.get(position);

        if (mLayoutId == R.layout.layout_staggered_grid) {
            final int dimenId;
            if (itemId % 3 == 0) {
                dimenId = R.dimen.staggered_child_medium;
            } else if (itemId % 5 == 0) {
                dimenId = R.dimen.staggered_child_large;
            } else if (itemId % 7 == 0) {
                dimenId = R.dimen.staggered_child_xlarge;
            } else {
                dimenId = R.dimen.staggered_child_small;
            }

            final int span;
            if (itemId == 2) {
                span = 2;
            } else {
                span = 1;
            }

            final int size = mContext.getResources().getDimensionPixelSize(dimenId);

            final StaggeredGridLayoutManager.LayoutParams lp =
                    (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();

            if (!isVertical) {
                lp.span = span;
                lp.width = size;
                itemView.setLayoutParams(lp);
            } else {
                lp.span = span;
                lp.height = size;
                itemView.setLayoutParams(lp);
            }
        } else if (mLayoutId == R.layout.layout_spannable_grid) {
            final SpannableGridLayoutManager.LayoutParams lp =
                    (SpannableGridLayoutManager.LayoutParams) itemView.getLayoutParams();

            final int span1 = (itemId == 0 || itemId == 3 ? 2 : 1);
            final int span2 = (itemId == 0 ? 2 : (itemId == 3 ? 3 : 1));

            final int colSpan = (isVertical ? span2 : span1);
            final int rowSpan = (isVertical ? span1 : span2);

            if (lp.rowSpan != rowSpan || lp.colSpan != colSpan) {
                lp.rowSpan = rowSpan;
                lp.colSpan = colSpan;
                itemView.setLayoutParams(lp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }







}

