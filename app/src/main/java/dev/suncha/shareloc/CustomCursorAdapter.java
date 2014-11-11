package dev.suncha.shareloc;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Sunny on 11/7/2014.
 * refer this http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html added on 11/10/2014
 */
public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context, ArrayList c) {
        super(context, (Cursor) c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View retView = inflater.inflate(R.layout.single_row_item,viewGroup,false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

//        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
//        tvTitle.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
//
//        TextView tvDescription = (TextView)view.findViewById(R.id.tvDescription);
//        tvDescription.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        //int titleCol = cursor.getColumnIndex()
//http://www.android86.com/android-advanced/android-sqlite-database-cursoradapter/ perfect

    }
}