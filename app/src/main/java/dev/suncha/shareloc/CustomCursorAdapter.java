package dev.suncha.shareloc;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Sunny on 11/7/2014.
 * refer this http://www.mysamplecode.com/2012/07/android-listview-cursoradapter-sqlite.html added on 11/10/2014
 * why not working
 */
public class CustomCursorAdapter extends CursorAdapter {
    private Cursor cursor;

    public CustomCursorAdapter(Context context, Cursor cur) {
        super(context, cur);
        cursor = cur;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View retView = inflater.inflate(R.layout.single_row_item,viewGroup,false);
        bindView(retView,context,cursor);
        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvTitle.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));

        TextView tvDescription = (TextView)view.findViewById(R.id.tvDescription);
        tvDescription.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

//http://www.android86.com/android-advanced/android-sqlite-database-cursoradapter/ perfect


    }

    public int getCount(){
        return cursor.getCount();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


}
