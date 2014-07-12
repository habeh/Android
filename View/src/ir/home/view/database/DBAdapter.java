
package ir.home.view.database;

import ir.home.model.TbMessage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    // TbMessage
    static final String Message_ROWID = "Id";
    static final String Message_MESSAGEID = "MessageId";
    static final String Message_USERID = "UserId";
    static final String Message_USERNAME = "UserName";
    static final String Message_CATEGORYTITLE = "CategoryTitle";
    static final String Message_CATEGORYID = "CategoryId";
    static final String Message_DESCRIPTION = "Description";
    static final String Message_SENDDATE = "SendDate";
    static final String Message_TOTALSHARE = "TotalShare";
    static final String Message_LOCALSHARE = "LocalShare";

    // TbUserMessage
    static final String usermessage_ROWID = "Id";
    static final String usermessage_SENDERUSERID = "SendUserId";
    static final String usermessage_SENDERUSERNAME = "SenderUserName";
    static final String usermessage_MESSAGE = "Message";
    static final String usermessage_RECEIVEUSERID = "ReceiveUserId";
    static final String usermessage_RECEIVEUSERNAME = "ReceiveUserName";
    static final String usermessage_RECEIVESTATE = "ReceiveState";
    static final String usermessage_DATE = "Date";

    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "Habbeh.db";
    static final String DATABASE_TBMESSAGE = "TbMessage";
    static final String DATABASE_TBUSERMESSAGE = "TbUserMessage";
    static final int DATABASE_VERSION = 2;

    static final String CREATE_TBMESSAGE = "create table " + DATABASE_TBMESSAGE
            + "(" + usermessage_ROWID + " integer primary key autoincrement, "
            + Message_MESSAGEID + " INTEGER not null," + Message_USERID
            + " INTEGER not null," + Message_USERNAME + " TEXT,"
            + Message_CATEGORYTITLE + " TEXT," + Message_CATEGORYID + " INTEGER,"
            + Message_DESCRIPTION + " TEXT,"
            + Message_SENDDATE + " TEXT ," + Message_TOTALSHARE + " INTEGER,"
            + Message_LOCALSHARE + " INTEGER DEFAULT 0);";

    static final String CREATE_TBUSERMESSAGE = "create table "
            + DATABASE_TBUSERMESSAGE + "(" + usermessage_ROWID
            + " integer primary key autoincrement, " + usermessage_SENDERUSERID
            + " INTEGER not null," + usermessage_SENDERUSERNAME + " TEXT,"
            + usermessage_MESSAGE + " TEXT," + usermessage_RECEIVEUSERID
            + " INTEGER not null," + usermessage_RECEIVEUSERNAME + " TEXT,"
            + usermessage_RECEIVESTATE + " TEXT ," + usermessage_DATE
            + " DATETIME);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TBMESSAGE);
                db.execSQL(CREATE_TBUSERMESSAGE);
                Log.w(TAG, "creat database");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TBMESSAGE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TBUSERMESSAGE);
            onCreate(db);
        }
    }

    // ---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // ---closes the database---
    public void close() {
        DBHelper.close();
    }

    public long insertTbMessage(int messageId, int userId, String userName,
            String categoryTitle, int categoryId, String description, int totalShare,
            String sendDate) throws ParseException {

        ContentValues initialValues = new ContentValues();
        initialValues.put(Message_MESSAGEID, messageId);
        initialValues.put(Message_USERID, userId);
        initialValues.put(Message_USERNAME, userName);
        initialValues.put(Message_CATEGORYTITLE, categoryTitle);
        initialValues.put(Message_CATEGORYID, categoryId);
        initialValues.put(Message_DESCRIPTION, description);
        initialValues.put(Message_SENDDATE, sendDate);
        initialValues.put(Message_TOTALSHARE, totalShare);

        // Toast.makeText(context,
        // sendDate, Toast.LENGTH_LONG)
        // .show();

        return db.insert(DATABASE_TBMESSAGE, null, initialValues);
    }

    public long insertTbUserMessage(int senderUserId, String senderUserName,
            String message, int receiveUserId, String receiveUserName,
            String receiveState, Date date, String Table) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(usermessage_SENDERUSERID, senderUserId);
        initialValues.put(usermessage_SENDERUSERNAME, senderUserName);
        initialValues.put(usermessage_MESSAGE, message);
        initialValues.put(usermessage_RECEIVEUSERID, receiveUserId);
        initialValues.put(usermessage_RECEIVEUSERNAME, receiveUserName);
        initialValues.put(usermessage_RECEIVESTATE, receiveState);
        initialValues.put(usermessage_DATE, date.toString());
        return db.insert(Table, null, initialValues);
    }

    // ---retrieves all the SavedMassage---
    public List<TbMessage> getAllSaveMessage(int categoryId) {
        Cursor cursor = db.query(DATABASE_TBMESSAGE, new String[] {
                Message_MESSAGEID,
                Message_USERID, Message_CATEGORYTITLE, Message_USERNAME,
                Message_DESCRIPTION, Message_SENDDATE, Message_TOTALSHARE,
                Message_LOCALSHARE
        }, Message_CATEGORYID + "=?", new String[] {
            String.valueOf(categoryId)
        }, null, null, null);
        List<TbMessage> allOfflineMessage = cursorToList(cursor);
        return allOfflineMessage;
    }

    private List<TbMessage> cursorToList(Cursor cursor) {
        List<TbMessage> SetallOfflineMessage = new ArrayList<TbMessage>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                TbMessage temp = new TbMessage();
                temp.setId(Integer.parseInt(cursor.getString(0)));
                temp.setUserId(Integer.parseInt(cursor.getString(1)));
                temp.setCategoryTitle(cursor.getString(2));
                temp.setUserName(cursor.getString(3));
                temp.setDescription(cursor.getString(4));
                temp.setSendDate(cursor.getString(5));

                SetallOfflineMessage.add(temp);
            }

        }
        return SetallOfflineMessage;
    }

    public String getLastUpdate()
    {
        Cursor cursor = null;
        String temp = null;
        cursor = this.db.rawQuery("SELECT * FROM TbMessage order by SendDate desc limit 1", null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                temp = cursor.getString(cursor.getColumnIndex(DBAdapter.Message_SENDDATE));
            }
            cursor.close();
        }
        return temp;

    }

}
