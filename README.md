# Android: How switch between two activities by screen orientation

How to fix warning: "Calling ActionMode finish() in onConfigurationChanged() can lead to redraws".

There are two activities: [MainActivity](app/src/main/java/ua/in/asilichenko/testactivity/MainActivity.java) and [SecondActivity](app/src/main/java/ua/in/asilichenko/testactivity/SecondActivity.java).

+ `MainActivity` should be displayed in the portrait orientation
+ `SecondActivity` should be displayed in the landscape orientation

We need such attributes in [Manifest](app/src/main/AndroidManifest.xml):
```
<activity
        android:name=".MainActivity"
        android:screenOrientation="fullSensor"
        android:configChanges="orientation|screenSize"
...
<activity
        android:name=".SecondActivity"
        android:screenOrientation="fullSensor"
...
```
`MainActivity` starts `SecondActivity` when the screen is rotated into `LANDSCAPE`:
```
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
...
@Override
public void onConfigurationChanged(@NonNull Configuration newConfig) {
  super.onConfigurationChanged(newConfig);

  if (ORIENTATION_LANDSCAPE == newConfig.orientation) {
    startActivity(new Intent(this, SecondActivity.class));
  }
}
```

`SecondActivity` checks on create if current orientation is `LANDSCAPE`, otherwise it finishes:
```
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation) {
      finish();
      return;
    }

    setContentView(R.layout.second_activity);
    ...
  }
```
When attribute `configChanges="orientation|screenSize"` is not present - activity will be recreated on each screen rotation, otherwise - method `onConfigurationChanged` is called.

## Test cases

### Test 1

1. Start in portrait orientation:
```
MainActivity.onCreate()
MainActivity.onStart()
MainActivity.onResume()
```
2. Rotate into landscape orientation:
```
MainActivity.onConfigurationChanged()
MainActivity.onPause()
SecondActivity.onCreate()
SecondActivity.onStart()
SecondActivity.onResume()
MainActivity.onStop()
```
3. Rotate back into portrait orientation:
```
SecondActivity.onPause()
SecondActivity.onStop()
SecondActivity.onDestroy()
SecondActivity.onCreate()
MainActivity.onConfigurationChanged()
MainActivity.onRestart()
MainActivity.onStart()
MainActivity.onResume()
SecondActivity.onDestroy()
```
### Test 2

1. Start in landscape orientation:
```
MainActivity.onCreate()
MainActivity.onStart()
MainActivity.onResume()
MainActivity.onPause()
SecondActivity.onCreate()
SecondActivity.onStart()
SecondActivity.onResume()
MainActivity.onStop()
```
2. Rotate into portrait orientation:
```
SecondActivity.onPause()
SecondActivity.onStop()
SecondActivity.onDestroy()
SecondActivity.onCreate()
MainActivity.onConfigurationChanged()
MainActivity.onRestart()
MainActivity.onStart()
MainActivity.onResume()
SecondActivity.onDestroy()
```
3. Rotate back into landscape orientation:
```
MainActivity.onConfigurationChanged()
MainActivity.onPause()
SecondActivity.onCreate()
SecondActivity.onStart()
SecondActivity.onResume()
MainActivity.onStop()
```
### Test 3

1. Start in portrait orientation.
2. Minimize application:
```
MainActivity.onPause()
MainActivity.onStop()
```
3. Rotate into landscape orientation.
4. Restore application:
```
MainActivity.onConfigurationChanged()
MainActivity.onRestart()
MainActivity.onStart()
MainActivity.onResume()
MainActivity.onPause()
SecondActivity.onCreate()
SecondActivity.onStart()
SecondActivity.onResume()
MainActivity.onStop()
```
### Test 4

1. Start in landscape orientation.
2. Minimize application:
```
SecondActivity.onPause()
SecondActivity.onStop()
```
3. Restore application:
```
SecondActivity.onDestroy()
SecondActivity.onCreate()
MainActivity.onConfigurationChanged()
MainActivity.onRestart()
MainActivity.onStart()
MainActivity.onResume()
SecondActivity.onDestroy()
```
