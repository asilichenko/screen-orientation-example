package ua.in.asilichenko.testactivity;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ToastUtil.toast(this, "second:create");

    if (ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation) {
      finish();
      return;
    }

    setContentView(R.layout.second_activity);
  }

  @Override
  protected void onStart() {
    super.onStart();
    ToastUtil.toast(this, "second:start");
  }

  @Override
  protected void onResume() {
    super.onResume();
    ToastUtil.toast(this, "second:resume");
  }

  @Override
  protected void onStop() {
    super.onStop();
    ToastUtil.toast(this, "second:stop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ToastUtil.toast(this, "second:destroy");
  }
}
