package fi.jamk.shoplist;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    private ListView listView;
    private SQLiteDatabase db;

    private final String DATABASE_TABLE = "shoplist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find list view
        listView = (ListView) findViewById(android.R.id.list);
        // register listView's context menu (to delete row)
        registerForContextMenu(listView);

        // get database instance
        db = (new DatabaseOpenHelper(this)).getWritableDatabase();
        // get data with using own made queryData method
        queryData();

    }

    public void queryData() {
        //cursor = db.rawQuery("SELECT _id, name, score FROM highscores ORDER BY score DESC", null);
        // get data with query
        String[] resultColumns = new String[]{"_id","product","count","price"};
        Cursor cursor = db.query(DATABASE_TABLE, resultColumns, null, null, null, null, "price DESC", null);

        // add data to adapter
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_item, cursor,
                new String[] {"product", "count", "price"},      // from
                new int[] {R.id.product, R.id.count, R.id.price}    // to
                ,0);  // flags

        // show data in listView
        listView.setAdapter(adapter);
    }

    public void addNew(View v){
        //dodod
    }


}
