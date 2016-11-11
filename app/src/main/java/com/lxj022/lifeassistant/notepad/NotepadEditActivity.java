package com.lxj022.lifeassistant.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.Note;
import com.lxj022.lifeassistant.data.local.db.DbOperate;

public class NotepadEditActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private ImageButton ib_back;
    private ImageButton ib_delete_note;
    private EditText et_note;
    private boolean isTextChanged = false;
    private DbOperate dbOperate;
    private Note note;
    private boolean isAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_edit);
        Intent intent = getIntent();
        isAddNote = intent.getBooleanExtra("isAdd", false);
        note = (Note) intent.getSerializableExtra("note");
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_delete_note = (ImageButton) findViewById(R.id.ib_delete_note);
        et_note = (EditText) findViewById(R.id.et_note);

        if (isAddNote) {
            ib_delete_note.setVisibility(View.GONE);
        } else {
            et_note.setText(note.getContent());
            et_note.setSelection(note.getContent().length());
            ib_delete_note.setVisibility(View.VISIBLE);
        }
        ib_back.setOnClickListener(this);
        ib_delete_note.setOnClickListener(this);
        et_note.addTextChangedListener(this);
        dbOperate = new DbOperate(this);
//        et_note.setFocusable(false);
//        et_note.setFocusableInTouchMode(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                if (isTextChanged) {
                    ib_back.setImageResource(R.drawable.weather_title_bar_back_selector);
                    isTextChanged = false;
                    String content = et_note.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (isAddNote) {
                        note = new Note();
                        note.setContent(content);
                        dbOperate.addNote(note);
                    } else {
                        note.setContent(content);
                        dbOperate.updateNote(note);
                    }
                } else {
                    finish();
                }
                break;
            case R.id.ib_delete_note:
                dbOperate.deleteNote(note);
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!isTextChanged) {
            ib_back.setImageResource(R.drawable.notepad_edit_complete_img_selector);
            isTextChanged = true;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
