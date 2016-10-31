package com.example.wzb97.mywordsapptestprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = this.getContentResolver();
        Button button=(Button)findViewById(R.id.button);
        final TextView t=(TextView)findViewById(R.id.meanText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=(EditText)findViewById(R.id.editText);
                final String s=editText.getText().toString().trim();
                Cursor cursor = resolver.query(Words.Word.CONTENT_URI,
                        new String[] { Words.Word.COLUMN_NAME_WORD},
                        Words.Word.COLUMN_NAME_WORD+" like '%"+s+"%'", null, null);
                if (cursor == null){
                    Toast.makeText(MainActivity.this,"没有找到记录",Toast.LENGTH_LONG).show();
                    return;
                }


                String msg = "";
                if (cursor.moveToFirst()){
                    do{
                        msg += "单词：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_WORD))+ ",";
                    }while(cursor.moveToNext());
                }
                t.setText(msg);
            }
        });
    }
}
