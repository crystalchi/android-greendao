package com.crystal.androidgreendao;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import com.crystal.androidgreendao.db.DaoMaster;
import com.crystal.androidgreendao.db.DaoSession;
import com.crystal.androidgreendao.db.Note;
import com.crystal.androidgreendao.db.NoteDao;
import java.text.DateFormat;
import java.util.Date;

import de.greenrobot.dao.database.Database;

public class MainActivity extends AppCompatActivity {

    private Database db;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NoteDao noteDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.EncryptedDevOpenHelper helper = new DaoMaster.EncryptedDevOpenHelper(this, "notes-db2");
        db = helper.getWritableDatabase("123456");
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        Note note2 = new Note(null, "我和我的小花猫", comment, new Date());
        noteDao.insert(note2);


    }




}
