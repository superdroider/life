package com.lxj022.lifeassistant.notepad;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.Note;

import java.util.List;

/**
 * Created by LiuXuejiao on 2016/11/6.
 */
public class NotepadListAdapter extends BaseAdapter {

    private List<Note> notes;
    private Context context;

    public NotepadListAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    public void updateData(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_note, null);
            holder = new ViewHolder();
            holder.tv_desc = (TextView) view.findViewById(R.id.tv_note_desc);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_note_time);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_time.setText(notes.get(i).getTime());
        holder.tv_desc.setText(notes.get(i).getContent());
        return view;
    }

    static class ViewHolder {
        private TextView tv_time;
        private TextView tv_desc;
    }
}
