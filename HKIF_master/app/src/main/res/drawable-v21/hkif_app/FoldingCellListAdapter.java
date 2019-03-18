package com.e.hkif_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */

public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;

        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);

            // binding view parts to view holder
            viewHolder.going_numb = cell.findViewById(R.id.going);
            viewHolder.from = cell.findViewById(R.id.from);
            viewHolder.to = cell.findViewById(R.id.to);
            viewHolder.temperature = cell.findViewById(R.id.temperature);
            viewHolder.sport_name = cell.findViewById(R.id.sport_name);
            viewHolder.sport_name_1 = cell.findViewById(R.id.sport_name_1);
            viewHolder.time = cell.findViewById(R.id.time);
            viewHolder.day = cell.findViewById(R.id.date);
            viewHolder.head_image = cell.findViewById(R.id.head_image);
            viewHolder.temperature_1 = cell.findViewById(R.id.temperature_1);
            viewHolder.going_1 = cell.findViewById(R.id.going_1);
            viewHolder.leader_name = cell.findViewById(R.id.leader_name);
            viewHolder.to_1 = cell.findViewById(R.id.to_1);
            viewHolder.from_1 = cell.findViewById(R.id.from_1);
            viewHolder.location_date = cell.findViewById(R.id.location_date);
            viewHolder.location = cell.findViewById(R.id.location);


            //TODO button done / initializing all buttons done //
            viewHolder.contentCalenderBtn = cell.findViewById(R.id.content_calender_btn);
            viewHolder.going_button = cell.findViewById(R.id.content_going_btn);



            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.going_numb.setText(item.getGoing());
        viewHolder.going_1.setText(item.getGoing());
        viewHolder.time.setText(item.getLocation_date());
        viewHolder.day.setText(item.getDay());
        viewHolder.temperature.setText(item.getTemperature());
        viewHolder.temperature_1.setText(item.getTemperature());
        viewHolder.from.setText(item.getFrom());
        viewHolder.from_1.setText(item.getFrom());
        viewHolder.to.setText(String.valueOf(item.getTo()));
        viewHolder.to_1.setText(String.valueOf(item.getTo()));
        viewHolder.sport_name.setText(item.getSport_name());
        viewHolder.sport_name_1.setText(item.getSport_name());
        viewHolder.location.setText(item.getLocation());
        viewHolder.location_date.setText(item.getLocation_date());
        viewHolder.leader_name.setText(item.getLeader_name());
        viewHolder.leader_name.setText(item.getLeader_name());


        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentCalenderBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder. contentCalenderBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView going_numb;
        TextView day;
        TextView time;
        TextView from;
        TextView to;
        TextView temperature;
        TextView sport_name;
        TextView sport_name_1;
        TextView contentCalenderBtn;
        TextView location;
        TextView location_date;
        TextView from_1;
        TextView to_1;
        TextView leader_name;
        TextView going_1;
        TextView temperature_1;
        TextView going_button;
        ImageView head_image;
    }
}