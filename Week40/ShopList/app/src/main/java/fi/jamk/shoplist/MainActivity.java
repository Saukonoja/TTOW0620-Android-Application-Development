package fi.jamk.shoplist;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity implements ShopListDialogFragment.DialogListener {
    private ListView listView;
    private SQLiteDatabase db;
    private Cursor cursor;

    private final String DATABASE_TABLE = "shoplist";
    private final int DELETE_ID = 0;


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
        String[] resultColumns = new String[]{"_id", "product", "count", "price"};
        Cursor cursor = db.query(DATABASE_TABLE, resultColumns, null, null, null, null, "price DESC", null);

        // add data to adapter
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_item, cursor,
                new String[]{"product", "count", "price"},      // from
                new int[]{R.id.product, R.id.count, R.id.price}    // to
                , 0);  // flags

        // show data in listView
        listView.setAdapter(adapter);

        double total = 0;

        if (cursor.moveToFirst()) {
            do {
                int count = cursor.getInt(2);
                double price = cursor.getFloat(3);
                total += count * price;
            } while (cursor.moveToNext());
            Toast.makeText(getBaseContext(), "Total price: " + total, Toast.LENGTH_LONG).show();
        }
    }

    public void addNew(View v) {
        ShopListDialogFragment eDialog = new ShopListDialogFragment();
        eDialog.show(getFragmentManager(), "Add new product");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String product, int count, double price) {
        ContentValues values = new ContentValues(3);
        values.put("product", product);
        values.put("count", count);
        values.put("price", price);
        db.insert("shoplist", null, values);
        queryData();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                String[] args = {String.valueOf(info.id)};
                db.delete("shoplist", "_id=?", args);
                queryData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
