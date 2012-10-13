import com.fruitninjaremote.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AccelerometerDataActivity extends Activity implements
		SensorEventListener {
	int mLastX;
	int mLastY;
	int mLastZ;
	boolean mInitialized;

	public void onAccuracyChanged(Sensor arg0, int arg1) {	}

	public void onSensorChanged(SensorEvent event) 
	{
		
	}

}
