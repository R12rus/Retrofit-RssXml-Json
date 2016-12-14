package r12.retrofitrssxmljson.RssXml.RecyclerViewAdapterRssXml;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import r12.retrofitrssxmljson.R;
import r12.retrofitrssxmljson.RssXml.ModelRss.Item;

public class RssXmlAdapter extends RecyclerView.Adapter<RssXmlAdapter.RssXmlViewHolder>{

    private ArrayList<Item> arrayList;
    private int rowLayout;
    private Context context;

    public class RssXmlViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleRssXml) TextView titleRssXml;
        @BindView(R.id.dateRssXml) TextView dateRssXml;
        @BindView(R.id.descriptionRssXml) TextView descriptionRssXml;

        public RssXmlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public RssXmlAdapter(ArrayList<Item> arrayList,int rowLayout,Context context){
        this.arrayList = arrayList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RssXmlAdapter.RssXmlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new RssXmlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RssXmlViewHolder holder, int position) {
        holder.titleRssXml.setText(arrayList.get(position).getTitle().toString());
        holder.dateRssXml.setText(arrayList.get(position).getPubDate().toString());
        holder.descriptionRssXml.setText(arrayList.get(position).getDescription().toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
