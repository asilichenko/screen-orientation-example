# screen-orientation-example
Android example app how switch between two activites by screen orientation.

There are two activities: `MainActivity` and `SecondActivity`.

+ `MainActivity` should be displayed in the portrait orientation
+ `SecondActivity` should be displayed in the landscape orientation

We need such attributes in `Manifest`:
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
