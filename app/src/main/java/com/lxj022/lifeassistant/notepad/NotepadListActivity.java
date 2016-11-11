package com.lxj022.lifeassistant.notepad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.Note;
import com.lxj022.lifeassistant.data.local.db.DbOperate;

import java.util.List;

public class NotepadListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv_note_list;
    private ImageButton ib_back;
    private ImageButton ib_add_note;
    private NotepadListAdapter adapter;
    private TextView tv_empty;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_list);
        lv_note_list = (ListView) findViewById(R.id.lv_note_list);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_add_note = (ImageButton) findViewById(R.id.ib_add_note);
        tv_empty = (TextView) findViewById(R.id.tv_empty);
        ib_back.setOnClickListener(this);
        ib_add_note.setOnClickListener(this);
        lv_note_list.setOnItemClickListener(this);
        new LoadDataTask().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_add_note:
                Intent intent = new Intent(this, NotepadEditActivity.class);
                intent.putExtra("isAdd", true);
                startActivity(intent);
                break;
            case R.id.ib_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, NotepadEditActivity.class);
        intent.putExtra("isAdd", false);
        intent.putExtra("note", notes.get(i));
        startActivity(intent);
    }

    class LoadDataTask extends AsyncTask<Void, Void, List<Note>> {
        ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(NotepadListActivity.this);
            dialog.setMessage("数据加载中请稍后……");
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            return new DbOperate(NotepadListActivity.this).getAllNotes();
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            NotepadListActivity.this.notes = notes;
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (notes == null || notes.size() == 0) {
                tv_empty.setVisibility(View.VISIBLE);
                lv_note_list.setVisibility(View.GONE);
                return;
            }
            if (adapter == null) {
                tv_empty.setVisibility(View.GONE);
                lv_note_list.setVisibility(View.VISIBLE);
                adapter = new NotepadListAdapter(notes, NotepadListActivity.this);
                lv_note_list.setAdapter(adapter);
            } else {
                adapter.updateData(notes);
            }
        }
    }
}
