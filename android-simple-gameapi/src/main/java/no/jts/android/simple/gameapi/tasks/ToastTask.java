package no.jts.android.simple.gameapi.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ToastTask extends AsyncTask<Object, Void, Integer> {

	private Context context;

	public ToastTask(Context context){
		this.context = context;
	}

	@Override
	protected Integer doInBackground(Object... params) {
		int resId = (Integer) params[0];
		return resId;
	}

	@Override
	protected void onPostExecute(Integer resId) {
		Toast.makeText(context, context.getString(resId), Toast.LENGTH_LONG).show();
	}

}
