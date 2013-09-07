package ru.omsu.diveintoandroid.myavangard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ru.omsu.diveintoandroid.myavangard.R;
import ru.omsu.diveintoandroid.myavangard.model.Match;

/**
 * MatchesAdapter
 *
 * @author Anton Rozhkov <aurozhkov@gmail.com>
 */
public class MatchesAdapter extends ArrayAdapter<Match> {

    private LayoutInflater mInflater;
    private Context mContext;
    private DateFormat mDateFormat;

    public MatchesAdapter(Context context, List<Match> list) {
        super(context, R.layout.matches_list_item, list);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        mDateFormat = new SimpleDateFormat("d MMMM");
    }

    static class ViewHolder {
        TextView time;
        TextView result;
        TextView team1Name;
        ImageView team1Logo;
        TextView team2Name;
        ImageView team2Logo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.matches_list_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.time = (TextView) view.findViewById(R.id.matches_item_time);
            viewHolder.team1Name = (TextView) view.findViewById(R.id.matches_item_team1name);
            viewHolder.team1Logo = (ImageView) view.findViewById(R.id.matches_item_logo1);
            viewHolder.team2Name = (TextView) view.findViewById(R.id.matches_item_team2name);
            viewHolder.team2Logo = (ImageView) view.findViewById(R.id.matches_item_logo2);
            viewHolder.result = (TextView) view.findViewById(R.id.matches_item_result);
            view.setTag(viewHolder);
        }

        final Match match = getItem(position);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.time.setText(mDateFormat.format(match.time));
        holder.team1Name.setText(match.team1Name);
        Picasso.with(mContext).load(match.team1Logo).into(holder.team1Logo);
        holder.team2Name.setText(match.team2Name);
        Picasso.with(mContext).load(match.team2Logo).into(holder.team2Logo);
        holder.result.setText(getItem(position).result);
        return view;
    }
}
