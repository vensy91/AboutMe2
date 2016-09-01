package vensy.aboutme2;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int min = c.get(Calendar.MINUTE);
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView time = (TextView) findViewById(R.id.time);
        time.setText(hour + " : " + min);
        TextView date = (TextView) findViewById(R.id.date);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(dateFormat.format(calendar.getTime()));
    }

    public void showDateDialog(View view) {
        DialogFragment dialog = new DatePickFragment();
        dialog.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    public void showTimeDialog(View view) {
        DialogFragment timeDialog = new TimePickFragment();
        timeDialog.show(getSupportFragmentManager(), "TIME_PICKER");
    }

    public void showPopup(View view) {
        PopupMenu skills = new PopupMenu(this, view);
        skills.setGravity(Gravity.END);
        skills.inflate(R.menu.popup);

        skills.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.skills:
                        Intent intent = new Intent(getApplicationContext(), SkillsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.info:
                        intent = new Intent(getApplicationContext(), InfoActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.linkedin:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ru.linkedin.com/in/vensy91"));
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        skills.show();
    }
}
