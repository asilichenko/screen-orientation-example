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
    System.out.println("MainActivity.onCreate()");
    if (ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
      startActivity(new Intent(this, SecondActivity.class));
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    System.out.println("MainActivity.onRestart()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    System.out.println("MainActivity.onStart()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    System.out.println("MainActivity.onPause()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    System.out.println("MainActivity.onResume()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    System.out.println("MainActivity.onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    System.out.println("MainActivity.onDestroy()");
  }

  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    System.out.println("MainActivity.onConfigurationChanged()");

    if (ORIENTATION_LANDSCAPE == newConfig.orientation) {
      startActivity(new Intent(this, SecondActivity.class));
    }
  }
}