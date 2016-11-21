package me.wooz.mobile.android.app;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import me.wooz.mobile.android.dto.policies.DaoMaster;
import me.wooz.mobile.android.dto.policies.DaoSession;

/**
 * Created by byron on 20/11/16.
 */

public class WoozApplicaiton extends Application {

	private static final String DATABASE = "woozdb";

	private DaoSession daoSession;

	public DaoSession getDaoSession() {
		if(this.daoSession == null) {
			DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DATABASE);
			Database db = helper.getWritableDb();
			this.daoSession = new DaoMaster(db).newSession();
		}

		return this.daoSession;
	}
}
