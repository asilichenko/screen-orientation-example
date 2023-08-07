package ua.in.asilichenko.testactivity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ToastUtil.toast(this, "main:create");
  }

  @Override
  protected void onStart() {
    super.onStart();
    ToastUtil.toast(this, "main:start");

    if (ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
      startActivity(new Intent(this, SecondActivity.class));
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    ToastUtil.toast(this, "main:resume");
  }

  @Override
  protected void onStop() {
    super.onStop();
    ToastUtil.toast(this, "main:stop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ToastUtil.toast(this, "main:destroy");
  }

  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    ToastUtil.toast(this, "main:config");

    if (ORIENTATION_LANDSCAPE == newConfig.orientation) {
      startActivity(new Intent(this, SecondActivity.class));
    }
  }
}