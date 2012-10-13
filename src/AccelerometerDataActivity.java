import com.fruitninjaremote.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AccelerometerDataActivity extends Activity implements SensorEventListener {

	float xLinearAcceleration;
	float yLinearAcceleration;
	float zLinearAcceleration;
	float[] gravity = new float[3];
	TextView display = new TextView(this);
	public void onCreate()
	{
		setContentView(display);
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {	}

	public void onSensorChanged(SensorEvent event) 
	{
		if (event.sensor != null && event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
		{
			
			xLinearAcceleration=event.values[0];
			yLinearAcceleration=event.values[1];
			zLinearAcceleration=event.values[2];
			display.setText(xLinearAcceleration + " / " + yLinearAcceleration + " / " + zLinearAcceleration);
		}
	}

}
