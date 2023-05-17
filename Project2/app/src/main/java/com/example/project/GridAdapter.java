package com.example.project;

import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class GridAdapter extends ArrayAdapter  {
    Context c;
    String[] p;
    int[] imArrows;
    String[] arrowsDir;
    String[] letters;
    String temp;
    RecyclerView.ViewHolder holder;
    View boardView;
    int[] bgColor;

    public GridAdapter(Context context, String[] s1, int[] arrows, String[] arrowsDir, String[] letters, int[] bgColor) {
        super(context, R.layout.card_item, R.id.idTVCourse, s1);
        c = context;
        p = s1;
        imArrows=arrows;
        this.arrowsDir=arrowsDir;
        this.letters=letters;
        this.bgColor=bgColor;
    }

/*
    public void updateLetter(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        EditText lt= (EditText) convertView.findViewById(R.id.setLetter);
        if(!lt.getText().toString().equals("")) {
            temp = lt.getText().toString();
            letters[position] = lt.getText().toString();
        }
    }*/

    public View getView(int position, View convertView, ViewGroup parent) {
        // View listItemView = convertView;
        // listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        boardView = convertView;
        TextView tv = (TextView) convertView.findViewById(R.id.idTVCourse);
        ImageView im = (ImageView) convertView.findViewById(R.id.arrow);
        EditText lt = (EditText) convertView.findViewById(R.id.setLetter);
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.lLayout);
        ll.setBackgroundColor(Color.TRANSPARENT);
        lt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (!lt.getText().toString().equals("")) {
                    temp = lt.getText().toString();
                    letters[position] = lt.getText().toString();
                }
                return false;
            }
        });
        //lt.setText("C");
        if (p[position] != null) {
            tv.setText(p[position]);
        } else
            tv.setText(" ");
        if (arrowsDir[position] != null) {
            if (arrowsDir[position].equals("down"))
                im.setImageResource(imArrows[0]);
            else if (arrowsDir[position].equals("up right"))
                im.setImageResource(imArrows[1]);
            else if (arrowsDir[position].equals("up left"))
                im.setImageResource(imArrows[2]);
            else if (arrowsDir[position].equals("right"))
                im.setImageResource(imArrows[3]);
            else if (arrowsDir[position].equals("down left"))
                im.setImageResource(imArrows[4]);
            else if (arrowsDir[position].equals("down right col"))
                im.setImageResource(imArrows[5]);
            else if (arrowsDir[position].equals("up right row"))
                im.setImageResource(imArrows[6]);
        } else {
            im.setImageResource(android.R.color.transparent);
            //im.setImageBitmap(null);
        }
        if (letters[position] != null)
            lt.setText(letters[position]);

        if (bgColor[position] == 0) {
            ll.setBackgroundColor(Color.TRANSPARENT);
        }
        else if(bgColor[position]==1) {
            ll.setBackgroundColor(Color.GREEN);
        }
        else if(bgColor[position]==2) {
            ll.setBackgroundColor(Color.RED);
        }


/*        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!lt.getText().toString().equals("")) {
                    temp = lt.getText().toString();
                    letters[position] = lt.getText().toString();
                }
            }
        });*/

        return convertView;
    }

}