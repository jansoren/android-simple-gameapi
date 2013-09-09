package no.jts.android.simple.gameapi.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ToastTask extends AsyncTask<Object, Void, Object> {

	private Context context;

	public ToastTask(Context context){
		this.context = context;
	}

	@Override
	protected Object doInBackground(Object... params) {
		return params[0];
	}

	@Override
	protected void onPostExecute(Object object) {
		String message = "";
		if(object instanceof Integer){
			Integer resId = (Integer)object;
			message = context.getString(resId);
		} else if(object instanceof String){
			message = (String)object;
		}
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
